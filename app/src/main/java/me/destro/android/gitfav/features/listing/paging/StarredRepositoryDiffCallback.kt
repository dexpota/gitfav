package me.destro.android.gitfav.features.listing.paging

import androidx.recyclerview.widget.DiffUtil
import me.destro.android.gitfav.domain.model.Repository


class StarredRepositoryDiffCallback : DiffUtil.ItemCallback<Repository>() {
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return newItem.name == oldItem.name
                && newItem.owner == newItem.owner
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.owner == newItem.owner &&
                oldItem.description == newItem.description &&
                oldItem.topics.contentEquals(newItem.topics)
    }
}
