package com.international.baselib.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.international.baselib.dialog.callback.ConfirmCallback;

/**
 * Created by Administrator on 2018/4/17.
 */

public class ConfirmDialog {

    /**
     * app退出确认弹框
     *
     * @param activity
     */
    public static void ExitApp(final Activity activity) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle("提示");
        dialog.setMessage("确认退出app？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
            }
        });
//        dialog.setCancelable(false);
        dialog.setNegativeButton("再玩一会", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 注销登录确认弹框
     *
     * @param activity
     */
    public static void LogoutApp(Activity activity, final ConfirmCallback callback) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle("提示");
        dialog.setMessage("确认注销当前用户？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onResult(true, null);
            }
        });
//        dialog.setCancelable(false);
        dialog.setNegativeButton("再玩一会", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 删除object确认弹框
     *
     * @param obj
     * @param activity
     * @param callback
     */
    public static void DeleteObject(final Object obj, final Activity activity, final ConfirmCallback callback) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setTitle("提示");
        dialog.setMessage("确认删除？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onResult(true, obj);
            }
        });
//        dialog.setCancelable(false);
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
