package com.example.sampleproject.data.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepositoryListResponse(
    @SerializedName("items")
    val list: List<GithubRepoDto>
)