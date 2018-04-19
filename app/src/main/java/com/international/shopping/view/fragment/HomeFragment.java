package com.international.shopping.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.international.shopping.R;

public class HomeFragment extends Fragment {
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

//        initBannerData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView tagTv = view.findViewById(R.id.tag_fragment);
        tagTv.setText(mParam1);

//        mBannerView = view.findViewById(R.id.home_bannerView);
//        initBanner();
        return view;
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

    private void initBanner() {
//        mBannerView.initBanner(beanList, this, new OnBannerItemClickListener() {
//            @Override
//            public void onItemShortClick(BannerItemBean bean) {
//
//            }
//        });
//
//        mBannerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "==", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
