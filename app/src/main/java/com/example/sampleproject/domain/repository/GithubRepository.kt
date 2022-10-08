package com.example.sampleproject.domain.repository

import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.util.RepositoryResponse
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun getRepositoryList(
        query: String,
        amountPerPage: Int,
        currentPage: Int
    ): Flow<RepositoryResponse<List<GithubRepo>>>
}