package com.example.sampleproject.domain.usecase

import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository
import com.example.sampleproject.util.RepositoryResponse

class GetRepositoryDetailUseCase constructor(
    private val repository: GithubRepository
) {
    suspend operator fun invoke(
        ownerName: String,
        repoName: String
    ): RepositoryResponse<GithubRepo> {
        return repository.getRepositoryDetail(ownerName, repoName)
    }
}