package com.example.sampleproject.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.data.remote.model.GithubRepoDto
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.util.ListMapper
import kotlinx.coroutines.delay

class GithubRepositoryPagingSource constructor(
    private val api: GithubApi,
    private val query: String,
    private val listMapper: ListMapper<GithubRepoDto, GithubRepo>
) : PagingSource<Int, GithubRepo>() {

    override fun getRefreshKey(state: PagingState<Int, GithubRepo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepo> {
        return try {
            val page = params.key ?: FIRST_PAGE
            val response = api.getRepositoryList(query, AMOUNT_PER_PAGE, page)
            delay(2000)
            LoadResult.Page(
                data = listMapper.toModel(response.list),
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (response.list.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private companion object {
        const val FIRST_PAGE = 1
        const val AMOUNT_PER_PAGE = 15
    }
}
