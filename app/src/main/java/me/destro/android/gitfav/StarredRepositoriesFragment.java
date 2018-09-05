package me.destro.android.gitfav;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.destro.android.gitfav.adapters.StarredRepositoriesAdapter;
import me.destro.android.gitfav.github.model.StarredRepository;
import me.destro.android.gitfav.viewmodels.MainViewModel;


public class StarredRepositoriesFragment extends Fragment {

    @BindView(R.id.starred_repositories)
    RecyclerView rvStarredRepositories;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_starred_repositories, container, false);
        ButterKnife.bind(this, root);

        StarredRepositoriesAdapter mAdapter = new StarredRepositoriesAdapter();
        rvStarredRepositories.setAdapter(mAdapter);
        rvStarredRepositories.setLayoutManager(new LinearLayoutManager(getContext()));

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.mStarredRepository.observe(this, new Observer<PagedList<StarredRepository>>() {
            @Override
            public void onChanged(@Nullable PagedList<StarredRepository> starredRepositories) {
                mAdapter.submitList(starredRepositories);
            }
        });

        return root;
    }
}
