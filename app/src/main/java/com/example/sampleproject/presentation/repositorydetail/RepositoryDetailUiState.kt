package com.example.sampleproject.presentation.repositorydetail

import com.example.sampleproject.domain.model.GithubRepo

data class RepositoryDetailUiState(
    val data: GithubRepo? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)