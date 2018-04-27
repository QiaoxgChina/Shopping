package com.international.shopping.view.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.international.shopping.R;
import com.international.shopping.base.BaseActivity;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
//                overridePendingTransition(R.anim.animation_activity_in,R.anim.animation_activity_out);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
//            overridePendingTransition(R.anim.animation_activity_in,R.anim.animation_activity_out);
        }
        return true;
    }
}
