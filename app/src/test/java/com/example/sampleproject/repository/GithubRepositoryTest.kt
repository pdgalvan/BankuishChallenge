package com.example.sampleproject.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleproject.data.remote.GithubApi
import com.example.sampleproject.data.remote.model.GithubRepoDto
import com.example.sampleproject.data.remote.model.OwnerDto
import com.example.sampleproject.data.repository.GithubRepositoryImpl
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.model.Owner
import com.example.sampleproject.domain.repository.GithubRepository
import com.example.sampleproject.util.Mapper
import com.example.sampleproject.util.RepositoryResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.validateMockitoUsage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GithubRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var api: GithubApi
    private lateinit var repository: GithubRepository
    lateinit var mapper: Mapper<GithubRepoDto, GithubRepo>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        mapper = provideMapper()
        repository = GithubRepositoryImpl(api, mapper, mock())
    }

    @After
    fun tearDown() {
        validateMockitoUsage()
    }

    @Test
    fun `getRepositoryDetail returns Success with data`() = runTest {
        given(api.getRepositoryDetail(any(), any())).willReturn(
            GithubRepoDto(
                "name",
                OwnerDto("owner"),
                emptyList(),
                "description",
                1,
                1
            )
        )
        val expectedResult = RepositoryResponse.Success(
            GithubRepo(
                name = "name",
                owner = Owner("owner"),
                topics = emptyList(),
                description = "description",
                watchersCount = 1,
                starsCount = 1
            )
        )

        assertEquals(expectedResult, repository.getRepositoryDetail("ownerName", "repoName"))
    }

    private fun provideMapper(): Mapper<GithubRepoDto, GithubRepo> {
        return mock {
            on { toModel(any()) } doReturn GithubRepo(
                name = "name",
                owner = Owner("owner"),
                topics = emptyList(),
                description = "description",
                watchersCount = 1,
                starsCount = 1
            )
        }
    }
}