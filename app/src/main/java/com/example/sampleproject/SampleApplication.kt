package com.example.sampleproject

import android.app.Application
import com.example.sampleproject.di.repositoryModule
import com.example.sampleproject.di.useCaseModule
import com.example.sampleproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@SampleApplication)
            modules(listOf(repositoryModule, useCaseModule, viewModelModule))
        }
    }
}