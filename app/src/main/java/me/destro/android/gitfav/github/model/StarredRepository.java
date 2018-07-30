package me.destro.android.gitfav.github.model;

import com.squareup.moshi.Json;

public class StarredRepository {
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
    @Json(name = "topics")
    public String[] topics;
}
