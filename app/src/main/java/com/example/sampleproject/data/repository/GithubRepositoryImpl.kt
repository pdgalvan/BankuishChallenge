package com.example.sampleproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.data.remote.model.GithubRepoDto
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository
import com.example.sampleproject.util.ListMapper
import com.example.sampleproject.util.Mapper
import com.example.sampleproject.util.RepositoryResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class GithubRepositoryImpl constructor(
    private val api: GithubApi,
    private val mapper: Mapper<GithubRepoDto, GithubRepo>,
    private val listMapper: ListMapper<GithubRepoDto, GithubRepo>
) : GithubRepository {
    override suspend fun getRepositoryList(
        query: String
    ): Flow<PagingData<GithubRepo>> {
        return Pager(
            pagingSourceFactory = { GithubRepositoryPagingSource(api, query, listMapper) },
            config = PagingConfig(pageSize = 15)
        ).flow
    }

    override suspend fun getRepositoryDetail(
        ownerName: String,
        repoName: String
    ): RepositoryResponse<GithubRepo> {
        return try {
            val response = api.getRepositoryDetail(ownerName, repoName)
            RepositoryResponse.Success(mapper.toModel(response))
        } catch (e: HttpException) {
            RepositoryResponse.Error(e.message())
        }
    }
}