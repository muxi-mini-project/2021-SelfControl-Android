package com.bignerdranch.android.sc.user.model;

import android.util.Log;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.bean.GoldHistory;
import com.bignerdranch.android.sc.user.bean.Rank;
import com.bignerdranch.android.sc.user.bean.Report;
import com.bignerdranch.android.sc.user.bean.Week;
import com.bignerdranch.android.sc.user.presenter.UsePresent;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserService_send  {
    private UsePresent usePresent ;
    private Retrofit baseApi;


    public UserService_send(UsePresent u){
        this.usePresent = u;
    }
    public UserAPI_send GetBaseApi() {
        baseApi = new Retrofit.Builder()
                .baseUrl("http://39.99.53.8:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return baseApi.create(UserAPI_send.class);
    }



    public void GetMessageWeek(String token, int month) {
        UserAPI_send userAPI_send = GetBaseApi();
        userAPI_send.getWeekNumber(token, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Week>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserPresent", "折线图部分网络连接失败");
                        Log.d("UserPresent",e.toString());
                    }

                    @Override
                    public void onNext(Week weeks) {
                        List<Week.DataDTO> list = weeks.getData();
                        usePresent.transMessageWeek(list);
                    }
                });
    }

    public void GetMessageGoldHistory(String token) {
        GetBaseApi();
        UserAPI_send userAPI_send = GetBaseApi();
        userAPI_send.getGoldHistory(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoldHistory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserPresent", "金币部分网络连接失败");
                        Log.d("UserPresent",e.toString());
                    }

                    @Override
                    public void onNext(GoldHistory goldHistories) {
                        usePresent.transGoldHistory(goldHistories.getData());
                        Log.d("UserPresent", "金币部分网络连接成功");
                    }
                });
    }

    public void GetMessageUser(String token) {
        GetBaseApi();
        UserAPI_send userAPI_send = GetBaseApi();
        userAPI_send.mUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserPresent", "用户信息部分网络连接失败");
                        Log.d("UserPresent",e.toString());

                    }

                    @Override
                    public void onNext(User user) {
                        usePresent.transUser(user.getData());
                        Log.d("UserPresent", "用户信息部分网络连接成功");
                    }
                });
    }

    public void GetMessageRank(String token) {
        GetBaseApi();
        UserAPI_send userAPI_send = GetBaseApi();
        userAPI_send.getMyRank(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rank>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        {
                            Log.d("UserPresent", "排行榜部分网络连接失败");
                            Log.d("UserPresent",e.toString());
                        }

                    }

                    @Override
                    public void onNext(Rank rank) {
                        usePresent.transRank(rank.getData());
                    }
                });
    }


    public void ChangeName(String token, User.DataDTO user) {
        GetBaseApi();
        UserAPI_send userAPI_send = GetBaseApi();
        userAPI_send.changName(token, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserPresent", "用户信息改变失败");
                        Log.d("UserPresent",e.toString());

                    }

                    @Override
                    public void onNext(User user) {
                        usePresent.transChangedName();
                        Log.d("UserPresent", "用户信息改变成功");
                    }
                });
    }

    public void GetMonthReport(String token) {
        GetBaseApi();
        UserAPI_send userAPI_send = GetBaseApi();
        userAPI_send.getMonthReport(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Report>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserPresent", "月报部分网络连接失败");
                        Log.d("UserPresent",e.toString());

                    }

                    @Override
                    public void onNext(Report dataDTOS) {
                        usePresent.transMonthReport(dataDTOS.getData());
                        Log.d("UserPresent", "月报部分网络连接成功");
                    }
                });
    }
}
