package com.international.shopping.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.international.baselib.util.ToastUtil;
import com.international.baselib.view.SwipeRefreshView;
import com.international.shopping.R;
import com.international.shopping.event.SwitchMainTabEvent;
import com.international.shopping.model.CarItem;
import com.international.shopping.view.adapter.CarAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class CarFragment extends Fragment {

    private static final String TAG = "CarFragment";

    private static final int MSG_REFRESH_RECYCLE = 1;
    private static final int MSG_LOAD_RECYCLE = 2;

    private final int PAGE_SIZE = 12;
    private int PAGE_INDEX = 0;
    private int lastPosition = PAGE_SIZE - 1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REFRESH_RECYCLE:
                    Toast.makeText(getActivity(), "更新成功", Toast.LENGTH_SHORT).show();
                    mSwipeView.setRefreshing(false);
                    break;
                case MSG_LOAD_RECYCLE:
                    initData();
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CarFragment() {
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
    public static CarFragment newInstance(String param1, String param2) {
        CarFragment fragment = new CarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private View mNoDataView;
    private SwipeRefreshLayout mSwipeView;
    private RecyclerView mRecycleView;
    private CarAdapter mAdapter;
    private List<CarItem> carItemList = new ArrayList<>();
    private TextView mTotalMoneyTv;
    private View mCheckBox;
    private boolean isSelectedAll = false;

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
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        view.findViewById(R.id.toBuy_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SwitchMainTabEvent(2, 0));
            }
        });

        mTotalMoneyTv = view.findViewById(R.id.totalMoney_tv);
        mNoDataView = view.findViewById(R.id.noData_view);
        mNoDataView.setVisibility(View.GONE);

        mSwipeView = view.findViewById(R.id.swipe_refresh);
//        mSwipeView.setItemCount(12);
        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(MSG_REFRESH_RECYCLE, 1000);
            }
        });

        mAdapter = new CarAdapter(carItemList, getActivity(), new CarAdapter.OnUpdateTotalMoneyListener() {
            @Override
            public void totalMoneyChanged(String totalMoney) {
                mTotalMoneyTv.setText(String.format(getString(R.string.car_total_money), totalMoney));
            }
        });

        mRecycleView = view.findViewById(R.id.recycler_view);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LinearLayoutManager llm = (LinearLayoutManager) mRecycleView.getLayoutManager();
                Log.e(TAG, "onScrollChange: last position is " + llm.findLastVisibleItemPosition());
                int curLastPosition = llm.findLastVisibleItemPosition();
                if (curLastPosition < 20 && curLastPosition == lastPosition) {
                    PAGE_INDEX++;
                    lastPosition = PAGE_SIZE * (PAGE_INDEX + 1) - 1;
                    mHandler.sendEmptyMessageDelayed(MSG_LOAD_RECYCLE, 1000);
                } else {
                    ToastUtil.showTip("到底了", getActivity());
                }
            }
        });


        mRecycleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mOldYPosition = v.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float mCurrYPosition = v.getY();
                        Log.e(TAG, "onTouch: mOldYPosition is " + mOldYPosition + "  and mCurrYPosition is " + mCurrYPosition + " and  = ======  is " + (mCurrYPosition - mOldYPosition));
                        if (mCurrYPosition > mOldYPosition) {
                            if (mCurrYPosition - mOldYPosition > 100) {

                            } else {
                                ToastUtil.showTip("上拉距离不够", getActivity());
                            }
                        } else {
                            ToastUtil.showTip("下拉动作", getActivity());
                        }

                        break;
                }
                return false;
            }
        });

        mTotalMoneyTv.setText(String.format(getString(R.string.car_total_money), mAdapter.getTotalMoney(carItemList)));

        mCheckBox = view.findViewById(R.id.selectedAll_check);
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.selectedAll(!isSelectedAll);
                isSelectedAll = !isSelectedAll;
            }
        });
        return view;
    }

    float mOldYPosition;

    private void initData() {
        CarItem item = new CarItem();
        item.setCount(2);
        item.setImgUrl("https://img10.360buyimg.com/n1/s180x180_jfs/t15082/163/1102057221/274351/c3fbc184/5a449dd7Ne415a02c.jpg");
        item.setMoney(125);
        item.setTitle("联想电脑Y5300");
        item.setSelected(false);
        carItemList.add(item);

        CarItem item2 = new CarItem();
        item2.setCount(1);
        item2.setImgUrl("https://img14.360buyimg.com/n1/jfs/t6049/208/2003677230/203727/54271ae0/593959fbNa76b2674.jpg");
        item2.setMoney(13488);
        item2.setTitle("MacBook Pro");
        item2.setSelected(false);
        carItemList.add(item2);

        for (int i = 0; i < 10; i++) {
            carItemList.add(item2);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
