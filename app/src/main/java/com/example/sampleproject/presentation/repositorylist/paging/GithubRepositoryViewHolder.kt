package com.example.sampleproject.presentation.repositorylist.paging

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.databinding.ItemGithubRepositoryBinding
import com.example.sampleproject.domain.model.GithubRepo

class GithubRepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemGithubRepositoryBinding.bind(view)

    fun setup(githubRepo: GithubRepo, onSelect: (String, String) -> Unit) {
        binding.apply {
            repoName.text = githubRepo.name
            ownerName.text = githubRepo.owner.name
            ownerName.setOnClickListener {
                onSelect(githubRepo.owner.name, githubRepo.name)
            }
            cvRepoItem.setOnClickListener {
                onSelect(githubRepo.owner.name, githubRepo.name)
            }
        }
    }

}