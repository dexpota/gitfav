package me.destro.android.gitfav.paging;

import android.support.v7.util.DiffUtil;

import me.destro.android.gitfav.github.model.StarredRepository;

public class StarredRepositoryDiffCallback extends DiffUtil.ItemCallback<StarredRepository> {
    @Override
    public boolean areItemsTheSame(StarredRepository oldItem, StarredRepository newItem) {
        return oldItem.id == newItem.id;
    }

    @Override
    public boolean areContentsTheSame(StarredRepository oldItem, StarredRepository newItem) {
        return oldItem.name == newItem.name &&
                oldItem.fullName == newItem.fullName &&
                oldItem.description == newItem.description &&
                oldItem.htmlUrl == newItem.htmlUrl &&
                oldItem.topics == newItem.topics;
    }
}
