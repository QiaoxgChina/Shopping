package com.international.baselib.net;

import com.international.baselib.AppConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

public class RetrofitManager {

    private static final String TAG = RetrofitManager.class.getSimpleName();

    private static volatile RetrofitManager mRetrofitManager = null;
    private static Retrofit mRetrofit = null;

    //私有化构造函数
    private RetrofitManager(){
        initRetrofit();
    }

    public static synchronized RetrofitManager getInstance(){
        synchronized (RetrofitManager.class){
            if(mRetrofitManager == null)
                mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor();
        LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

//        builder.addInterceptor(new RspCheckInterceptor());
        if (AppConfig.DEBUG){
            builder.addInterceptor(LoginInterceptor);
        }

        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                //添加Gson支持
                .addConverterFactory(GsonConverterFactory.create())
                //添加RxJava
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
    }

    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
