package com.international.shopping.view.activity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.international.shopping.R;
import com.international.shopping.base.BaseActivity;


public class AboutUsActivity extends BaseActivity {

    private TextView versionTv;

    @SuppressLint("StringFormatInvalid")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abour_us);
        findViewById(R.id.back_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        versionTv = (TextView) findViewById(R.id.version_tv);
        versionTv.setText(String.format(getResources().getString(R.string.about_version),getAppVersion()));
    }

    private String getAppVersion(){
        String verName = "";
        try {
            verName = getApplicationContext().getPackageManager().
                    getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }


}
