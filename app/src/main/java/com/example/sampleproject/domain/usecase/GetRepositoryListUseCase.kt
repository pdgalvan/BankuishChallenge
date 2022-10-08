package com.example.sampleproject.domain.usecase

import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository
import com.example.sampleproject.util.RepositoryResponse
import kotlinx.coroutines.flow.Flow

class GetRepositoryListUseCase constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(query: String,
                                amountPerPage: Int,
                                currentPage: Int): Flow<RepositoryResponse<List<GithubRepo>>> {
        return repository.getRepositoryList(query,amountPerPage,currentPage)
    }
}