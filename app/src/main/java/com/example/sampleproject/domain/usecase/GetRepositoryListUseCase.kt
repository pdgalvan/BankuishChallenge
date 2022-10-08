package com.example.sampleproject.domain.usecase

import androidx.paging.PagingData
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository
import com.example.sampleproject.util.RepositoryResponse
import kotlinx.coroutines.flow.Flow

class GetRepositoryListUseCase constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(query: String): Flow<PagingData<GithubRepo>> {
        return repository.getRepositoryList(query)
    }
}