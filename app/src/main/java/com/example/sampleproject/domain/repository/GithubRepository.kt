package com.example.sampleproject.domain.repository

import com.example.sampleproject.domain.model.GithubRepo

interface GithubRepository {
    suspend fun getRepositoryList(
        query: String,
        amountPerPage: Int,
        currentPage: Int
    ): List<GithubRepo>
}