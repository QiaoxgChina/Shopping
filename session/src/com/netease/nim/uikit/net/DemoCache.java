package com.netease.nim.uikit.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

//import com.netease.nim.avchatkit.AVChatKit;
//import com.netease.nim.rtskit.RTSKit;
import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;

/**
 * Created by jezhee on 2/20/15.
 */
public class DemoCache {

    private static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    private static final String KEY_TOKEN = "KEY_TOKEN";

    private static Context context;

    private static String account;

    private static String token;

    private static StatusBarNotificationConfig notificationConfig;

    public static void clear() {
        account = null;
        token = null;
    }

    public static String getToken() {
        if(TextUtils.isEmpty(token)){
            DemoCache.token = getString(KEY_TOKEN);
        }

        return token;
    }

    public static void setToken(String token) {
        DemoCache.token = token;

        saveString(KEY_TOKEN, token);
    }

    public static String getAccount() {
        if(TextUtils.isEmpty(account)){
            DemoCache.account = getString(KEY_ACCOUNT);
        }

        return account;
    }

    private static boolean mainTaskLaunching;

    public static void setAccount(String account) {
        DemoCache.account = account;
        NimUIKit.setAccount(account);
//        AVChatKit.setAccount(account);
//        RTSKit.setAccount(account);
        saveString(KEY_ACCOUNT, account);
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
        return context.getSharedPreferences("Shopping", Context.MODE_PRIVATE);
    }

    public static void setNotificationConfig(StatusBarNotificationConfig notificationConfig) {
        DemoCache.notificationConfig = notificationConfig;
    }

    public static StatusBarNotificationConfig getNotificationConfig() {
        return notificationConfig;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DemoCache.context = context.getApplicationContext();

//        AVChatKit.setContext(context);
//        RTSKit.setContext(context);
    }

    public static void setMainTaskLaunching(boolean mainTaskLaunching) {
        DemoCache.mainTaskLaunching = mainTaskLaunching;

//        AVChatKit.setMainTaskLaunching(mainTaskLaunching);
    }

    public static boolean isMainTaskLaunching() {
        return mainTaskLaunching;
    }
}
