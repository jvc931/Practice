package com.globant.practice.domain.model;

/**
 * Represents the user profile.
 * Created by jonathan.vargas on 5/04/2017.
 */

public final class Profile {
    private final String login;
    private final String avatarUrl;
    private final String htmlUrl;
    private final String name;
    private final String company;
    private final String location;
    private final int followers;
    private final int following;
    private final int publicRepos;

    public Profile(String login, String avatarUrl, String htmlUrl, String name,
                   String company, String location, int followers, int following,
                   int publicRepos) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.name = name;
        this.company = company;
        this.location = location;
        this.followers = followers;
        this.following = following;
        this.publicRepos = publicRepos;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public int getPublicRepos() {
        return publicRepos;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }
}
