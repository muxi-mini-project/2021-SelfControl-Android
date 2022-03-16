package com.bignerdranch.android.sc.net;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetUtil {
    private final OkHttpClient client;
    private final RetrofitApi api;
    private final Gson gson;
    private static final String baseUrl = "http://self-control.muxixyz.com/api/v1/";

    private NetUtil() {
        gson = new Gson();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        api = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build().create(RetrofitApi.class);
    }

    public Gson getGson() {
        return gson;
    }

    public static NetUtil getInstance() {
        return NetUtilHolder.INSTANCE;
    }

    private static class NetUtilHolder {
        private static final NetUtil INSTANCE = new NetUtil();
    }

    public OkHttpClient getClient() {
        return client;
    }

    public RetrofitApi getApi() {
        return api;
    }
}
