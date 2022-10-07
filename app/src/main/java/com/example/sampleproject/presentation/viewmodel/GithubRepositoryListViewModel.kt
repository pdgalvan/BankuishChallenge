package com.example.sampleproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sampleproject.domain.usecase.GetRepositoryListUseCase

class GithubRepositoryListViewModel constructor(
    getRepositoryListUseCase: GetRepositoryListUseCase
): ViewModel() {

}