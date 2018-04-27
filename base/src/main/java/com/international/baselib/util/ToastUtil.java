package com.international.baselib.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    private static Toast mToast;

    /**
     * 显示toast
     *
     * @param tip     string提示
     * @param context
     */
    public static void showTip(String tip, Context context) {
        if (mToast == null) {
            mToast = Toast.makeText(context, tip, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(tip);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    /**
     * 显示toast
     *
     * @param tipId   string资源id
     * @param context
     */
    public static void showTip(int tipId, Context context) {
        if (mToast == null) {
            mToast = Toast.makeText(context, tipId, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(tipId);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
