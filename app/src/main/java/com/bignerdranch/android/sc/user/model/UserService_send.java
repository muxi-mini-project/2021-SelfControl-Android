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
    private Retrofit baseApi;


    @Override
    public void GetBaseApi() {
        baseApi = new Retrofit.Builder()
                .baseUrl("http://39.99.53.8:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


    }



    public UserAPI_send getBase() {
        return baseApi.create(UserAPI_send.class);
    }
}
