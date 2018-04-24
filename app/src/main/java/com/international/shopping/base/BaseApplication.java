package com.international.shopping.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.example.third.UMengHelper;
import com.netease.nim.uikit.NimHelper;

//import com.netease.nimlib.sdk.NIMClient;

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
    }


    public static Context getContext() {
        return mContext;
    }

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

}
