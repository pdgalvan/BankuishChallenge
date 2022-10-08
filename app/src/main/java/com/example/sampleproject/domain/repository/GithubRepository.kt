package com.example.sampleproject.domain.repository

import androidx.paging.PagingData
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.util.RepositoryResponse
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun getRepositoryList(
        query: String
    ): Flow<PagingData<GithubRepo>>

    suspend fun getRepositoryDetail(
        ownerName: String,
        repoName: String
    ): RepositoryResponse<GithubRepo>

}