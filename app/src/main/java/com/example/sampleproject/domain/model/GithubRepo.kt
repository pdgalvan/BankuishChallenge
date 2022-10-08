package com.example.sampleproject.domain.model


data class GithubRepo(
    val name: String,
    val owner: Owner,
    val topics: List<Topic>,
    val description: String,
    val watchersCount: Int,
    val starsCount: Int,
)