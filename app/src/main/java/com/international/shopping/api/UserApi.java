package com.international.shopping.api;

import com.international.baselib.net.CustomResult;
import com.international.shopping.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("")
    Call<User> getUserInfo(@Field("id") String userId);

    @POST("")
    Call<User> login(@Field("name")String name,@Field("password")String password);
}
