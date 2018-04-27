package com.international.shopping.view;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.international.baselib.base.BaseViewPager;
import com.international.baselib.dialog.ConfirmDialog;
import com.international.baselib.view.BaseViewPagerAdapter;
import com.international.shopping.R;
import com.international.shopping.base.BaseActivity;
import com.international.shopping.event.SwitchMainTabEvent;
import com.international.shopping.view.fragment.CarFragment;
import com.international.shopping.view.fragment.DiscoverFragment;
import com.international.shopping.view.fragment.HomeFragment;
import com.international.shopping.view.fragment.MessageFragment;
import com.international.shopping.view.fragment.MineFragment;
import com.netease.nim.uikit.support.permission.MPermission;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionDenied;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionGranted;
import com.netease.nim.uikit.support.permission.annotation.OnMPermissionNeverAskAgain;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private BaseViewPager mViewPager;
    private TabLayout mTableLayout;
    private BaseViewPagerAdapter mViewPagerAdapter;
    private String[] TAB_TITLES;
    private int[] TAB_IMGS;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private static final int BASIC_PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);

        View mainView = findViewById(R.id.main_layout);
        mainView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        initData();
        initView();

        requestBasicPermission();
    }

    /**
     * 基本权限管理
     */
    private final String[] BASIC_PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    private void requestBasicPermission() {
        MPermission.printMPermissionResult(true, this, BASIC_PERMISSIONS);
        MPermission.with(MainActivity.this)
                .setRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(BASIC_PERMISSIONS)
                .permissions(BASIC_PERMISSIONS)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
        try {
            Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MPermission.printMPermissionResult(false, this, BASIC_PERMISSIONS);
    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    @OnMPermissionNeverAskAgain(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        try {
            Toast.makeText(this, "未全部授权，部分功能可能无法正常运行！", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        MPermission.printMPermissionResult(false, this, BASIC_PERMISSIONS);
    }

    private void initData() {
        TAB_TITLES = new String[]{"主页", "发现", "购物车", "我的"};

        mFragmentList.add(HomeFragment.newInstance(TAB_TITLES[0], ""));
        mFragmentList.add(DiscoverFragment.newInstance(TAB_TITLES[1], ""));
//        mFragmentList.add(MessageFragment.newInstance(TAB_TITLES[2], ""));
        mFragmentList.add(CarFragment.newInstance(TAB_TITLES[2], ""));
        mFragmentList.add(MineFragment.newInstance(TAB_TITLES[3], ""));

        TAB_IMGS = new int[]{R.drawable.selector_tab_img_main,
                R.drawable.selector_tab_img_discover,
                R.drawable.selector_tab_img_message,
                R.drawable.selector_tab_img_car,
                R.drawable.selector_tab_img_mine};
    }

    private void initView() {
        mViewPager = (BaseViewPager) findViewById(R.id.main_viewPager);
        mTableLayout = (TabLayout) findViewById(R.id.main_tabLayout);
        mTableLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTableLayout));
        mViewPagerAdapter = new BaseViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setCanScroll(false);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setTab();
    }

    private void setTab() {
        for (int i = 0; i < TAB_TITLES.length; i++) {
            TabLayout.Tab tab = mTableLayout.newTab();
            View view = this.getLayoutInflater().inflate(R.layout.view_main_tab, null);
            tab.setCustomView(view);

            TextView tabTitle = view.findViewById(R.id.tab_title);
            tabTitle.setText(TAB_TITLES[i]);

            ImageView iv = view.findViewById(R.id.tab_img);
            iv.setImageResource(TAB_IMGS[i]);

            mTableLayout.addTab(tab);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //注销EventBus
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ConfirmDialog.ExitApp(MainActivity.this);
        }
        return true;
    }

    /**
     * 处理EventBus事件
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchMainTabEvent(SwitchMainTabEvent event) {
        if (event != null) {
            mViewPager.setCurrentItem(event.getTargetFragment());
        }
    }

}
