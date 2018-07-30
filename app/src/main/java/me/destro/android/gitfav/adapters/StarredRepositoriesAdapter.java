package me.destro.android.gitfav.adapters;

import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.destro.android.gitfav.R;
import me.destro.android.gitfav.github.model.StarredRepository;
import me.destro.android.gitfav.paging.StarredRepositoryDiffCallback;

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
        @BindView(R.id.project_name)
        TextView projectName;
        @BindView(R.id.full_project_name)
        TextView fullProjectName;

        public StarredRepositoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(StarredRepository starredRepository) {
            projectName.setText(starredRepository.name);
            fullProjectName.setText(starredRepository.fullName);
        }
    }
}
