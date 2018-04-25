package com.international.shopping.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.international.baselib.view.SwipeRefreshView;
import com.international.shopping.R;
import com.international.shopping.event.SwitchMainTabEvent;

import org.greenrobot.eventbus.EventBus;

public class CarFragment extends Fragment {

    private static final int MSG_REFRESH_RECYCLE = 1;
    private static final int MSG_LOAD_RECYCLE = 2;


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_REFRESH_RECYCLE:
                    Toast.makeText(getActivity(),"更新成功",Toast.LENGTH_SHORT).show();
                    mSwipeView.setRefreshing(false);
                    break;
                case MSG_LOAD_RECYCLE:
                    Toast.makeText(getActivity(),"加载成功",Toast.LENGTH_SHORT).show();
                    mSwipeView.setLoading(false);
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
    private SwipeRefreshView mSwipeView;
    private RecyclerView mRecycleView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car, container, false);

        view.findViewById(R.id.toBuy_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SwitchMainTabEvent(2,0));
            }
        });

        mNoDataView = view.findViewById(R.id.noData_view);
        mNoDataView.setVisibility(View.GONE);

        mSwipeView = view.findViewById(R.id.swipe_refresh);
        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(MSG_REFRESH_RECYCLE,1000);
            }
        });

        mSwipeView.setOnLoadMoreListener(new SwipeRefreshView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mHandler.sendEmptyMessageDelayed(MSG_LOAD_RECYCLE,1000);
            }
        });

        mRecycleView = view.findViewById(R.id.recycler_view);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
