package me.destro.android.gitfav;

import android.arch.core.util.Function;
import android.arch.paging.LivePagedListBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.destro.android.gitfav.adapters.StarredRepositoriesAdapter;
import me.destro.android.gitfav.github.model.StarredRepository;
import me.destro.android.gitfav.utilities.Algorithms;
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

        final Call<List<StarredRepository>> starredCall = GitfavApplication.githubService.listStarredRepository("dexpota");

        starredCall.enqueue(new Callback<List<StarredRepository>>() {
            @Override
            public void onResponse(Call<List<StarredRepository>> call, Response<List<StarredRepository>> response) {
                if(response.isSuccessful()) {
                    List<StarredRepository> starredRepositories = response.body();

                    Log.d(MainActivity.class.getName(), response.headers().get("Link"));

                    for (StarredRepository starredRepository : starredRepositories) {
                        Log.d(MainActivity.class.getName(), starredRepository.name);
                        Log.d(MainActivity.class.getName(), starredRepository.fullName);

                        Algorithms.Consumer<String> tConsumer = topic -> Log.d(MainActivity.class.getName(), "topic: " + topic);
                        Algorithms.foreach(starredRepository.topics, tConsumer);
                    }

                    StarredRepositoriesAdapter adapter = new StarredRepositoriesAdapter(starredRepositories);
                    rvStarredRepositories.setAdapter(adapter);
                    rvStarredRepositories.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
            }

            @Override
            public void onFailure(Call<List<StarredRepository>> call, Throwable t) {

            }
        });
    }
}
