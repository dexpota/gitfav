package me.destro.android.gitfav;

import android.app.Application;

import com.squareup.moshi.Moshi;

import me.destro.android.libraries.github.GithubService;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class GitfavApplication extends Application {

    public static GithubService githubService;

    @Override
    public void onCreate() {
        super.onCreate();

        String BASE_URL = "https://api.github.com";

        Moshi moshi = new Moshi.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        githubService = retrofit.create(GithubService.class);
    }
}
