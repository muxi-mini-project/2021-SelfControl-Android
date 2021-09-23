package com.bignerdranch.android.sc.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtil {
    private RetrofitApi api;

    private NetUtil() {
        api = new Retrofit.Builder()
                .baseUrl("http://39.99.53.8:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RetrofitApi.class);
    }

    private static class NetUtilHolder {
        private static NetUtil INSTANCE = new NetUtil();
    }

    public static NetUtil getInstance() {
        return NetUtilHolder.INSTANCE;
    }

    public RetrofitApi getApi() {
        return api;
    }
}
