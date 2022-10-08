package com.example.sampleproject.data.remote.model

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("login") val name: String
)
