package com.example.sampleproject.di

import com.example.sampleproject.domain.usecase.GetRepositoryDetailUseCase
import com.example.sampleproject.domain.usecase.GetRepositoryListUseCase
import org.koin.dsl.module

internal val useCaseModule = module {
    single { GetRepositoryListUseCase(get()) }
    single { GetRepositoryDetailUseCase(get()) }
}