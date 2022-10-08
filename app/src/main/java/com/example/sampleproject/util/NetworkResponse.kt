package com.example.sampleproject.util

sealed class NetworkResponse<out T>(
    val status: ResponseStatus,
    val data: T?,
    val message: String?
) {
    data class Success<out T>(val _data: T?) : NetworkResponse<T>(
        status = ResponseStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error(val exception: String) : NetworkResponse<Nothing>(
        status = ResponseStatus.ERROR,
        data = null,
        message = exception
    )

    data class Loading<out T>(val _data: T?, val isLoading: Boolean) : NetworkResponse<T>(
        status = ResponseStatus.LOADING,
        data = _data,
        message = null
    )

}

enum class ResponseStatus {
    SUCCESS,
    ERROR,
    LOADING
}
