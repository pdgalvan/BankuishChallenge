package com.example.sampleproject.domain.usecase

import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository

class GetRepositoryListUseCase constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(query: String,
                                amountPerPage: Int,
                                currentPage: Int): List<GithubRepo> {
        return repository.getRepositoryList(query,amountPerPage,currentPage)
    }
}