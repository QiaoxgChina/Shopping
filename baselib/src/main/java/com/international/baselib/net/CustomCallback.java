package com.international.baselib.net;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class CustomCallback<T extends CustomResult> implements Callback<T> {
    @Override
    public void onResponse(Call call, Response response) {
        if (response.raw().code() == 200) {//200是服务器有合理响应
            if(response.code() == 0){
                onSuccess(response);
            }else {
                onFail(response.message());
            }

        } else {//失败响应
            onFailure(call, new RuntimeException("response error,detail = " + response.raw().toString()));
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if(t instanceof SocketTimeoutException){
            //
        }else if(t instanceof ConnectException){
            //
        }else if(t instanceof RuntimeException){
            //
        }
        onFail(t.getMessage());
    }

    public abstract void onSuccess(Response<T> response);

    public abstract void onFail(String message);
}
