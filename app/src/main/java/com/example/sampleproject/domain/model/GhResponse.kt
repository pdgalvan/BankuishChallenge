package com.example.sampleproject.domain.model

import com.google.gson.annotations.SerializedName

data class GhResponse(
    @SerializedName("items")
    val list: List<GithubRepo>
)