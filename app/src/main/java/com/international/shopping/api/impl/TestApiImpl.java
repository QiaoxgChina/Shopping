package com.international.shopping.api.impl;

import com.international.baselib.net.CustomCallback;
import com.international.baselib.net.RetrofitManager;
import com.international.shopping.api.ITestApi;
import com.international.shopping.api.TestApi;

import retrofit2.Callback;

public class TestApiImpl implements ITestApi {
    @Override
    public void getGithubApi(Callback callback) {
        RetrofitManager.getInstance().create(TestApi.class).getGithubApi().enqueue(callback);
    }
}
