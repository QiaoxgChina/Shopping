package com.international.shopping.presenter;

import android.app.Activity;
import android.util.Log;

import com.example.third.UMengLogin;
import com.international.shopping.model.User;
import com.international.shopping.util.SharedPreferencesUtil;
import com.international.shopping.api.ITestApi;
import com.international.shopping.api.IUserApi;
import com.international.shopping.api.impl.TestApiImpl;
import com.international.shopping.api.impl.UserApiImpl;
import com.international.shopping.model.GithubApi;
import com.international.shopping.view.iview.ILoginView;
import com.netease.nim.uikit.net.DemoCache;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.friend.FriendService;
import com.netease.nimlib.sdk.friend.model.Friend;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {

    private static final String TAG = "UserPresenter";

    private ILoginView iLoginView;

    private IUserApi iUserApi;

    private ITestApi iTestApi;

    public UserPresenter(ILoginView iView) {
        this.iLoginView = iView;
        iUserApi = new UserApiImpl();
        iTestApi = new TestApiImpl();
    }

    public void userLogin(final String account, final String token) {
        LoginInfo info = new LoginInfo(account, token); // config...
        NIMClient.getService(AuthService.class).login(info)
                .setCallback(new RequestCallback<LoginInfo>() {
                    @Override
                    public void onSuccess(LoginInfo loginInfo) {
                        // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
                        Log.e(TAG, "onSuccess: name is " + loginInfo.getAccount());

                        Friend friend = NIMClient.getService(FriendService.class).getFriendByAccount("123456");
                        if (friend == null) {
                            Log.e(TAG, "onSuccess: friend is null");
                        } else {

                        }

                        iLoginView.loginOk();
                        DemoCache.setAccount(account);
                        saveLoginInfo(account, token);
                    }

                    @Override
                    public void onFailed(int i) {
                        Log.e(TAG, "onFailed: i is " + i);
                        iLoginView.loginFail();
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        Log.e(TAG, "onException: error is " + throwable.getMessage());
                        iLoginView.loginFail();
                    }
                });
    }

    public void thirdUserLogin(Activity activity,SHARE_MEDIA media) {
        UMengLogin.getInstance().login(activity, media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.e(TAG, "onStart: share_media is " + share_media);
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                if (i == 2 && map != null) {
                    User user = new User();
                    switch (share_media){
                        case QQ:
                            user.setName(map.get("name"));
                            user.setGender(map.get("gender"));
                            user.setIconUrl(map.get("iconurl"));
                            user.setWyAccount(map.get("accessToken"));
                            user.setWyToken(map.get("accessToken"));
                            break;
                        case WEIXIN:
                            break;
                        case SINA:
                            break;
                    }
                    SharedPreferencesUtil.saveUser(user);
                    userLogin(user.getWyAccount(), user.getWyToken());
                }else{
                    iLoginView.loginFail();
                }

                Log.e(TAG, "onComplete:  i is " + i + " and map is " + map.toString());
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.e(TAG, "onError: i is " + i);
                iLoginView.loginFail();
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.e(TAG, "onCancel: i is " + i);
                iLoginView.loginFail();
            }
        });
    }

    private void saveLoginInfo(final String account, final String token) {
        SharedPreferencesUtil.saveUserAccount(account);
        SharedPreferencesUtil.saveUserToken(token);
    }

    public void userRegister(String username, String password) {
//        ContactHttpClient.getInstance().register(username, "qiaoxg", password, new ContactHttpClient.ContactHttpCallback<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Log.e(TAG, "====================userRegister onSuccess: name is " + aVoid.toString());
//            }
//
//            @Override
//            public void onFailed(int code, String errorMsg) {
//                Log.e(TAG, "=======================userRegister onFailed: name is " + errorMsg);
//            }
//        });
    }

    public void getGithubApi() {
        iTestApi.getGithubApi(new Callback<GithubApi>() {

            @Override
            public void onResponse(Call<GithubApi> call, Response<GithubApi> response) {
                Log.i(TAG, "onResponse: hub_url is " + response.body().getHub_url());
                iLoginView.loginOk();
            }

            @Override
            public void onFailure(Call<GithubApi> call, Throwable t) {
                iLoginView.loginFail();
            }
        });
    }
}
