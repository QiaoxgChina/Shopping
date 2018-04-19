package com.international.shopping.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */

public class ActivityUtil {

    private static final String TAG = "ActivityUtil";

    private static List<Activity> mActivityList = null;


    private ActivityUtil() {
        mActivityList = new ArrayList<>();
    }

    public static ActivityUtil getInstance() {
        return InstanceHolder.mInstance;
    }

    public void addActivity(Activity activity) {
        if (!mActivityList.contains(activity)) {
            mActivityList.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (mActivityList.contains(activity)) {
            mActivityList.remove(activity);
        }
    }

    public void clearActivityTask() {
        for (Activity activity : mActivityList) {
            activity.finish();
        }
    }

    private static class InstanceHolder {
        static ActivityUtil mInstance = new ActivityUtil();
    }

}
