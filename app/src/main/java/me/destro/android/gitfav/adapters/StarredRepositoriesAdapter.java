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

import com.squareup.moshi.Json;

import org.w3c.dom.Text;

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
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.forks_count)
        TextView forksCount;
        @BindView(R.id.stargazers_count)
        TextView stargazersCount;
        @BindView(R.id.watchers_count)
        TextView watchersCount;

        public StarredRepositoryHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(@NonNull StarredRepository starredRepository) {
            projectName.setText(starredRepository.name);
            fullProjectName.setText(starredRepository.fullName);
            description.setText(starredRepository.description);

            forksCount.setText(String.valueOf(starredRepository.forksCount));
            stargazersCount.setText(String.valueOf(starredRepository.stargazersCount));
            watchersCount.setText(String.valueOf(starredRepository.watchersCount));
        }
    }
}
