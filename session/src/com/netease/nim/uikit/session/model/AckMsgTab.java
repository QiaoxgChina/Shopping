package com.netease.nim.uikit.session.model;


import com.netease.nim.uikit.R;
import com.netease.nim.uikit.session.fragment.tab.AckMsgTabFragment;

/**
 * Created by winnie on 2018/3/14.
 */

public enum  AckMsgTab {
    UNREAD(0, AckMsgReminderId.UNREAD,  R.string.unread, R.layout.ack_msg_unread_layout),
    READ(1, AckMsgReminderId.READ, R.string.readed, R.layout.ack_msg_readed_layout);

    public final int tabIndex;

    public final int reminderId;

//    public final Class<? extends AckMsgTabFragment> clazz;

    public final int resId;

    public final int fragmentId;

    public final int layoutId;

//    AckMsgTab(int index, int reminderId, Class<? extends AckMsgTabFragment> clazz, int resId, int layoutId) {
//        this.tabIndex = index;
//        this.reminderId = reminderId;
//        this.clazz = clazz;
//        this.resId = resId;
//        this.fragmentId = index;
//        this.layoutId = layoutId;
//    }

    AckMsgTab(int index, int reminderId,  int resId, int layoutId) {
        this.tabIndex = index;
        this.reminderId = reminderId;
//        this.clazz = clazz;
        this.resId = resId;
        this.fragmentId = index;
        this.layoutId = layoutId;
    }

    public static final AckMsgTab fromReminderId(int reminderId) {
        for (AckMsgTab value : AckMsgTab.values()) {
            if (value.reminderId == reminderId) {
                return value;
            }
        }

        return null;
    }

    public static final AckMsgTab fromTabIndex(int tabIndex) {
        for (AckMsgTab value : AckMsgTab.values()) {
            if (value.tabIndex == tabIndex) {
                return value;
            }
        }

        return null;
    }
}
