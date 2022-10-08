package com.example.sampleproject.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.domain.model.GithubRepo

class GithubRepositoryPagingSource(
    private val api: GithubApi,
    private val query: String
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
            val response = api.getRepositoryList(query, 15, page)
            LoadResult.Page(
                data = response.list,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (response.list.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

private const val FIRST_PAGE = 1;