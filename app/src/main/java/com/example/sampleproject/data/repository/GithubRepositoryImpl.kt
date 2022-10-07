package com.example.sampleproject.data.repository

import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository

class GithubRepositoryImpl : GithubRepository {
    override suspend fun getRepositoryList(
        query: String,
        amountPerPage: Int,
        currentPage: Int
    ): List<GithubRepo> {
        TODO("Not yet implemented")
    }
}