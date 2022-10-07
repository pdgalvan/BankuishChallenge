package com.example.sampleproject.di

import com.example.sampleproject.presentation.viewmodel.GithubRepositoryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel {
        GithubRepositoryListViewModel(get())
    }
}