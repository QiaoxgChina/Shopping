package com.example.third;

import android.content.Context;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.common.QueuedWork;

/**
 * Created by Administrator on 2018/4/23.
 */

public class UMengHelper {

    public static void init(Context context) {
        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
//        Config.DEBUG = true;
        QueuedWork.isUseThreadPool = false;

        UMConfigure.init(context, "5ad40a29a40fa3263f000058"
                , "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");

        PlatformConfig.setQQZone("1106844708", "hkAFoKXGa8vG7ST9");

        PlatformConfig.setWeixin("wx414ed93bf88dfcb5", "01eef456d7b83edafa325f3c4d00c32a");

        PlatformConfig.setSinaWeibo("2523723774","808a9f89f35bdfe4a87d4f43167e66a1","");


    }

}
