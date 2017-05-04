package com.globant.practice.presentation.model;

/**
 * Manages the states of the WebClientFragment
 * Created by jonathan.vargas on 4/05/2017.
 */

public class WebClientState {
    private String htmlUrl;
    private String detailType;
    private boolean loading;

    /**
     * Sets the new value of the htmlUrl
     *
     * @param htmlUrl new value of the htmlUrl
     */
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    /**
     * Sets the new value of the detailType
     *
     * @param detailType new value of the detailType
     */
    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    /**
     * Sets the new value of the loading boolean
     *
     * @param loading new value of the loading boolean
     */
    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    /**
     * Gets the address of the web page that will be load
     *
     * @return web page address
     */
    public String getHtmlUrl() {
        return htmlUrl;
    }

    /**
     * Gets the type of the web page (Profile or Repository)
     *
     * @return the type of the web page
     */
    public String getDetailType() {
        return detailType;
    }

    /**
     * Indicates if the ProgressDialog is showing or not
     *
     * @return true if the ProgressDialog is showing if not return false
     */
    public boolean isLoading() {
        return loading;
    }
}
