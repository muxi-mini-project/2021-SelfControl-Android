package com.bignerdranch.android.sc.user.presenter;

import android.graphics.Bitmap;
import android.net.Uri;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.bean.GoldHistory;
import com.bignerdranch.android.sc.user.bean.Rank;
import com.bignerdranch.android.sc.user.bean.Report;
import com.bignerdranch.android.sc.user.bean.Week;
import com.bignerdranch.android.sc.user.view.UserViewHandler;
import com.bignerdranch.android.sc.user.model.UserService_send;


import java.io.File;
import java.nio.file.Path;
import java.util.List;

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
    public void transUser(User.DataDTO u,Bitmap bitmap) {
        handler.getUser(u, bitmap);
    }

    @Override
    public void transRank(Rank.DataDTO rank) {
        handler.showRank(rank);
    }

    @Override
    public void transMonthReport(List<Report.DataDTO> report) {
        handler.showMonthReport(report);
    }


    public void changeAvatar(String path, String token){
        userService_send.updateUserPicture(path, token);
    }

}
