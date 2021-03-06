package com.international.shopping.view.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.third.UMengShare;
import com.example.third.bean.ShareBean;
import com.international.baselib.dialog.ConfirmDialog;
import com.international.baselib.dialog.callback.ConfirmCallback;
import com.international.shopping.R;
import com.international.shopping.base.BaseActivity;
import com.international.shopping.model.User;
import com.international.shopping.util.ActivityUtil;
import com.international.shopping.util.SharedPreferencesUtil;
import com.international.shopping.view.MainActivity;
import com.netease.nim.uikit.NimHelper;
import com.netease.nim.uikit.net.DemoCache;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.umeng.socialize.UMShareAPI;

import java.lang.reflect.Field;

public class SettingActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        findViewById(R.id.logout_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfirmDialog.LogoutApp(SettingActivity.this, new ConfirmCallback() {
                    @Override
                    public void onResult(boolean isConfirm, Object obj) {
                        if (isConfirm) {
                            //清楚所有activity
                            ActivityUtil.getInstance().clearActivityTask();

                            NimHelper.getInstance().logout();
                            SharedPreferencesUtil.saveUser(null);
                            startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                            finish();
                        }
                    }
                });
            }
        });

        findViewById(R.id.back_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.clear_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.setName("Qiaoxg");
                ConfirmDialog.DeleteObject(u, SettingActivity.this, new ConfirmCallback() {
                    @Override
                    public void onResult(boolean isConfirm, Object obj) {
                        if (obj instanceof User) {
                            User deleteUser = (User) obj;
                            Toast.makeText(SettingActivity.this, deleteUser.getName() + " 已删除", Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }
        });

        findViewById(R.id.share_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareBean bean = new ShareBean();
                bean.setContent("Shopping分享测试内容");
                bean.setTitle("分享标题");
                UMengShare.showShareView(SettingActivity.this, bean);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
