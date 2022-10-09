package com.example.sampleproject.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.data.remote.model.GithubRepoDto
import com.example.sampleproject.data.remote.model.GithubRepositoryListResponse
import com.example.sampleproject.data.remote.model.OwnerDto
import com.example.sampleproject.data.repository.GithubRepositoryPagingSource
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.model.Owner
import com.example.sampleproject.util.ListMapper
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GithubRepositoryPagingSourceTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var api: GithubApi
    lateinit var repositoryPagingSource: GithubRepositoryPagingSource
    lateinit var listMapper: ListMapper<GithubRepoDto, GithubRepo>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        listMapper = provideListMapper()
        repositoryPagingSource = GithubRepositoryPagingSource(api, "kotlin", listMapper)
    }

    @After
    fun tearDown() {
        validateMockitoUsage()
    }

    @Test
    fun `load returns Error WHEN RuntimeException is thrown`() = runTest {

        val error = RuntimeException("404", Throwable())
        given(api.getRepositoryList(any(), any(), any())).willThrow(error)

        val expectedResult = PagingSource.LoadResult.Error<Int, GithubRepo>(error)

        assertEquals(
            expectedResult, repositoryPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `load returns Error WHEN NullPointerException is thrown`() = runTest {
        given(api.getRepositoryList(any(), any(), any())).willReturn(null)
        val expectedResult = PagingSource.LoadResult.Error<Int, GithubRepo>(NullPointerException())

        assertEquals(
            expectedResult.toString(), repositoryPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            ).toString()
        )
    }

    @Test
    fun `load returns Page with data WHEN On successful`() = runTest {
        given(
            api.getRepositoryList(
                any(),
                any(),
                any()
            )
        ).willReturn(provideRepositoryListResponse())

        val expectedResult = PagingSource.LoadResult.Page(
            data = listOf(
                GithubRepo(
                    name = "name",
                    owner = Owner("owner"),
                    topics = emptyList(),
                    description = "description",
                    watchersCount = 1,
                    starsCount = 1

                )
            ),
            prevKey = -1,
            nextKey = 1
        )

        assertEquals(
            expectedResult, repositoryPagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    private fun provideRepositoryListResponse() = GithubRepositoryListResponse(
        list = listOf(
            GithubRepoDto(
                "name",
                OwnerDto("owner"),
                emptyList(),
                "description",
                1,
                1
            )
        )
    )

    private fun provideListMapper(): ListMapper<GithubRepoDto, GithubRepo> {
        return mock {
            on { toModel(anyList()) } doReturn listOf(
                GithubRepo(
                    name = "name",
                    owner = Owner("owner"),
                    topics = emptyList(),
                    description = "description",
                    watchersCount = 1,
                    starsCount = 1

                )
            )
        }
    }
}