package com.example.sampleproject.di

import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.data.repository.GithubRepositoryImpl
import com.example.sampleproject.domain.repository.GithubRepository
import com.example.sampleproject.util.Constant.GIT_HUB_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal val repositoryModule = module {
    factory<GithubRepository> { GithubRepositoryImpl(get()) }
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
}