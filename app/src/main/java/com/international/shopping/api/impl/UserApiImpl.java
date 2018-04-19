package com.international.shopping.api.impl;

import com.international.baselib.net.CustomCallback;
import com.international.baselib.net.RetrofitManager;
import com.international.shopping.api.IUserApi;
import com.international.shopping.api.UserApi;

public class UserApiImpl implements IUserApi {

    @Override
    public void getUserInfo(String id, CustomCallback callback) {
        RetrofitManager.getInstance()
                .create(UserApi.class)
                .getUserInfo(id)
                .enqueue(callback);
    }

    @Override
    public void login(String name, String password, CustomCallback callback) {
        RetrofitManager.getInstance()
                .create(UserApi.class)
                .login(name,password)
                .enqueue(callback);
    }
}
