package com.netease.nim.uikit;

import android.content.Context;
import android.text.TextUtils;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.api.UIKitOptions;
import com.netease.nim.uikit.business.contact.core.query.PinYin;
import com.netease.nim.uikit.chatroom.ChatRoomSessionHelper;
import com.netease.nim.uikit.config.NIMInitManager;
import com.netease.nim.uikit.config.NimSDKOptionConfig;
import com.netease.nim.uikit.config.UserPreferences;
import com.netease.nim.uikit.net.DemoCache;
import com.netease.nim.uikit.session.NimDemoLocationProvider;
import com.netease.nim.uikit.session.SessionHelper;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.util.NIMUtil;

/**
 * Created by Administrator on 2018/4/24.
 */

public class NimHelper {

    private NimHelper() {
    }

    private static NimHelper nimHelper = null;

    public static NimHelper getInstance() {
        if (nimHelper == null) {
            synchronized (NimHelper.class) {
                if (nimHelper == null) {
                    nimHelper = new NimHelper();
                }
            }

        }
        return nimHelper;
    }

    public void initNim(Context context) {
        DemoCache.setContext(context);

        // 4.6.0 开始，第三方推送配置入口改为 SDKOption#mixPushConfig，旧版配置方式依旧支持。
        NIMClient.init(context, loginInfo(), null);

        // crash handler
//        AppCrashHandler.getInstance(this);

        // 以下逻辑只在主进程初始化时执行
        if (NIMUtil.isMainProcess(context)) {

            // 注册自定义推送消息处理，这个是可选项
//            NIMPushClient.registerMixPushMessageHandler(new DemoMixPushMessageHandler());

            PinYin.init(context);
            PinYin.validate();
            // 初始化UIKit模块
            initUIKit(context);
            // 初始化消息提醒
            NIMClient.toggleNotification(UserPreferences.getNotificationToggle());
            // 云信sdk相关业务初始化
            NIMInitManager.getInstance().init(true);
        }
    }

    private void initUIKit(Context context) {
        // 初始化
        NimUIKit.init(context, buildUIKitOptions(context));

        // 设置地理位置提供者。如果需要发送地理位置消息，该参数必须提供。如果不需要，可以忽略。
        NimUIKit.setLocationProvider(new NimDemoLocationProvider());

        // IM 会话窗口的定制初始化。
        SessionHelper.init();

        // 聊天室聊天窗口的定制初始化。
        ChatRoomSessionHelper.init();

        // 通讯录列表定制初始化
//        ContactHelper.init();

        // 添加自定义推送文案以及选项，请开发者在各端（Android、IOS、PC、Web）消息发送时保持一致，以免出现通知不一致的情况
//        NimUIKit.setCustomPushContentProvider(new DemoPushContentProvider());

//        NimUIKit.setOnlineStateContentProvider(new DemoOnlineStateContentProvider());
    }

    private UIKitOptions buildUIKitOptions(Context context) {
        UIKitOptions options = new UIKitOptions();
        // 设置app图片/音频/日志等缓存目录
        options.appCacheDir = NimSDKOptionConfig.getAppCacheDir(context) + "/app";
        return options;
    }

    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
    private LoginInfo loginInfo() {
        String account = getAccount();
        String token = getToken();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(token)) {
            return null;
        }
        DemoCache.setAccount(account.toLowerCase());
        return new LoginInfo(account, token);
    }

    public String getAccount() {
        return DemoCache.getAccount();
    }

    public String getToken() {
        return DemoCache.getToken();
    }

    public void setAccount(String account) {
        DemoCache.setAccount(account);
    }

    public void setToken(String token) {
        DemoCache.setToken(token);
    }

    public void logout(){
        NIMClient.getService(AuthService.class).logout();
        DemoCache.clear();
    }
}
