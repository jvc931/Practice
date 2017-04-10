package com.globant.practice.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Represents the user repository.
 * Created by jonathan.vargas on 5/04/2017.
 */

public class Repository {
    @SerializedName("name")
    private String name;

    @SerializedName("html_url")
    private String htmlUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }
}