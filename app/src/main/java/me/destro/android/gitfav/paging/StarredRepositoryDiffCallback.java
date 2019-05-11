package me.destro.android.gitfav.paging;

import androidx.recyclerview.widget.DiffUtil;

import me.destro.android.libraries.github.model.StarredRepository;

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
