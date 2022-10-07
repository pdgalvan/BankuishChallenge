package com.example.sampleproject.di

import com.example.sampleproject.data.repository.GithubRepositoryImpl
import com.example.sampleproject.domain.repository.GithubRepository
import org.koin.dsl.module

internal val repositoryModule = module {
    factory<GithubRepository> { GithubRepositoryImpl() }
}