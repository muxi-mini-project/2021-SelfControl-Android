package com.bignerdranch.android.sc.punch.presenter;

import com.bignerdranch.android.sc.punch.bean.LabelPunch;
import com.bignerdranch.android.sc.punch.bean.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.model.ClockInModel;
import com.bignerdranch.android.sc.punch.model.ClockInResponseListener;
import com.bignerdranch.android.sc.punch.view.ClockInView;

import java.util.List;

public class ClockInPresenter {
    ClockInView mClockInView;
    ClockInModel mClockInModel = new ClockInModel();

    public ClockInPresenter(ClockInView clockInView) {
        this.mClockInView = clockInView;
    }

    public void getLabels(String token){
        mClockInModel.getClockInLabels(token, new ClockInResponseListener() {
            @Override
            public void clockInRequestLabelSuccess(List<LabelPunch> list) { mClockInView.showLabelInfo(list); }

            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }
        });
    }

    public void toClockIn(String token, LabelPunchTitle clockInLabelTitle){
        mClockInModel.toClockIn(token, clockInLabelTitle, new ClockInResponseListener() {
            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }
        });
    }

    public void removeLabel(String token, LabelPunchTitle clockInLabelTitle){
        mClockInModel.removeLabel(token, clockInLabelTitle, new ClockInResponseListener() {
            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }

            @Override
            public void removeLabelSuccess(String message) {
                mClockInView.showRemoveSuccess(message);
            }
        });
    }

    public void CheckLabelStatus(String token, String url, LabelPunch clockInLabel){
        mClockInModel.CheckLabelStatus(token, url, new ClockInResponseListener() {
            @Override
            public void CheckLabelStatus(boolean status) {
                clockInLabel.setLabelStatus(status);
            }

            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }
        });
    }

    public void ifDayAllPunch(String token, int day){
        mClockInModel.getFlowerStatus(token, day, new ClockInResponseListener(){
            @Override
            public void ifDayAllPunch(int number) {
                mClockInView.ifDayAllPunch(number);
            }

            @Override
            public void clockInRequestFail(String message) {
                mClockInView.showError(message);
            }
        });
    }
}
