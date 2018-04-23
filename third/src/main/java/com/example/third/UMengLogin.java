package com.example.third;

import android.app.Activity;
import android.content.Context;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;

/**
 * Created by Administrator on 2018/4/16.
 */

public class UMengLogin {

    private static UMengLogin mInstance = null;

    private UMShareAPI mShareAPI;

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

    public void init(Context context) {
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
//        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;
        mShareAPI = UMShareAPI.get(context);

        UMShareConfig config = new UMShareConfig();
//        config.isNeedAuthOnGetUserInfo(true);
        mShareAPI.setShareConfig(config);
        UMConfigure.init(context, "5ad40a29a40fa3263f000058"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

        PlatformConfig.setQQZone("1106844708", "hkAFoKXGa8vG7ST9");
    }

    public void login(Activity context, SHARE_MEDIA platform, UMAuthListener listener) {
        mShareAPI.getPlatformInfo(context, platform, listener);
    }

}
