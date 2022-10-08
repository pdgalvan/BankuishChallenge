package com.example.sampleproject.presentation.repositorylist.paging

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.databinding.ItemGithubRepositoryBinding
import com.example.sampleproject.domain.model.GithubRepo

class GithubRepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemGithubRepositoryBinding.bind(view)

    fun setup(githubRepo: GithubRepo) {
        binding.apply {
            repoName.text = githubRepo.name
            ownerName.text = githubRepo.owner.name
        }
    }

}