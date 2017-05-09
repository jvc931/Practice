package com.globant.practice.presentation.model;

/**
 * Manages the states of the WebClientFragment
 * Created by jonathan.vargas on 4/05/2017.
 */

public class WebClientState {
    private String htmlUrl;
    private String detailType;
    private boolean loading;
    private String error;
    private boolean errorShowing;

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
     * Sets the new value of the error message
     *
     * @param error new value of error message
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Sets the new value of the errorShowing boolean
     *
     * @param erroShowing new value of the errorShowing boolean
     */
    public void setErrorShowing(boolean erroShowing) {
        this.errorShowing = erroShowing;
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

    /**
     * Gets the error message
     *
     * @return error message
     */
    public String getError() {
        return error;
    }

    /**
     * Indicates if the error message is  showing or not
     *
     * @return true if the error message is showing if not return false
     */
    public boolean isErrorShowing() {
        return errorShowing;
    }
}
