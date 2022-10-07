package com.example.sampleproject.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sampleproject.databinding.GithubRepositoryListFragmentBinding
import com.example.sampleproject.presentation.viewmodel.GithubRepositoryListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class GithubRepositoryListFragment : Fragment() {
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

}