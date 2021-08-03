package com.bignerdranch.android.sc.user.Presenter;

import android.util.Log;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.Bean.GoldHistory;
import com.bignerdranch.android.sc.user.Bean.Rank;
import com.bignerdranch.android.sc.user.Bean.Week;
import com.bignerdranch.android.sc.user.View.CoinQueryActivity;
import com.bignerdranch.android.sc.user.View.UserViewHandler;
import com.bignerdranch.android.sc.user.model.UserAPI_send;
import com.bignerdranch.android.sc.user.model.UserListener_send;
import com.bignerdranch.android.sc.user.model.UserService_send;


import java.util.List;

import rx.Observer;
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
        userService_send.GetWeekNumber();
        UserAPI_send userAPI_send = userService_send.getWeekApi();
        userAPI_send.getWeekNumber(token, month)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Week>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserPresent","折线图部分网络连接失败");
                    }

                    @Override
                    public void onNext(List<Week> weeks) {
                        List<Week> list = weeks;
                        handler.showTheWeekPicture(list);
                    }
                });
    }

    public void GetMessageGoldHistory(String token) {
        userService_send.GetGoldHistory();
        UserAPI_send userAPI_send = userService_send.getGoldHistoryApi();
        userAPI_send.getGoldHistory(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GoldHistory>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserPresent","金币部分网络连接失败");
                    }

                    @Override
                    public void onNext(List<GoldHistory> goldHistories) {

                    }
                });
    }

    public void GetMessageUser(String token){
        userService_send.GetUserClient();
        UserAPI_send userAPI_send = userService_send.getUserClientApi();
        userAPI_send.mUser(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("UserPresent","用户信息部分网络连接失败");
                    }

                    @Override
                    public void onNext(User user) {

                    }
                });
    }

    public void GetMessageRank(String token){
        userService_send.GetRank();
        UserAPI_send userAPI_send = userService_send.getRankApi();
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
                            Log.d("UserPresent","排行榜部分网络连接失败");
                        }

                    }

                    @Override
                    public void onNext(Rank rank) {

                    }
                });
    }

    public void GetMessageBackDrop(String token){
        userService_send.GetBackDrop();
        UserAPI_send userAPI_send = userService_send.getBackDropApi();
        userAPI_send.getCurrentBackdrop(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {

                    }
                });
    }
}
