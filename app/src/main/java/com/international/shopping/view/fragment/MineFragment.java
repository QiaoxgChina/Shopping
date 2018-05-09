package com.international.shopping.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.international.baselib.view.SwipeRefreshView;
import com.international.shopping.R;
import com.international.shopping.model.User;
import com.international.shopping.util.SharedPreferencesUtil;
import com.international.shopping.view.activity.AboutUsActivity;
import com.international.shopping.view.activity.CustomRefreshActivity;
import com.international.shopping.view.activity.SettingActivity;
import com.netease.nim.uikit.NimHelper;
import com.netease.nim.uikit.net.DemoCache;
import com.netease.nim.uikit.session.SessionHelper;

public class MineFragment extends Fragment {

    private static final String TAG = "MineFragment";

    private static final int MSG_UPDATE_INFO = 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_UPDATE_INFO:
                    mSwipeView.setRefreshing(false);
                    break;
            }
        }
    };

    //    private View hiddenTitleBar;
    private ScrollView contentScrollView;
    private View settingIv;
    //    private View titleBar;
    private View titleTv;
    private TextView nameTv;
    private ImageView iconIv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private float mAlph = 0;
    private User mUser;

    public MineFragment() {
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
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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

        mUser = SharedPreferencesUtil.getUser();
    }

    private SwipeRefreshView mSwipeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        mSwipeView = view.findViewById(R.id.mine_swipe);
        mSwipeView.setColorSchemeResources(R.color.main_color);
        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(MSG_UPDATE_INFO, 1000);
            }
        });

        nameTv = view.findViewById(R.id.name_tv);
        iconIv = view.findViewById(R.id.mine_header_iv);
        if (mUser != null) {
            nameTv.setText(mUser.getName());
            Glide.with(this).asBitmap()
                    .load(mUser.getIconUrl())
                    .into(iconIv)
                    .onLoadStarted(getActivity().getResources().getDrawable(R.drawable.mine_default_header));
        }
//        hiddenTitleBar = view.findViewById(R.id.titleBar_hidden);
        contentScrollView = view.findViewById(R.id.content_scrollView);
        contentScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                mAlph = (float) i1 / 200;
                setTitleBarAlpha();
            }
        });

        settingIv = view.findViewById(R.id.mine_setting_iv);
        settingIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        titleTv = view.findViewById(R.id.mine_title_tv);

        view.findViewById(R.id.kefu_rl).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String account = "qiaoxg";
                if (NimHelper.getInstance().getAccount().equals("qiaoxg")) {
                    account = "123456";
                }
//                P2PMessageActivity.start(getActivity(), account, null, null);
//"ai-assistant"
                SessionHelper.startP2PSession(getActivity(), account);
            }
        });

        view.findViewById(R.id.aboutUs_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
            }
        });

        view.findViewById(R.id.setting_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        view.findViewById(R.id.collection_rl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), CustomRefreshActivity.class));
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.e(TAG, "onHiddenChanged: hidden is " + hidden);
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause: is ");
        super.onPause();
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume: is ");
        setTitleBarAlpha();
        super.onResume();
    }

    /**
     * 设置titleBar的透明度
     */
    private void setTitleBarAlpha() {
        if (titleTv == null) {
            return;
        }
        titleTv.setAlpha(mAlph);
    }

}
