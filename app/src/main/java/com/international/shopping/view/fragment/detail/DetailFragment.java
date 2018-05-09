package com.international.shopping.view.fragment.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.international.baselib.AppConfig;
import com.international.baselib.util.ToastUtil;
import com.international.shopping.R;
import com.international.shopping.view.adapter.DetailAdapter;

import java.util.List;

public class DetailFragment extends Fragment {

    private static final String TAG = "DetailFragment";

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public DetailFragment() {
    }

    public static DetailFragment newInstance(String param1) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }

        initItemData();
    }

    private RecyclerView mRecyclerView;
    private DetailAdapter mDetailAdapter;
    private List<String> imageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        TextView tv = view.findViewById(R.id.detailInfo_tv);
        tv.setText(mParam1);

        initRecyclerView(view);

        return view;
    }

    private void initItemData() {
        imageList = AppConfig.getRandomImageUrl(15);

        ToastUtil.showTip(imageList.size() + "", getActivity());
    }

    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.detailInfo_rv);
        mDetailAdapter = new DetailAdapter(imageList, getActivity());

        mRecyclerView.setAdapter(mDetailAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.setNestedScrollingEnabled(false);

//        mRecyclerView.setMinimumHeight(imageList.size()*500);
    }

}
