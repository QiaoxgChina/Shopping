package com.international.shopping.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.international.shopping.R;
import com.international.shopping.base.BaseActivity;
import com.international.shopping.util.SharedPreferencesUtil;
import com.international.shopping.view.MainActivity;
import com.netease.nim.uikit.NimHelper;
import com.netease.nim.uikit.net.DemoCache;

public class SplashActivity extends BaseActivity {

    private static int DELAYED_TIME = 5;

    private static final int MSG_SKIP_TIP = 1;
    private static final int MSG_TO_SKIP = 2;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SKIP_TIP:

                    timeTv.setText(String.format(getResources().getString(R.string.splash_time), DELAYED_TIME + ""));
                    DELAYED_TIME--;
                    if (DELAYED_TIME < 0) {
                        removeMsg();
                        mHandler.sendEmptyMessage(MSG_TO_SKIP);
                    } else {
                        mHandler.sendEmptyMessageDelayed(MSG_SKIP_TIP, 1000);
                    }
                    break;
                case MSG_TO_SKIP:
                    if (!TextUtils.isEmpty(NimHelper.getInstance().getAccount()) && !TextUtils.isEmpty(NimHelper.getInstance().getToken())) {
                        skipPlace(MainActivity.class);
                    } else {
                        skipPlace(LoginActivity.class);
                    }
                    break;
            }
        }
    };

    private TextView timeTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        timeTv = (TextView) findViewById(R.id.skip_tv);
        timeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMsg();
                mHandler.sendEmptyMessage(MSG_TO_SKIP);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.sendEmptyMessageDelayed(MSG_SKIP_TIP, DELAYED_TIME >= 4 ? 0 : 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        removeMsg();
    }

    private void skipPlace(Class activityClass) {
        startActivity(new Intent(this, activityClass));
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeMsg();
        DELAYED_TIME = 5;
        mHandler = null;
    }

    private void removeMsg() {
        if (mHandler.hasMessages(MSG_SKIP_TIP)) {
            mHandler.removeMessages(MSG_SKIP_TIP);
        }
        if (mHandler.hasMessages(MSG_TO_SKIP)) {
            mHandler.removeMessages(MSG_TO_SKIP);
        }
    }
}
