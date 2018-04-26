package com.international.shopping.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import javax.security.auth.login.LoginException;

/**
 * Created by Administrator on 2018/4/25.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarItemViewHolder> {

    private static final String TAG = "CarAdapter";

    private List<CarItem> mCarItemList;
    private Context mContext;
    private OnUpdateTotalMoneyListener mListener;

    public CarAdapter(List<CarItem> list, Context context, OnUpdateTotalMoneyListener listener) {
        this.mCarItemList = list;
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    public CarItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car_fragment, null, false);
        return new CarItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarItemViewHolder holder, int position) {

        if (holder != null) {
            final CarItem item = mCarItemList.get(position);
            Glide.with(mContext).load(item.getImgUrl()).into(holder.headerIv);

            holder.titleTv.setText(item.getTitle());

            holder.countTv.setText(item.getCount() + "");

            holder.moneyTv.setText(item.getMoney() + "");

            holder.addTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateCount(item, true);
                }
            });

            holder.minusTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateCount(item, false);
                }
            });

            holder.checkBox.setChecked(item.isSelected());
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateSelected(item);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mCarItemList.size();
    }

    public class CarItemViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTv, minusTv, addTv, moneyTv, countTv;
        private ImageView headerIv;
        private CheckBox checkBox;

        public CarItemViewHolder(View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.carItem_title);
            minusTv = itemView.findViewById(R.id.carItem_minus);
            addTv = itemView.findViewById(R.id.carItem_add);
            moneyTv = itemView.findViewById(R.id.carItem_money);
            countTv = itemView.findViewById(R.id.carItem_count);
            headerIv = itemView.findViewById(R.id.carItem_img);
            checkBox = itemView.findViewById(R.id.carItem_check);
        }
    }

    private void updateCount(CarItem item, boolean isAdd) {
        int count = item.getCount();
        count = isAdd ? count + 1 : count - 1;
        if (count <= 0) {
            count = 1;
        }
        item.setCount(count);
        notifyDataSetChanged();
    }

    private void updateSelected(CarItem item) {
        item.setSelected(!item.isSelected());
        mListener.totalMoneyChanged(getTotalMoney(mCarItemList));
        notifyDataSetChanged();
    }

    public String getTotalMoney(List<CarItem> list) {
        int totalMoney = 0;
        for (CarItem item : list) {
            if (item.isSelected()) {
                totalMoney = totalMoney + item.getCount() * item.getMoney();
            }
        }
        return "" + totalMoney;
    }

    public void selectedAll(boolean isSelectedAll) {
        Log.e(TAG, "selectedAll: isSelectedAll is " + (isSelectedAll ? "true" : "false"));
        for (CarItem item : mCarItemList) {
            item.setSelected(isSelectedAll);
        }
        mListener.totalMoneyChanged(getTotalMoney(mCarItemList));
        notifyDataSetChanged();
    }

    public interface OnUpdateTotalMoneyListener {
        void totalMoneyChanged(String totalMoney);
    }
}
