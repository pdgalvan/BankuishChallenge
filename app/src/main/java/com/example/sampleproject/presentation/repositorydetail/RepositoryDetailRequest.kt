package com.example.sampleproject.presentation.repositorydetail

import java.io.Serializable

data class RepositoryDetailRequest(
    val ownerName: String,
    val repoName: String
):Serializable
