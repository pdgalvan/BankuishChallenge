package com.example.sampleproject.presentation.repositorydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.domain.usecase.GetRepositoryDetailUseCase
import com.example.sampleproject.util.RepositoryResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GithubRepositoryDetailViewModel constructor(
    request: RepositoryDetailRequest,
    getRepositoryDetailUseCase: GetRepositoryDetailUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(RepositoryDetailUiState(isLoading = true))
    val uiState = _uiState

    init {
        viewModelScope.launch {
            val response = getRepositoryDetailUseCase.invoke(request.ownerName, request.repoName)
            _uiState.update {
                when (response) {
                    is RepositoryResponse.Error -> {
                        it.copy(isLoading = false, errorMessage = response.exception)
                    }
                    is RepositoryResponse.Success -> {
                        it.copy(isLoading = false, data = response.data)
                    }
                }

            }
        }
    }
}