package com.international.baselib.dialog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2018/4/17.
 */

public class LoadingDialog {

    public static void loading(Activity activity){
        ProgressDialog progressBar = new ProgressDialog(activity);
        progressBar.setMessage("");
    }
}
