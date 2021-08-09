package com.bignerdranch.android.sc.user.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.Bean.GoldHistory;
import com.bignerdranch.android.sc.user.Bean.Rank;
import com.bignerdranch.android.sc.user.Bean.Report;
import com.bignerdranch.android.sc.user.Bean.Week;
import com.bignerdranch.android.sc.user.View.CoinQueryActivity;
import com.bignerdranch.android.sc.user.View.UserViewHandler;
import com.bignerdranch.android.sc.user.model.UserAPI_send;
import com.bignerdranch.android.sc.user.model.UserListener_send;
import com.bignerdranch.android.sc.user.model.UserService_send;


import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPresenter {

    private UserViewHandler handler;

    private UserService_send userService_send;

    public UserPresenter(UserViewHandler handler) {
        this.handler = handler;
        userService_send = new UserService_send();
    }


    /**
     * 目前Presenter里的方法均没有实现数据处理
     */

    public void GetMessageWeek(String token, int month) {
        userService_send.GetBaseApi();
        UserAPI_send userAPI_send = userService_send.getBase();
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
                        handler.showTheWeekPicture(list);
                    }
                });
    }

    public void GetMessageGoldHistory(String token) {
        userService_send.GetBaseApi();
        UserAPI_send userAPI_send = userService_send.getBase();
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
                        handler.showGoldHistory(goldHistories.getData());
                        Log.d("UserPresent", "金币部分网络连接成功");
                    }
                });
    }

    public void GetMessageUser(String token) {
        userService_send.GetBaseApi();
        UserAPI_send userAPI_send = userService_send.getBase();
        userAPI_send.mUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User.DataDTO>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserPresent", "用户信息部分网络连接失败");
                        Log.d("UserPresent",e.toString());

                    }

                    @Override
                    public void onNext(User.DataDTO user) {
                        handler.getUser(user);
                        Log.d("UserPresent", "用户信息部分网络连接成功");
                    }
                });
    }

    public void GetMessageRank(String token) {
        userService_send.GetBaseApi();
        UserAPI_send userAPI_send = userService_send.getBase();
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
                        handler.showRank(rank.getData());
                    }
                });
    }


    public void ChangeName(String token, User.DataDTO user) {
        userService_send.GetBaseApi();
        ;
        UserAPI_send userAPI_send = userService_send.getBase();
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
                        handler.showChangedName();
                        Log.d("UserPresent", "用户信息改变成功");
                    }
                });
    }

    public void GetMonthReport(String token) {
        userService_send.GetBaseApi();
        ;
        UserAPI_send userAPI_send = userService_send.getBase();
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
                        handler.showMonthReport(dataDTOS.getData());
                        Log.d("UserPresent", "月报部分网络连接成功");
                    }
                });
    }
}
