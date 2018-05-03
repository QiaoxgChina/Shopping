package com.international.shopping.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.international.shopping.R;
import com.international.shopping.model.HomeItem;
import com.international.shopping.view.activity.ProductInfoActivity;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


/**
 * Created by Administrator on 2018/4/25.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CarItemViewHolder> {

    private static final String TAG = "HomeAdapter";

    private List<HomeItem> mItemList;
    private Context mContext;
    private MultiTransformation multi;

    public HomeAdapter(List<HomeItem> list, Context context) {
        this.mItemList = list;
        this.mContext = context;

        multi = new MultiTransformation(
                new RoundedCornersTransformation(7, 0, RoundedCornersTransformation.CornerType.TOP));
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

            Glide.with(mContext).load(item.getImgUrl())
                    .apply(bitmapTransform(multi))
                    .into(holder.headerIv);

            holder.titleTv.setText(item.getTitle());

            holder.moneyTv.setText(String.format(mContext.getResources().getString(R.string.label_money), item.getMoney() + ""));
            holder.parentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ProductInfoActivity.start("", (Activity) mContext);
//                    mContext.startActivity(new Intent(mContext, ProductInfoActivity.class));
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    public class CarItemViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTv, moneyTv;
        private ImageView headerIv;
        private View parentView;

        public CarItemViewHolder(View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.homeItem_title);
            moneyTv = itemView.findViewById(R.id.homeItem_money);
            headerIv = itemView.findViewById(R.id.homeItem_img);
            parentView = itemView.findViewById(R.id.homeItem_parent);
        }
    }

}
