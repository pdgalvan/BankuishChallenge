package com.example.sampleproject.presentation.repositorylist.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.sampleproject.R
import com.example.sampleproject.domain.model.GithubRepo

class GithubRepositoryAdapter :
    PagingDataAdapter<GithubRepo, GithubRepositoryViewHolder>(COMPARATOR) {
    override fun onBindViewHolder(holder: GithubRepositoryViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.setup(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubRepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GithubRepositoryViewHolder(
            layoutInflater.inflate(
                R.layout.item_github_repository,
                parent,
                false
            )
        )
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
                return oldItem.name == newItem.name && oldItem.owner == newItem.owner
            }

            override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
                return oldItem == newItem
            }

        }
    }

}