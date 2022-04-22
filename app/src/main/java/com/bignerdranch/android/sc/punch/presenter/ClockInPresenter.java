package com.bignerdranch.android.sc.punch.presenter;

import com.bignerdranch.android.sc.punch.bean.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.model.ClockInModel;
import com.bignerdranch.android.sc.punch.view.CallBackInView;

public class ClockInPresenter {
    private ClockInModel mClockInModel;
    private String token;

    public ClockInPresenter(String token) {
        this.token = token;
        mClockInModel = new ClockInModel();
    }

    // 删除标签
    public void removeLabels(LabelPunchTitle title, CallBackInView.RemoveCallBack removeCallBack){
        mClockInModel.removeLabel(token, title, removeCallBack::getRemoveMessage);   //不知道这是什么高级的写法！！！
    }

    //设置背景色
    public void setBG(CallBackInView.BGCallBack bgCallBack){
        mClockInModel.setBG(bgCallBack::getBGData, token);
    }

    public void getLabels(String url, CallBackInView.LabelListCallBack labelListCallBack){
        mClockInModel.getClockInLabels(token, url, labelListCallBack::getList);
    }

    public void toClockIn(LabelPunchTitle clockInLabelTitle, CallBackInView.ClockInCallBack clockInCallBack){
        mClockInModel.toClockIn(token, clockInLabelTitle, clockInCallBack::getIsClockIn);
    }
/*
    public void CheckLabelStatus(String token, String url, LabelPunch clockInLabel){
        mClockInModel.CheckLabelStatus(token, url, new ClockInResponseListener() {
            @Override
            public void CheckLabelStatus(boolean status) {
                mClockInView.checkStatusSuccess(clockInLabel, status);
            }

            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }
        });
    }
*/
    public void ifDayAllPunch(int day, CallBackInView.IfAllPunchCallBack ifAllPunchCallBack){
        mClockInModel.getFlowerStatus(token, day, ifAllPunchCallBack::getIsAllPunch);
    }

    public void viewDayPunch(String url, CallBackInView.ViewDayLabelCallBack viewDayPunchCallBack){
        mClockInModel.getViewDayPunch(viewDayPunchCallBack::getViewDayLabels, url, token);
    }
}
