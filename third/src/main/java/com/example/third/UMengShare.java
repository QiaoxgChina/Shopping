package com.example.third;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.third.bean.ShareBean;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.UmengText;

/**
 * Created by Administrator on 2018/4/20.
 */

public class UMengShare {

    private static final String TAG = "UMengShare";

    private static void share(Activity context, ShareBean bean, SHARE_MEDIA MEDIA) {

        ShareAction sa = new ShareAction(context);
        sa.setPlatform(MEDIA);
        sa.withText(bean.getContent());
        if (MEDIA == SHARE_MEDIA.QQ) {
            UMImage image = new UMImage(context, R.drawable.label_qq);//资源文件
            sa.withMedia(image);
        }

        sa.setCallback(new ShareListener(context));
        sa.share();
    }

    public static void showShareView(final Activity context, final ShareBean bean) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.layout_share_view, null);

        final BottomSheetDialog bsd = new BottomSheetDialog(context);
        bsd.setCancelable(true);//设置点击外部是否可以取消
        bsd.setContentView(rootView);//设置对框框中的布局

        rootView.findViewById(R.id.share_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(context, bean, SHARE_MEDIA.QQ);
                bsd.dismiss();
            }
        });

        rootView.findViewById(R.id.share_qqZone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(context, bean, SHARE_MEDIA.QZONE);
                bsd.dismiss();
            }
        });

        rootView.findViewById(R.id.share_weixin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(context, bean, SHARE_MEDIA.WEIXIN);
                bsd.dismiss();
            }
        });

        rootView.findViewById(R.id.share_weibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(context, bean, SHARE_MEDIA.SINA);
                bsd.dismiss();
            }
        });

        rootView.findViewById(R.id.share_friend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(context, bean, SHARE_MEDIA.WEIXIN_CIRCLE);
                bsd.dismiss();
            }
        });
        bsd.show();
    }

    private static class ShareListener implements UMShareListener {

        private Context mContext;

        public ShareListener(Context context) {
            this.mContext = context;
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Log.e(TAG, "onResult: 分享成功");
            Toast.makeText(mContext, "分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Log.e(TAG, "onError: 分享失败");
            Toast.makeText(mContext, "分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Log.e(TAG, "onCancel: 分享取消");
            Toast.makeText(mContext, "分享取消", Toast.LENGTH_SHORT).show();
        }
    }

}
