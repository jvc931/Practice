package com.globant.practice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the user.
 * Created by jonathan.vargas on 3/04/2017.
 */

public class User {
    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


}
