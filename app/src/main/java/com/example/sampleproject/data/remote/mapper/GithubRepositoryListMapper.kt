package com.example.sampleproject.data.remote.mapper

import com.example.sampleproject.data.remote.model.GithubRepoDto
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.util.ListMapper
import com.example.sampleproject.util.Mapper

class GithubRepositoryListMapper(
    private val mapper: Mapper<GithubRepoDto, GithubRepo>
) : ListMapper<GithubRepoDto, GithubRepo> {
    override fun toModel(value: List<GithubRepoDto>): List<GithubRepo> {
        return value.map(mapper::toModel)
    }
}