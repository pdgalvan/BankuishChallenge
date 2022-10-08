package com.example.sampleproject.presentation.repositorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.domain.usecase.GetRepositoryListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GithubRepositoryListViewModel constructor(
    getRepositoryListUseCase: GetRepositoryListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RepositoryListUiState(isLoading = true))
    val uiState = _uiState

    init {
        viewModelScope.launch {
            val response = getRepositoryListUseCase.invoke("kotlin", 10, 1)
            _uiState.update { currentUiState ->
                currentUiState.copy(isLoading = false, list = response)
            }
        }
    }
}