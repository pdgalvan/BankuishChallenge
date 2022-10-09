package com.example.sampleproject.presentation.repositorylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleproject.databinding.GithubRepositoryListFragmentBinding
import com.example.sampleproject.presentation.repositorydetail.RepositoryDetailRequest
import com.example.sampleproject.presentation.repositorylist.paging.GithubRepositoryAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class GithubRepositoryListFragment : Fragment() {

    private lateinit var adapter: GithubRepositoryAdapter
    private lateinit var viewBinding: GithubRepositoryListFragmentBinding
    private val viewModel by viewModel<GithubRepositoryListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        viewBinding = GithubRepositoryListFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupUi()
    }

    private fun setupUi() {
        adapter = GithubRepositoryAdapter { ownerName, repoName ->
            findNavController().navigate(
                GithubRepositoryListFragmentDirections.actionGithubRepositoryListFragmentToGithubRepositoryDetailFragment(
                    RepositoryDetailRequest(
                        ownerName, repoName
                    )
                )
            )
        }
        adapter.addLoadStateListener { loadState ->
            viewBinding.recyclerGithubRepo.isVisible = loadState.source.refresh is LoadState.NotLoading
            viewBinding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            viewBinding.tvRetry.isVisible = loadState.source.refresh is LoadState.Error
        }
        viewBinding.apply {
            recyclerGithubRepo.layoutManager = LinearLayoutManager(requireContext())
            recyclerGithubRepo.adapter = adapter
            recyclerGithubRepo.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            swipeRefresh.setOnRefreshListener {
                adapter.refresh()
                viewBinding.swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getList().collect {
                    adapter.submitData(it)
                }
            }
        }
    }
}