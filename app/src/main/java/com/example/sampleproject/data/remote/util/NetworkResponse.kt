package com.example.sampleproject.data.remote.util

sealed class NetworkResponse<out R>{
    data class Success<out R>(val data: R?)
}
