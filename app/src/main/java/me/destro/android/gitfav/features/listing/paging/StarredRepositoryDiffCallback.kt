package me.destro.android.gitfav.features.listing.paging

import androidx.recyclerview.widget.DiffUtil

import me.destro.android.libraries.github.model.StarredRepository

class StarredRepositoryDiffCallback : DiffUtil.ItemCallback<StarredRepository>() {
    override fun areItemsTheSame(oldItem: StarredRepository, newItem: StarredRepository): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: StarredRepository, newItem: StarredRepository): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.fullName == newItem.fullName &&
                oldItem.description == newItem.description &&
                oldItem.htmlUrl == newItem.htmlUrl &&
                oldItem.topics.contentEquals(newItem.topics)
    }
}
