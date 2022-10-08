package com.example.sampleproject.presentation.repositorydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.domain.usecase.GetRepositoryDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
            val response = getRepositoryDetailUseCase.invoke(request.ownerName,request.repoName)
            _uiState.update {
                it.copy(data = response.data)
            }
            val algo = 1
        }
    }
}