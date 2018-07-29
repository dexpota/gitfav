package me.destro.android.gitfav.github;

import java.util.List;

import me.destro.android.gitfav.github.model.StarredRepository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface GithubService {

    @GET("users/{user}/starred")
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    Call<List<StarredRepository>> listStarredRepository(@Path("user") String user);

}
