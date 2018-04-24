package com.international.shopping.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.international.shopping.base.BaseApplication;
import com.international.shopping.model.User;


/**
 * Created by Administrator on 2018/4/11.
 */

public class SharedPreferencesUtil {

    private static final String KEY_USER = "user";

    public static User getUser(){
        String userStr = getString(KEY_USER);
        if(TextUtils.isEmpty(userStr)){
            return null;
        }
        return new Gson().fromJson(userStr, User.class);
    }

    public static void saveUser(User user){
        String userStr = new Gson().toJson(user);
        saveString(KEY_USER,userStr);
    }

    private static void saveString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static String getString(String key) {
        return getSharedPreferences().getString(key, null);
    }

    static SharedPreferences getSharedPreferences() {
        return BaseApplication.getContext().getSharedPreferences("Shopping", Context.MODE_PRIVATE);
    }
}
