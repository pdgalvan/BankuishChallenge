package com.example.sampleproject.viewmodel

import com.example.sampleproject.MainCoroutineScopeRule
import com.example.sampleproject.domain.model.GithubRepo
import com.example.sampleproject.domain.model.Owner
import com.example.sampleproject.domain.usecase.GetRepositoryDetailUseCase
import com.example.sampleproject.presentation.repositorydetail.GithubRepositoryDetailViewModel
import com.example.sampleproject.presentation.repositorydetail.RepositoryDetailRequest
import com.example.sampleproject.presentation.repositorydetail.RepositoryDetailUiState
import com.example.sampleproject.util.RepositoryResponse
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GithubRepositoryDetailViewModelTest {

    private val useCase = GetRepositoryDetailUseCase(mock())

    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @After
    fun tearDown() {
        validateMockitoUsage()
    }

    @Test
    fun `WHEN  useCase is invoked and returns Success THEN uiState data is not null and isLoading is false`() =
        runTest {
            whenever(useCase.invoke(any(), any())).doReturn(
                RepositoryResponse.Success(
                    _data = GithubRepo(
                        name = "name",
                        owner = Owner("owner"),
                        topics = emptyList(),
                        description = "description",
                        watchersCount = 1,
                        starsCount = 1
                    )
                )
            )
            val viewModel = GithubRepositoryDetailViewModel(
                provideRequest(),
                useCase
            )
            val expectedResult = RepositoryDetailUiState(
                isLoading = false, data = GithubRepo(
                    name = "name",
                    owner = Owner("owner"),
                    topics = emptyList(),
                    description = "description",
                    watchersCount = 1,
                    starsCount = 1
                )
            )
            assertEquals(expectedResult, viewModel.uiState.value)
        }

    @Test
    fun `WHEN useCase is invoked and returns Error THEN uiState has errorMessage`() =
        runTest {
            whenever(useCase.invoke(any(), any())).doReturn(RepositoryResponse.Error("exception"))
            val viewModel = GithubRepositoryDetailViewModel(
                provideRequest(),
                useCase
            )

            val expectedResult = RepositoryDetailUiState(errorMessage = "exception", isLoading = false)

            assertEquals(expectedResult, viewModel.uiState.value)
        }

    private fun provideRequest() = RepositoryDetailRequest("ownerName", "repoName")
}