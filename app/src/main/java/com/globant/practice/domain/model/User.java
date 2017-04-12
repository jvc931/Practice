package com.globant.practice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the user.
 * Created by jonathan.vargas on 3/04/2017.
 */

public final class User {

    private final String login;
    @SerializedName("avatar_url")
    private final String avatarUrl;

    public User(String login, String avatarUrl) {
        this.login = login;
        this.avatarUrl = avatarUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
