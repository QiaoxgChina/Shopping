package com.international.shopping.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.international.baselib.AppConfig;
import com.international.baselib.view.CustomRefreshLayout;
import com.international.shopping.R;
import com.international.shopping.model.HomeItem;
import com.international.shopping.view.adapter.HomeAdapter;
import com.international.shopping.view.fragment.HomeFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class CustomRefreshActivity extends AppCompatActivity {

    private CustomRefreshLayout customRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_refresh);

        customRefreshLayout = findViewById(R.id.refresh_layout);
        customRefreshLayout.setCanLoadMore(true);
        customRefreshLayout.setFooterView(createFootView());
        customRefreshLayout.setHeaderView(createHeaderView());

        Banner mBanner = findViewById(R.id.banner);
        //设置图片加载器
        mBanner.setImageLoader(new HomeFragment.GlideImageLoader());
        //设置图片集合
        mBanner.setImages(AppConfig.getRandomImageUrl(5));
        mBanner.start();

        initRecyclerView();
    }

    private void initRecyclerView() {
        List<HomeItem> items = new ArrayList<>();
        List<String> imageUrls = AppConfig.getRandomImageUrl(20);
        for (int i = 0; i < imageUrls.size(); i++) {
            HomeItem item = new HomeItem();
            item.setMoney(125 + 125 * i);
            item.setImgUrl(imageUrls.get(i));
            if (i % 3 == 0) {
                item.setTitle("联想电脑Y5300");
            } else if (i % 3 == 1) {
                item.setTitle("康佳（KONKA）A49U 49英寸64位10核4KHDR超高清安卓智能平板液晶电视");

            } else {
                item.setTitle("雷士（NVC）插电五孔插座光控夜灯 浪漫温馨家居卧室过道走廊LED节能小夜灯 EJBX9001");
            }

            items.add(item);
        }
        HomeAdapter mAdapter = new HomeAdapter(items, this);
        RecyclerView mRecyclerView = findViewById(R.id.home_recyclerView);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    private View createFootView() {
        View footView = LayoutInflater.from(this).inflate(R.layout.recycler_load_more_layout, null);
        return footView;
    }

    private View createHeaderView() {
        View footView = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        return footView;
    }
}
