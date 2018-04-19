package com.international.shopping.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.international.shopping.util.ActivityUtil;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUtil.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityUtil.getInstance().removeActivity(this);
    }

}
