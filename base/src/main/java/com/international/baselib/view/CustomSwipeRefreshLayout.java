package com.international.baselib.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/4/25.
 */

public class CustomSwipeRefreshLayout extends SwipeRefreshLayout {

    public CustomSwipeRefreshLayout(Context context) {
        this(context,null);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }
}
