package com.international.shopping.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.international.shopping.R;
import com.international.shopping.model.HomeItem;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.CarItemViewHolder> {

    private static final String TAG = "DiscoverAdapter";

    private List<HomeItem> mItemList;
    private Context mContext;

    public DiscoverAdapter(List<HomeItem> list, Context context) {
        this.mItemList = list;
        this.mContext = context;
    }

    @Override
    public CarItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_fragment, null, false);
        return new CarItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarItemViewHolder holder, int position) {

        if (holder != null) {
            final HomeItem item = mItemList.get(position);

            Glide.with(mContext).load(item.getImgUrl()).into(holder.headerIv);

            holder.titleTv.setText(item.getTitle());

            holder.moneyTv.setText(String.format(mContext.getResources().getString(R.string.label_money), item.getMoney() + ""));

        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class CarItemViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTv, moneyTv;
        private ImageView headerIv;

        public CarItemViewHolder(View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.homeItem_title);
            moneyTv = itemView.findViewById(R.id.homeItem_money);
            headerIv = itemView.findViewById(R.id.homeItem_img);
        }
    }

}
