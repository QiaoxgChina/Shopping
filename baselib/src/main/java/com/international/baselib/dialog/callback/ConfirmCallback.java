package com.international.baselib.dialog.callback;

/**
 * Created by Administrator on 2018/4/17.
 */

public interface ConfirmCallback {

    /**
     * 确认结果
     *
     * @param isConfirm
     * @param obj
     */
    void onResult(boolean isConfirm, Object obj);
}
