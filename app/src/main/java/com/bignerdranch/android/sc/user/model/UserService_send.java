package com.bignerdranch.android.sc.user.model;

import com.bignerdranch.android.sc.user.Bean.Week;
import com.bignerdranch.android.sc.user.View.UserViewHandler;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserService_send implements UserListener_send {
    private UserService_send userService_send;
    private Retrofit weekApi;
    private Retrofit goldHistoryApi;
    private Retrofit userClientApi;

    @Override
    public void GetWeekNumber() {
        weekApi = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


    }

    @Override
    public void GetGoldHistory() {
        goldHistoryApi = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Override
    public void GetUserClient() {
        userClientApi = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    public UserAPI_send getWeekApi() {
        return weekApi.create(UserAPI_send.class);
    }

    public UserAPI_send getGoldHistoryApi(){
        return goldHistoryApi.create(UserAPI_send.class);
    }

    public UserAPI_send getUserClientApi(){
        return userClientApi .create(UserAPI_send.class);
    }


}
