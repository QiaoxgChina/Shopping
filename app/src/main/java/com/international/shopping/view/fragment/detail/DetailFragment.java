package com.international.shopping.view.fragment.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.international.shopping.R;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        TextView tv = view.findViewById(R.id.detailInfo_tv);
        tv.setText(mParam1);

        return view;
    }

    public int getFragmentViewHeight(){
        if(mParam1.endsWith("评价")){
            return 200;
        }else{
            return 800;
        }
    }

}
