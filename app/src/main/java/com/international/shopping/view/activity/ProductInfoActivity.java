package com.international.shopping.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.international.baselib.base.BaseViewPager;
import com.international.baselib.view.BaseViewPagerAdapter;
import com.international.baselib.view.MyScrollView;
import com.international.shopping.R;
import com.international.shopping.view.fragment.detail.DetailFragment;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ProductInfoActivity extends AppCompatActivity implements MyScrollView.OnScrollListener {

    private static final String TAG = "ProductInfoActivity";

    private static String PARAM_PRODUCT_ID = "";

    /**
     * 启动这个页面
     *
     * @param productId
     * @param activity
     */
    public static void start(String productId, Activity activity) {
        Intent i = new Intent(activity, ProductInfoActivity.class);
        i.putExtra(PARAM_PRODUCT_ID, productId);
        activity.startActivity(i);
    }

    private MyScrollView mScrollView;
    private LinearLayout ll_tab;
    private int picBottom;

    private Banner mBanner;
    private TabLayout mTabLayout;
    private BaseViewPager mViewPager;
    private BaseViewPagerAdapter mViewPagerAdapter;
    private View productView;
    private View titleView;

    private String[] TAB_TITLES;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        mScrollView = findViewById(R.id.mScrollView);
        mScrollView.setOnScrollListener(this);
        ll_tab = findViewById(R.id.ll_tab);
        findViewById(R.id.ll_main).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(mScrollView.getScrollY());
            }
        });

        productView = findViewById(R.id.product_info);
        titleView = findViewById(R.id.rl_title);

        initBannerData();
        initBanner();

        initTabData();
        initTabLayout();
    }

    private void initTabData() {
        TAB_TITLES = new String[]{"详情", "评价", "推荐"};

        mFragmentList.add(DetailFragment.newInstance(TAB_TITLES[0]));
        mFragmentList.add(DetailFragment.newInstance(TAB_TITLES[1]));
        mFragmentList.add(DetailFragment.newInstance(TAB_TITLES[2]));
    }

    private void initTabLayout() {
        mTabLayout = findViewById(R.id.detailInfo_tabLayout);
        mViewPager = findViewById(R.id.detailInfo_viewPager);
        mViewPager.setContentView(true);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPagerAdapter = new BaseViewPagerAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setCanScroll(true);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                DetailFragment ft = (DetailFragment) mFragmentList.get(position);
                mViewPager.setMinimumHeight(ft.getFragmentViewHeight());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setTab();
    }

    private void setTab() {
        for (int i = 0; i < TAB_TITLES.length; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            View view = this.getLayoutInflater().inflate(R.layout.view_discover_tab, null);
            tab.setCustomView(view);

            TextView tabTitle = view.findViewById(R.id.tab_title);
            tabTitle.setText(TAB_TITLES[i]);

            mTabLayout.addTab(tab);
        }
    }

    private void initBanner() {

        mBanner = findViewById(R.id.detailInfo_banner);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(mBannerImages);

        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
        mBanner.start();
    }

    private List<String> mBannerImages = new ArrayList<>();

    private void initBannerData() {
        mBannerImages.add("http://image1.92bizhi.com/art_green-widescreen_01-2560x1600.jpg");
        mBannerImages.add("http://imgstore.cdn.sogou.com/app/a/100540002/782261.jpg");
        mBannerImages.add("http://pic1.win4000.com/wallpaper/0/51832372d67d8.jpg");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            picBottom = productView.getBottom();
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }
    }

    @Override
    public void onScroll(int scrollY) {
        int top = Math.max(scrollY, picBottom);
        ll_tab.layout(0, top, ll_tab.getWidth(), top + ll_tab.getHeight());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
