package com.international.shopping.view.activity;

import android.os.Bundle;
import android.view.View;

import com.international.shopping.R;
import com.international.shopping.base.BaseActivity;

public class AboutUsActivity extends BaseActivity {

//    private ViewPager mBannerView;

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
//        mBannerView = (ViewPager)findViewById(R.id.aboutUs_bannerView);
//        initBanner();
    }

//    private List<BannerItemBean> beanList = new ArrayList<>();
//
//    private void initBannerData() {
//        BannerItemBean bean = new BannerItemBean();
//        bean.setImgUrl("http://img2.imgtn.bdimg.com/it/u=500808421,1575925585&fm=200&gp=0.jpg");
//        bean.setTitle("这是第一个图片");
//
//        BannerItemBean bean1 = new BannerItemBean();
//        bean1.setImgUrl("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=5e310a4ddb09b3deffb2ec2ba4d606f4/9d82d158ccbf6c81887581cdb63eb13533fa4050.jpg");
//        bean1.setTitle("这是第二个图片");
//
//        BannerItemBean bean2 = new BannerItemBean();
//        bean2.setImgUrl("http://imgsrc.baidu.com/image/c0%3Dshijue1%2C0%2C0%2C294%2C40/sign=5bc31d05b4096b6395145613645aed31/f7246b600c338744ba7df5565b0fd9f9d72aa064.jpg");
//        bean2.setTitle("这是第三个图片");
//
//        beanList.add(bean);
//        beanList.add(bean1);
//        beanList.add(bean2);
//    }
//
//    private void initBanner() {
//        initBannerData();
//        initData();
//
//    }
//    private List<Fragment> mFragmentList = new ArrayList<>();
//    private BannerViewPagerAdapter mAdapter;
//    private void initData() {
//        for (BannerItemBean bean : beanList) {
////            View view = LayoutInflater.from(mFragment.getActivity()).inflate(R.layout.layout_banner_item, null);
////            ImageView iv = view.findViewById(R.id.banner_imageView);
////            Glide.with(mFragment).asBitmap().load(bean.getImgUrl()).into(iv);
//////            iv.setOnClickListener(new OnClickListener() {
//////                @Override
//////                public void onClick(View v) {
//////                    mListener.onItemShortClick(bean);
//////                }
//////            });
////
////            TextView tv = view.findViewById(R.id.banner_tv);
////            tv.setText(bean.getTitle());
////
////            Log.e(TAG, "initData: title is " +  bean.getTitle());
////            mImageViews.add(view);
//
//            BannerFragment bfg = BannerFragment.newInstance(bean.getImgUrl(),bean.getTitle());
//            mFragmentList.add(bfg);
//
//        }
//
//        mAdapter = new BannerViewPagerAdapter(mFragmentList, getSupportFragmentManager());
//        mBannerView.setAdapter(mAdapter);
//        mBannerView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        mBannerView.setCurrentItem(0);
//
//    }

}
