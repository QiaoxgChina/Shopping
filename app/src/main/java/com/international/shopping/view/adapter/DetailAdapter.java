package com.international.shopping.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.international.shopping.R;
import com.international.shopping.model.CarItem;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailViewHolder> {

    private static final String TAG = "DetailAdapter";

    private List<String> mItemList;
    private Context mContext;

    public DetailAdapter(List<String> list, Context context) {
        this.mItemList = list;
        this.mContext = context;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_fragment, null);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {

        if (holder != null) {
            String item = mItemList.get(position);
            Glide.with(mContext).load(item).into(holder.headerIv);
        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {

        private ImageView headerIv;

        public DetailViewHolder(View itemView) {
            super(itemView);

            headerIv = itemView.findViewById(R.id.Item_img);
        }
    }
}
