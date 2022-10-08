package com.example.sampleproject.presentation.repositorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.usecase.GetRepositoryListUseCase
import kotlinx.coroutines.flow.Flow

class GithubRepositoryListViewModel constructor(
    private val getRepositoryListUseCase: GetRepositoryListUseCase
) : ViewModel() {

    suspend fun getList(): Flow<PagingData<GithubRepo>> {
        return getRepositoryListUseCase.invoke("kotlin").cachedIn(viewModelScope)
    }
}