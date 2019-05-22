package me.destro.android.libraries.github.model;

import com.squareup.moshi.Json;

@SuppressWarnings("unused")
public class Repository {
    @Json(name = "id")
    public String id;
    @Json(name = "name")
    public String name;
    @Json(name = "full_name")
    public String fullName;
    @Json(name = "html_url")
    public String htmlUrl;
    @Json(name = "description")
    public String description;
    @Json(name = "forks_count")
    public int forksCount;
    @Json(name = "stargazers_count")
    public int stargazersCount;
    @Json(name = "watchers_count")
    public int watchersCount;
    @Json(name = "topics")
    public String[] topics;
    @Json(name = "owner")
    public Owner owner;
}
