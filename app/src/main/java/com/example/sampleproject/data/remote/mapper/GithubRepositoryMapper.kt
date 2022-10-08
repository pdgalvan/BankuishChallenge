package com.example.sampleproject.data.remote.mapper

import com.example.sampleproject.data.remote.model.GithubRepoDto
import com.example.sampleproject.data.remote.model.OwnerDto
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.model.Owner
import com.example.sampleproject.domain.model.Topic
import com.example.sampleproject.util.Mapper

class GithubRepositoryMapper : Mapper<GithubRepoDto, GithubRepo> {

    override fun toModel(value: GithubRepoDto): GithubRepo {
        with(value) {
            return GithubRepo(
                name = name,
                owner = mapOwner(owner),
                topics = mapTopicList(topics),
                description = description,
                watchersCount = watchersCount,
                starsCount = starsCount
            )
        }
    }

    private fun mapTopicList(list: List<String>) = list.map { name ->
        Topic(name)
    }

    private fun mapOwner(ownerDto: OwnerDto) = Owner(ownerDto.name)
}