package com.international.shopping.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.international.shopping.base.BaseActivity;
import com.international.shopping.R;
import com.international.shopping.presenter.UserPresenter;
import com.international.shopping.view.MainActivity;
import com.international.shopping.view.iview.ILoginView;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

public class LoginActivity extends BaseActivity implements ILoginView {

    private static final String TAG = "LoginActivity";

    private UserPresenter mPresenter;

    private Button loginBtn, registerBtn;
    private EditText usernameEt, passwordEt;

    private ProgressDialog mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.login_btn);
        usernameEt = (EditText) findViewById(R.id.username_et);
        passwordEt = (EditText) findViewById(R.id.password_et);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoadingView.show();
                String username = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();
                mPresenter.userLogin(username, password);
            }
        });
        mPresenter = new UserPresenter(this);
        registerBtn = (Button) findViewById(R.id.register_btn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEt.getText().toString();
                String password = passwordEt.getText().toString();
                mPresenter.userRegister(username, password);
            }
        });

        findViewById(R.id.weixin_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingView.show();
                mPresenter.thirdUserLogin(LoginActivity.this, SHARE_MEDIA.WEIXIN);
                Toast.makeText(LoginActivity.this, "微信登录", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.weibo_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingView.show();
                mPresenter.thirdUserLogin(LoginActivity.this, SHARE_MEDIA.SINA);
                Toast.makeText(LoginActivity.this, "微博登录", Toast.LENGTH_LONG).show();
            }
        });

        findViewById(R.id.qq_ll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingView.show();
                mPresenter.thirdUserLogin(LoginActivity.this, SHARE_MEDIA.QQ);
                Toast.makeText(LoginActivity.this, "QQ登录", Toast.LENGTH_LONG).show();
            }
        });

        initLoadingView();
    }

    private void initLoadingView(){
        mLoadingView = new ProgressDialog(this);
        mLoadingView.setMessage("正在登录");
        mLoadingView.setCancelable(false);
        mLoadingView.setCanceledOnTouchOutside(false);
    }

    @Override
    public void loginOk() {
        goToMainActivity();
        mLoadingView.dismiss();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        mLoadingView.dismiss();
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}
