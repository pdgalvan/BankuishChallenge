package com.example.sampleproject.domain.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("login")
    val name: String
)