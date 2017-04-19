package com.globant.practice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the user repository.
 * Created by jonathan.vargas on 5/04/2017.
 */

public final class Repository {

    private final String name;
    @SerializedName("html_url")
    private final String htmlUrl;

    public Repository(String name, String htmlUrl) {
        this.name = name;
        this.htmlUrl = htmlUrl;
    }

    public String getName() {
        return name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }
}