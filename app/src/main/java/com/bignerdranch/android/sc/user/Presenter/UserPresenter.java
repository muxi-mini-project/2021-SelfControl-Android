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

public class UserPresenter implements UsePresent {

    private UserViewHandler handler;

    private UserService_send userService_send;

    public UserPresenter(UserViewHandler handler) {
        this.handler = handler;
        userService_send = new UserService_send(this);
    }


    @Override
    public void transMessageWeek(List<Week.DataDTO> list) {
        handler.showTheWeekPicture(list);
    }

    public void SendGoldHistory(String token){
        userService_send.GetMessageGoldHistory(token);
    }

    public void SendChangeName(String token,User.DataDTO user){
        userService_send.ChangeName(token,user);
    }

    public void SendRank(String token){
        userService_send.GetMessageRank(token);
    }

    public void SendUser(String token){
        userService_send.GetMessageUser(token);
    }

    public void SendMonthReport(String token){
        userService_send.GetMonthReport(token);
    }

    public void SendLineChart(String token,int month){
        userService_send.GetMessageWeek(token,month);
    }


    @Override
    public void transGoldHistory(List<GoldHistory.DataDTO> goldList) {
        handler.showGoldHistory(goldList);
    }

    @Override
    public void transChangedName() {
        handler.showChangedName();
    }

    @Override
    public void transUser(User.DataDTO u) {
        handler.getUser(u);

    }

    @Override
    public void transRank(Rank.DataDTO rank) {
        handler.showRank(rank);
    }

    @Override
    public void transMonthReport(List<Report.DataDTO> report) {
        handler.showMonthReport(report);
    }


}
