package com.example.sampleproject.data.repository

import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.data.remote.mapper.GithubRepositoryMapper
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.repository.GithubRepository
import com.example.sampleproject.util.RepositoryResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GithubRepositoryImpl constructor(
    private val api: GithubApi,
    private val mapper: GithubRepositoryMapper? =null
) : GithubRepository {
    override suspend fun getRepositoryList(
        query: String,
        amountPerPage: Int,
        currentPage: Int
    ): Flow<RepositoryResponse<List<GithubRepo>>> = flow {
        try {
            val list = api.getRepositoryList(query,amountPerPage,currentPage)
            emit(RepositoryResponse.Success(list.list))
        } catch (e: HttpException) {
            emit(RepositoryResponse.Error(e.message()))
        } catch (e: IOException) {
            emit(RepositoryResponse.Error(e.message))
        }
    }
}