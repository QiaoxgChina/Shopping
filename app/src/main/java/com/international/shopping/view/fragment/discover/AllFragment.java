package com.international.shopping.view.fragment.discover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.international.baselib.base.BaseFragment;
import com.international.shopping.R;

/**
 * A fragment with a Google +1 button.
 */
public class AllFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AllFragment() {
    }

    public static AllFragment newInstance(String param1, String param2) {
        AllFragment fragment = new AllFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadData() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    protected View initView() {
        View view = LayoutInflater.from(mContent).inflate(R.layout.fragment_all, null, false);

        TextView tv = view.findViewById(R.id.fragment_title);
        tv.setText(mParam1);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
