package com.bignerdranch.android.sc.user.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.user.bean.GoldHistory;
import com.bignerdranch.android.sc.user.bean.Rank;
import com.bignerdranch.android.sc.user.bean.Report;
import com.bignerdranch.android.sc.user.bean.Week;
import com.bignerdranch.android.sc.user.presenter.UsePresent;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserService_send  {
    private UsePresent usePresent ;

    public UserService_send(UsePresent u){
        this.usePresent = u;
    }

    public void GetMessageWeek(String token, int month) {
        NetUtil.getInstance().getApi().getWeekNumber(token, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Week>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Week weeks) {
                        List<Week.DataDTO> list = weeks.getData();
                        usePresent.transMessageWeek(list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("UserPresent", "折线图部分网络连接失败");
                        Log.d("UserPresent",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void GetMessageGoldHistory(String token) {
        NetUtil.getInstance().getApi().getGoldHistory(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GoldHistory>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GoldHistory goldHistories) {
                        usePresent.transGoldHistory(goldHistories.getData());
                        Log.d("UserPresent", "金币部分网络连接成功");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("UserPresent", "金币部分网络连接失败");
                        Log.d("UserPresent",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void GetMessageUser(String token) {
        NetUtil.getInstance().getApi().userInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        usePresent.transUser(user.getData(),null);
                        Log.d("UserPresent", "用户信息部分网络连接成功");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("UserPresent", "用户信息部分网络连接失败");
                        Log.d("UserPresent",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void GetMessageRank(String token) {
        NetUtil.getInstance().getApi().getMyRank(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Rank>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Rank rank) {
                        usePresent.transRank(rank.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("UserPresent", "排行榜部分网络连接失败");
                        Log.d("UserPresent",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void ChangeName(String token, User.DataDTO user) {
        NetUtil.getInstance().getApi().changName(token, user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        usePresent.transChangedName();
                        Log.d("UserPresent", "用户信息改变成功");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("UserPresent", "用户信息改变失败");
                        Log.d("UserPresent",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void GetMonthReport(String token) {
        NetUtil.getInstance().getApi().getMonthReport(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Report>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Report dataDTOS) {
                        usePresent.transMonthReport(dataDTOS.getData());
                        Log.d("UserPresent", "月报部分网络连接成功");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("UserPresent", "月报部分网络连接失败");
                        Log.d("UserPresent",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateUserPicture(File file){

    }
}
