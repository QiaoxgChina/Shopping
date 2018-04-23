package com.example.third;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2018/4/20.
 */

public class UMengShare {

    private static final String TAG = "UMengShare";

    public static void showShareView(View antor, final Activity context) {

//        AlertDialog.Builder ad = new AlertDialog.Builder(context);
//        ad.setView(R.layout.layout_share_view);
//        ad.show();
//        PopupWindow pw = new PopupWindow();
//        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_share_view,null);
//        pw.setContentView(rootView);
//        pw.showAsDropDown(antor);

        new ShareAction(context)
                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
                .withText("umeng share test")
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Log.e(TAG, "onResult: 分享成功");
                        Toast.makeText(context, "分享成功", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Log.e(TAG, "onError: 分享失败");
                        Toast.makeText(context, "分享失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Log.e(TAG, "onCancel: 分享取消");
                        Toast.makeText(context, "分享取消", Toast.LENGTH_LONG).show();
                    }
                }).share();//回调监听器


    }
}
