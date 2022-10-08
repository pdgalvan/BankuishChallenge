package com.example.sampleproject.data.remote

import com.example.sampleproject.domain.model.GhResponse
import com.example.sampleproject.domain.model.GithubRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubApi {
    private companion object {
        const val QUERY_PARAM = "q"
        const val AMOUNT_PER_PAGE = "per_page"
        const val CURRENT_PAGE = "page"
        const val SEARCH = "search"

        private object Search {
            const val REPOSITORY = "$SEARCH/repositories"
        }
    }

    @GET(Search.REPOSITORY)
    suspend fun getRepositoryList(
        @Query(QUERY_PARAM) query: String,
        @Query(AMOUNT_PER_PAGE) amountPerPage: Int,
        @Query(CURRENT_PAGE) currentPage: Int
    ): GhResponse
}