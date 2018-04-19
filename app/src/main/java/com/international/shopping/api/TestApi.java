package com.international.shopping.api;

import com.international.shopping.model.GithubApi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface TestApi {
    @GET("/")
    Call<GithubApi> getGithubApi();
}
