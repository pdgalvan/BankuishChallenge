package com.example.sampleproject.di

import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.data.remote.mapper.GithubRepositoryListMapper
import com.example.sampleproject.data.remote.mapper.GithubRepositoryMapper
import com.example.sampleproject.data.remote.model.GithubRepoDto
import com.example.sampleproject.data.repository.GithubRepositoryImpl
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository
import com.example.sampleproject.util.Constants.GIT_HUB_URL
import com.example.sampleproject.util.ListMapper
import com.example.sampleproject.util.Mapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal val repositoryModule = module {
    factory<GithubRepository> { GithubRepositoryImpl(get(), get(), get()) }
    single {
        val httpInterceptor = HttpLoggingInterceptor()
        httpInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(httpInterceptor).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(GIT_HUB_URL)
            .build()
            .create(GithubApi::class.java)
    }
    single<Mapper<GithubRepoDto, GithubRepo>> { GithubRepositoryMapper() }
    single<ListMapper<GithubRepoDto, GithubRepo>> { GithubRepositoryListMapper(get()) }
}