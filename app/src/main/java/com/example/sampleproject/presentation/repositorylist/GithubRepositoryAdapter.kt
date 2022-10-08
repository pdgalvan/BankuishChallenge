package com.example.sampleproject.presentation.repositorylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.R
import com.example.sampleproject.domain.model.GithubRepo

class GithubRepositoryAdapter(private val repoList: List<GithubRepo>) :
    RecyclerView.Adapter<GithubRepositoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GithubRepositoryViewHolder(layoutInflater.inflate(R.layout.item_github_repository,parent, false))
    }

    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {
        holder.setup(repoList[position])
    }

    override fun getItemCount() = repoList.size
}