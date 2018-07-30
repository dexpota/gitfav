package me.destro.android.gitfav;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.destro.android.gitfav.adapters.StarredRepositoriesAdapter;
import me.destro.android.gitfav.github.model.StarredRepository;
import me.destro.android.gitfav.utilities.Algorithms;
import me.destro.android.gitfav.viewmodels.MainViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.starred_repositories)
    RecyclerView rvStarredRepositories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        StarredRepositoriesAdapter mAdapter = new StarredRepositoriesAdapter();
        rvStarredRepositories.setAdapter(mAdapter);
        rvStarredRepositories.setLayoutManager(new LinearLayoutManager(this));

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.mStarredRepository.observe(this, new Observer<PagedList<StarredRepository>>() {
            @Override
            public void onChanged(@Nullable PagedList<StarredRepository> starredRepositories) {
                mAdapter.submitList(starredRepositories);
            }
        });

    }
}
