package com.international.shopping.api;

import com.international.baselib.net.CustomCallback;

public interface IUserApi {
    /**
     * 获取用户信息
     * @param id
     * @param callback
     */
    void getUserInfo(String id, CustomCallback callback);

    /**
     * 登录
     * @param name
     * @param password
     * @param callback
     */
    void login(String name,String password, CustomCallback callback);
}
