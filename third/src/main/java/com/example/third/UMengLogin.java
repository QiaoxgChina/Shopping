package com.example.third;

import android.app.Activity;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2018/4/16.
 */

public class UMengLogin {

    private static UMengLogin mInstance = null;

    private UMengLogin() {
    }

    public static UMengLogin getInstance() {
        if (mInstance == null) {
            synchronized (UMengLogin.class) {
                if (mInstance == null) {
                    mInstance = new UMengLogin();
                }
            }
        }
        return mInstance;
    }

    public void login(Activity context, SHARE_MEDIA platform, UMAuthListener listener) {
        UMShareAPI.get(context).getPlatformInfo(context, platform, listener);
    }

}
