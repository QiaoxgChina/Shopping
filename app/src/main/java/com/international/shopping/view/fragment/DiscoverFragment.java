package com.international.shopping.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.international.baselib.base.BaseViewPager;
import com.international.baselib.view.BaseViewPagerAdapter;
import com.international.shopping.R;
import com.international.shopping.view.fragment.discover.AllFragment;

import java.util.ArrayList;

public class DiscoverFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private BaseViewPager mViewPager;
    private TabLayout mTableLayout;
    private BaseViewPagerAdapter mViewPagerAdapter;
    private String[] TAB_TITLES;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();

    public DiscoverFragment() {
    }

    public static DiscoverFragment newInstance(String param1, String param2) {
        DiscoverFragment fragment = new DiscoverFragment();
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

        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        initView(view);
        return view;
    }

    private void initData() {
        TAB_TITLES = new String[]{"全部", "类别1", "类别2", "类别3","类别4", "类别5", "类别6", "类别7"};

        mFragmentList.add(AllFragment.newInstance(TAB_TITLES[0], ""));
        mFragmentList.add(AllFragment.newInstance(TAB_TITLES[1], ""));
        mFragmentList.add(AllFragment.newInstance(TAB_TITLES[2], ""));
        mFragmentList.add(AllFragment.newInstance(TAB_TITLES[3], ""));
        mFragmentList.add(AllFragment.newInstance(TAB_TITLES[4], ""));
        mFragmentList.add(AllFragment.newInstance(TAB_TITLES[5], ""));
        mFragmentList.add(AllFragment.newInstance(TAB_TITLES[6], ""));
        mFragmentList.add(AllFragment.newInstance(TAB_TITLES[7], ""));

    }

    private void initView(View view) {
        mViewPager = view.findViewById(R.id.discover_viewPager);
        mTableLayout = view.findViewById(R.id.discover_tabLayout);
        mTableLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTableLayout));
        mViewPagerAdapter = new BaseViewPagerAdapter(getActivity().getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setCanScroll(true);
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
            View view = this.getLayoutInflater().inflate(R.layout.view_discover_tab, null);
            tab.setCustomView(view);

            TextView tabTitle = view.findViewById(R.id.tab_title);
            tabTitle.setText(TAB_TITLES[i]);

            mTableLayout.addTab(tab);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
