package me.destro.android.libraries.github;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import me.destro.android.libraries.github.model.Repository;
import me.destro.android.libraries.github.model.StarredRepository;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


@SuppressWarnings("unused")
public interface GithubService {

    /**
     * @link https://developer.github.com/v3/activity/starring/#list-repositories-being-starred
     */
    @GET("users/{user}/starred")
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    Single<Response<List<StarredRepository>>> listStarredRepository(@Path("user") String user, @Query("page") int page);

    /**
     * @link https://developer.github.com/v3/repos/#get
     */
    @GET("/repos/{owner}/{repo}")
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    Single<Response<List<Repository>>> getRepository(@Path("user") String user, @Path("repo") String repository);

    @GET("/repos/{user}/{repo}/languages")
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    Single<Map<String, Integer>> listRepositoryLanguages(@Path("user") String user, @Path("repo") String repository);

    @GET("/repos/{user}/{repo}/topics")
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    Single<Map<String, Integer>> listRepositoryTopics(@Path("user") String user, @Path("repo") String repository);

}
