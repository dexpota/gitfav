package me.destro.android.gitfav.features.listing.adapters;

import androidx.paging.PagedListAdapter;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.destro.android.gitfav.R;
import me.destro.android.libraries.github.model.StarredRepository;
import me.destro.android.gitfav.features.listing.paging.StarredRepositoryDiffCallback;

public class StarredRepositoriesAdapter extends PagedListAdapter<StarredRepository, StarredRepositoriesAdapter.StarredRepositoryHolder> {

    public StarredRepositoriesAdapter() {
        super(new StarredRepositoryDiffCallback());
    }

    @NonNull
    @Override
    public StarredRepositoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_github_repository, viewGroup, false);
        return new StarredRepositoryHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull StarredRepositoryHolder viewHolder, int i) {
        StarredRepository item = getItem(i);
        if (item != null)
            viewHolder.bind(item);
    }

    class StarredRepositoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.full_project_name)
        TextView fullProjectName;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.stargazers_count)
        TextView stargazersCount;

        @BindView(R.id.topics)
        ChipGroup topicsChipGroup;

        public StarredRepositoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull StarredRepository starredRepository) {
            fullProjectName.setText(starredRepository.fullName);
            description.setText(starredRepository.description);
            stargazersCount.setText(String.valueOf(starredRepository.stargazersCount));

            if (starredRepository.topics.length != 0) {
                buildTopicsChips(starredRepository.topics);
                topicsChipGroup.setVisibility(View.VISIBLE);
            } else {
                topicsChipGroup.setVisibility(View.GONE);
            }
        }

        private void buildTopicsChips(String[] topics) {
            // Create new child as needed.
            int childCountBefore = topicsChipGroup.getChildCount();
            for (int i = 0; i < topics.length - childCountBefore; i++) {
                topicsChipGroup.addView(new Chip(topicsChipGroup.getContext()));
            }


            for (int i = 0; i < topics.length; i++) {
                String topic = topics[i];
                ((Chip)topicsChipGroup.getChildAt(i)).setText(topic);
            }

            // Hide those not-used chips.
            for (int i = topics.length; i < topicsChipGroup.getChildCount(); i++) {
                topicsChipGroup.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }
}
