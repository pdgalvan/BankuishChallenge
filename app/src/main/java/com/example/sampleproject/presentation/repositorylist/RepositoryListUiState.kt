package com.example.sampleproject.presentation.repositorylist

import com.example.sampleproject.domain.model.GithubRepo

data class RepositoryListUiState(
    val list: List<GithubRepo> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)