package com.international.shopping.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.FontRequest;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.activity.CaptureActivity;
import com.international.baselib.AppConfig;
import com.international.baselib.util.ToastUtil;
import com.international.shopping.R;
import com.international.shopping.model.CarItem;
import com.international.shopping.model.HomeItem;
import com.international.shopping.view.activity.SearchActivity;
import com.international.shopping.view.adapter.CarAdapter;
import com.international.shopping.view.adapter.HomeAdapter;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final int MSG_GET_DATA_RESULT = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_GET_DATA_RESULT:
                    mAdapter.notifyDataSetChanged();
                    mBanner.update(mBannerImages);
                    mSwipeView.setRefreshing(false);
                    break;
            }
        }
    };

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

//    private CustomBannerView mBannerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        initBannerData();

        initItemData();
    }

    private Banner mBanner;
    private RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private SwipeRefreshLayout mSwipeView;
    private View mSearchView;
    private float mAlph = 0.0f;
    private List<HomeItem> items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initBanner(view);
        initRecyclerView(view);
        initSwipeView(view);

        mSearchView = view.findViewById(R.id.home_searchView);
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.animation_activity_in, R.anim.animation_activity_out);
            }
        });
        NestedScrollView contentScrollView = view.findViewById(R.id.home_scrollView);
        contentScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                mAlph = (float) i1 / 200;
                setTitleBarAlpha();
            }
        });

        mSearchView.setEnabled(mAlph >= 0.3);


        view.findViewById(R.id.btn_saoyisao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CaptureActivity.class));
            }
        });

        return view;
    }

    private final static int REQ_CODE = 1028;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
//            mImage.setVisibility(View.GONE);
//            mTvResult.setVisibility(View.VISIBLE);
//            mImageCallback.setVisibility(View.VISIBLE);

            String result = data.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
            Bitmap bitmap = data.getParcelableExtra(CaptureActivity.SCAN_QRCODE_BITMAP);

//            mTvResult.setText("扫码结果："+result);
//            showToast("扫码结果：" + result);
//            if(bitmap != null){
//                mImageCallback.setImageBitmap(bitmap);//现实扫码图片
//            }
        }


    }

    /**
     * 设置titleBar的透明度
     */
    private void setTitleBarAlpha() {
        if (mSearchView == null) {
            return;
        }
        mSearchView.setEnabled(mAlph >= 0.3);
        mSearchView.setAlpha(mAlph);
    }

    private void initSwipeView(View view) {
        mSwipeView = view.findViewById(R.id.home_swipeView);
        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
                mHandler.sendEmptyMessageDelayed(MSG_GET_DATA_RESULT, 1000);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    private void initItemData() {
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
    }

    private void initRecyclerView(View view) {
        mAdapter = new HomeAdapter(items, getActivity());
        mRecyclerView = view.findViewById(R.id.home_recyclerView);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initBanner(View view) {

        mBanner = view.findViewById(R.id.banner);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(mBannerImages);

//        mBanner.setBannerTitles(mBannerTitles);

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                ToastUtil.showTip(mBannerTitles.get(position), getActivity());
            }
        });
    }

    public static class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             注意：
             1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
             2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
             传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
             切记不要胡乱强转！
             */
            //Glide 加载图片简单用法
            Glide.with(context).load(path).into(imageView);
        }

//        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
//        @Override
//        public ImageView createImageView(Context context) {
//            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
//            SimpleDraweeView simpleDraweeView=new SimpleDraweeView(context);
//            return simpleDraweeView;
//        }
    }


//    private List<String> mBannerTitles;
    private List<String> mBannerImages;

    private void initBannerData() {
//        mBannerTitles = new ArrayList<>();
//        mBannerTitles.add("Banner_1");
//        mBannerTitles.add("Banner_2");
//        mBannerTitles.add("Banner_3");
//        mBannerTitles.add("Banner_4");

        mBannerImages = AppConfig.getRandomImageUrl(5);
    }

    private void refreshData() {
//        mBannerTitles.clear();
        mBannerImages.clear();
        items.clear();

        initBannerData();
        initItemData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
