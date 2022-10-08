package com.example.sampleproject.presentation.repositorydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.sampleproject.databinding.GithubRepositoryDetailFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class GithubRepositoryDetailFragment : Fragment() {

    private lateinit var viewBinding: GithubRepositoryDetailFragmentBinding
    private val viewModel by viewModel<GithubRepositoryDetailViewModel>{ parametersOf(args.request)}
    private val args: GithubRepositoryDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        viewBinding = GithubRepositoryDetailFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    setupUi(state)
                }
            }
        }
    }

    private fun setupUi(state: RepositoryDetailUiState) {
        with(viewBinding) {
            progressBar.isVisible = state.isLoading
            state.data?.let {
                tvRepoName.text = it.name
                tvOwnerName.text = it.owner.name
                tvAbout.text = it.description
                tvWatcherCount.text = it.watchersCount.toString()
                tvStarCount.text = it.starsCount.toString()
            }
        }
    }
}