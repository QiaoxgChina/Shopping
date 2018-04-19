package com.international.baselib.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BaseViewPager extends ViewPager {

    //设置ViewPager是否可以滑动
    private boolean isCanScroll = false;

    public BaseViewPager(@NonNull Context context) {
        super(context);
    }

    public BaseViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isCanScroll)
            return false;
        else
            return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isCanScroll) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }

    public void setCanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }

    public boolean getIsCanScroll() {
        return isCanScroll;
    }
}
