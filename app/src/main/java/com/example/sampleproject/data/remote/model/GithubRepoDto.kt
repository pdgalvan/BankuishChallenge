package com.example.sampleproject.data.remote.model

import com.google.gson.annotations.SerializedName

data class GithubRepoDto(
    val name: String,
    @SerializedName("owner") val owner: OwnerDto,
    @SerializedName("topics") val topics: List<String>,
    val description: String,
    @SerializedName("subscribers_count") val watchersCount: Int,
    @SerializedName("watchers") val starsCount: Int,
)