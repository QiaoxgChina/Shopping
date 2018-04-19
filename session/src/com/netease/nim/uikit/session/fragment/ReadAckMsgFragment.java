//package com.netease.nim.uikit.session.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AbsListView;
//
//import com.netease.nim.uikit.R;
//import com.netease.nim.uikit.business.team.ui.TeamInfoGridView;
//import com.netease.nim.uikit.common.adapter.TAdapterDelegate;
//import com.netease.nim.uikit.common.adapter.TViewHolder;
//import com.netease.nim.uikit.session.activity.AckMsgInfoActivity;
//import com.netease.nim.uikit.session.adapter.AckMsgDetailAdapter;
//import com.netease.nim.uikit.session.fragment.tab.AckMsgTabFragment;
//import com.netease.nim.uikit.session.model.AckMsgViewModel;
//import com.netease.nim.uikit.session.viewholder.AckMsgDetailHolder;
//import com.netease.nimlib.sdk.Observer;
//import com.netease.nimlib.sdk.msg.model.IMMessage;
//import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 群已读人员界面
// * Created by winnie on 2018/3/15.
// */
//
//public class ReadAckMsgFragment extends AckMsgTabFragment implements TAdapterDelegate {
//    private AckMsgViewModel viewModel;
//    private AckMsgDetailAdapter adapter;
//    private List<AckMsgDetailAdapter.AckMsgDetailItem> dataSource;
//    private View rootView;
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        initAdapter();
//        findViews();
//        IMMessage message = (IMMessage) getActivity().getIntent().getSerializableExtra(AckMsgInfoActivity.EXTRA_MESSAGE);
//        viewModel = ViewModelProviders.of(getActivity()).get(AckMsgViewModel.class);
//        viewModel.init(message);
//        viewModel.getTeamMsgAckInfo().observe(getActivity(), new Observer<TeamMsgAckInfo>() {
//            @Override
//            public void onChanged(@Nullable TeamMsgAckInfo teamMsgAckInfo) {
//                for (String account : teamMsgAckInfo.getAckAccountList()) {
//                    dataSource.add(new AckMsgDetailAdapter.AckMsgDetailItem(teamMsgAckInfo.getTeamId(), account));
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }
//
//    @Override
//    protected void onInit() {
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        rootView = inflater.inflate(R.layout_custom_banner_view.unread_ack_msg_fragment, container, false);
//        return rootView;
//    }
//
//    private void initAdapter() {
//        dataSource = new ArrayList<>();
//        adapter = new AckMsgDetailAdapter(getActivity(), dataSource, this);
//    }
//
//    private void findViews() {
//        TeamInfoGridView teamInfoGridView = rootView.findViewById(R.id.team_member_grid);
//        teamInfoGridView.setSelector(R.color.transparent);
//        teamInfoGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if (scrollState == 0) {
//                    adapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//            }
//        });
//        teamInfoGridView.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    adapter.notifyDataSetChanged();
//                    return true;
//                }
//                return false;
//            }
//        });
//        teamInfoGridView.setAdapter(adapter);
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 1;
//    }
//
//    @Override
//    public Class<? extends TViewHolder> viewHolderAtPosition(int position) {
//        return AckMsgDetailHolder.class;
//    }
//
//    @Override
//    public boolean enabled(int position) {
//        return false;
//    }
//}
