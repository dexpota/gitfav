package me.destro.android.libraries.github.model;

import com.squareup.moshi.Json;

@SuppressWarnings("unused")
public class Owner {
    @Json(name = "login")
    public String login;
}