package me.destro.android.gitfav.paging;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.destro.android.gitfav.GitfavApplication;
import me.destro.android.gitfav.MainActivity;
import me.destro.android.gitfav.github.model.StarredRepository;
import me.destro.android.gitfav.github.utilities.PageLinks;
import me.destro.android.gitfav.utilities.Algorithms;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StarredRepositoryDataSource extends PageKeyedDataSource<String, StarredRepository> {

    private String username;

    public StarredRepositoryDataSource(String username) {
        this.username = username;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, StarredRepository> callback) {
        Call<List<StarredRepository>> starredCall = GitfavApplication.githubService.listStarredRepository(this.username, 0);
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

                    PageLinks pageLinks = new PageLinks(response.headers());

                    callback.onResult(starredRepositories, pageLinks.getPrev(), pageLinks.getNext());
                }
            }

            @Override
            public void onFailure(Call<List<StarredRepository>> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, StarredRepository> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, StarredRepository> callback) {

        Pattern p = Pattern.compile("page=(\\d+).*$");
        Matcher m = p.matcher(params.key);
        Integer next = 0;
        if (m.find()) {
            next = Integer.valueOf(m.group(1));
        }

        Call<List<StarredRepository>> starredCall = GitfavApplication.githubService.listStarredRepository(this.username, next);

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


                    //StarredRepositoriesAdapter adapter = new StarredRepositoriesAdapter(starredRepositories);
                    //rvStarredRepositories.setAdapter(adapter);
                    //rvStarredRepositories.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    PageLinks pageLinks = new PageLinks(response.headers());
                    callback.onResult(starredRepositories, pageLinks.getNext());
                }
            }

            @Override
            public void onFailure(Call<List<StarredRepository>> call, Throwable t) {

            }
        });
    }
}
