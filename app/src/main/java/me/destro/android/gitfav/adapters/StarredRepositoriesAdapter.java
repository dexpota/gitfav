package me.destro.android.gitfav.adapters;

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

public class StarredRepositoriesAdapter extends RecyclerView.Adapter<StarredRepositoriesAdapter.StarredRepositoryHolder>{
    List<StarredRepository> mRepositories;

    public StarredRepositoriesAdapter(@NonNull List<StarredRepository> repositories) {
        mRepositories = repositories;
    }

    @NonNull
    @Override
    public StarredRepositoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_github_repository, viewGroup, false);
        return new StarredRepositoryHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull StarredRepositoryHolder viewHolder, int i) {
        viewHolder.bind(mRepositories.get(i));
    }

    @Override
    public int getItemCount() {
        return mRepositories != null ? mRepositories.size() : 0;
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
