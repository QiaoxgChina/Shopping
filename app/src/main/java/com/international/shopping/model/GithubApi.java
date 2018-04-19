package com.international.shopping.model;

import com.international.baselib.net.CustomResult;

public class GithubApi{

    private String hub_url;
    private String keys_url;
    private String issues_url;

    public String getHub_url() {
        return hub_url;
    }

    public void setHub_url(String hub_url) {
        this.hub_url = hub_url;
    }

    public String getKeys_url() {
        return keys_url;
    }

    public void setKeys_url(String keys_url) {
        this.keys_url = keys_url;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }
}
