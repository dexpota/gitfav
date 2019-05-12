package me.destro.android.libraries.github;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import me.destro.android.libraries.github.model.StarredRepository;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("users/{user}/starred")
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    Single<Response<List<StarredRepository>>> listStarredRepository(@Path("user") String user, @Query("page") int page);

    @GET("/repos/:owner/:repo/languages")
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    Single<Map<String, Integer>> listRepositoryLanguages(@Path("user") String user, @Path("repo") String repository);
}
