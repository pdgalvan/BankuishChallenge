package com.example.sampleproject.util

sealed class RepositoryResponse<out T>(
    val status: ResponseStatus,
    val data: T?,
    val message: String?
) {
    data class Success<out T>(val _data: T?) : RepositoryResponse<T>(
        status = ResponseStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String?) : RepositoryResponse<Nothing>(
        status = ResponseStatus.ERROR,
        data = null,
        message = exception
    )
}

enum class ResponseStatus {
    SUCCESS,
    ERROR
}
