package com.example.qiaoxg.jgpush;

import android.content.Context;

import cn.jpush.android.api.JPushInterface;

public class JPushHelper {

    private static JPushHelper mInstance = null;

    private JPushHelper() {
    }

    public static JPushHelper getInstance() {
        if (mInstance == null) {
            synchronized (JPushHelper.class) {
                if (mInstance == null) {
                    mInstance = new JPushHelper();
                }
            }
        }

        return mInstance;
    }

    /**
     * 初始化极光推送
     *
     * @param context
     */
    public void init(Context context) {
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(context);            // 初始化 JPush
    }

    /**
     * 设置停止接收推送消息
     *
     * @param context
     */
    public void stopPush(Context context) {
        JPushInterface.stopPush(context);
    }

    /**
     * 设置可以接收推送消息
     *
     * @param context
     */
    public void startGetPush(Context context) {
        JPushInterface.resumePush(context);
    }

}
