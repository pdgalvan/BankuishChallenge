package com.example.sampleproject.data.repository

import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository

class GithubRepositoryImpl(
    private val api: GithubApi
) : GithubRepository {
    override suspend fun getRepositoryList(
        query: String,
        amountPerPage: Int,
        currentPage: Int
    ): List<GithubRepo> {
        return api.getRepositoryList(query,amountPerPage,currentPage).list
    }
}