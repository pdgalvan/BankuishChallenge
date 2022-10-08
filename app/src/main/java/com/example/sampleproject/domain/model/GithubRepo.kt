package com.example.sampleproject.domain.model

import java.io.Serializable


data class GithubRepo(
    val name: String,
    val owner: Owner
) : Serializable