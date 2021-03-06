package com.netease.nim.uikit.chatroom.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.netease.nim.uikit.R;
import com.netease.nim.uikit.net.DemoCache;

import java.util.HashMap;
import java.util.Map;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by hzxuwen on 2016/1/19.
 */
public class ChatRoomHelper {
    private static final int[] imageRes = {R.drawable.room_cover_36, R.drawable.room_cover_37, R.drawable.room_cover_49,
            R.drawable.room_cover_50, R.drawable.room_cover_57, R.drawable.room_cover_58, R.drawable.room_cover_64,
            R.drawable.room_cover_72};

    private static Map<String, Integer> roomCoverMap = new HashMap<>();
    private static int index = 0;

    public static void setCoverImage(String roomId, ImageView coverImage, boolean blur) {
        if (roomCoverMap.containsKey(roomId)) {
            blurCoverImage(blur, coverImage, roomCoverMap.get(roomId));
        } else {
            if (index > imageRes.length) {
                index = 0;
            }
            roomCoverMap.put(roomId, imageRes[index]);
            blurCoverImage(blur, coverImage, imageRes[index]);
            index++;
        }
    }

    private static void blurCoverImage(boolean blur, final ImageView imageView, final int resId) {
        final Context context = DemoCache.getContext();

        if (!blur) {
            Glide.with(context).load(resId).into(imageView);
        } else {
            Glide.with(context).load(resId)
                    .apply(new RequestOptions().bitmapTransform(new BlurTransformation(5)))
                    .into(imageView);
        }
    }
}
