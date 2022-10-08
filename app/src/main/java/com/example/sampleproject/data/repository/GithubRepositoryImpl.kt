package com.example.sampleproject.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.data.remote.mapper.GithubRepositoryMapper
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow

class GithubRepositoryImpl constructor(
    private val api: GithubApi,
    private val mapper: GithubRepositoryMapper? =null
) : GithubRepository {
    override suspend fun getRepositoryList(
        query: String
    ): Flow<PagingData<GithubRepo>> {
        return Pager(
            pagingSourceFactory = { GithubRepositoryPagingSource(api, query) },
            config = PagingConfig(pageSize = 15)
        ).flow
    }
}