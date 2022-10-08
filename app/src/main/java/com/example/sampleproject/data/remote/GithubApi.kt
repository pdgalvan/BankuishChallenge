package com.example.sampleproject.data.remote

import com.example.sampleproject.data.remote.model.GithubRepoDto
import com.example.sampleproject.data.remote.model.GithubRepositoryListResponse
import com.example.sampleproject.domain.model.GithubRepo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GithubApi {
    private companion object {
        const val QUERY_PARAM = "q"
        const val AMOUNT_PER_PAGE = "per_page"
        const val CURRENT_PAGE = "page"
        const val SEARCH = "search"
        const val OWNER_NAME = "ownerName"
        const val REPO_NAME = "repoName"
        const val REPOSITORY = "repos"

        private object Search {
            const val REPOSITORY_LIST = "$SEARCH/repositories"
        }

        private object Repository {
            const val DETAIL = "$REPOSITORY/{$OWNER_NAME}/{$REPO_NAME}"
        }

    }

    @GET(Search.REPOSITORY_LIST)
    suspend fun getRepositoryList(
        @Query(QUERY_PARAM) query: String,
        @Query(AMOUNT_PER_PAGE) amountPerPage: Int,
        @Query(CURRENT_PAGE) currentPage: Int
    ): GithubRepositoryListResponse

    @GET(Repository.DETAIL)
    suspend fun getRepositoryDetail(
        @Path(OWNER_NAME) ownerName: String,
        @Path(REPO_NAME) repoName: String
    ): GithubRepoDto
}