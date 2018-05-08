package com.international.shopping.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.qiaoxg.jgpush.JPushHelper;
import com.example.third.UMengHelper;
import com.netease.nim.uikit.NimHelper;
import com.squareup.leakcanary.LeakCanary;

import cn.jpush.android.api.JPushInterface;

public class BaseApplication extends MultiDexApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        //友盟第三方
        UMengHelper.init(this);

        //网易云信
        NimHelper.getInstance().initNim(mContext);

        LeakCanary.install(this);

        //初始化极光推送
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);
        JPushHelper.getInstance().init(this);
    }


    public static Context getContext() {
        return mContext;
    }

}
