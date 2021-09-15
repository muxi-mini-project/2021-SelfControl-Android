package com.bignerdranch.android.sc.settings.presenter;

import com.bignerdranch.android.sc.settings.API.BackgroundAPI;
import com.bignerdranch.android.sc.settings.model.BackgroundM;
import com.bignerdranch.android.sc.settings.view.BackgroundView;

public class BackgroundP implements BackgroundAPI.VP {
    private BackgroundView mView;
    private BackgroundM mModel;

    public BackgroundP(){
        this.mModel = getModelInstance();
    }
    public void bindView(BackgroundView mView){ //绑定View
        this.mView = mView;
    }
    public void unBindView(){
        this.mView = null;
    }

    public BackgroundM getModelInstance() {
        return new BackgroundM(this);
    }

    @Override
    public void haveRequest(int click,String token) {
        mModel.haveRequest(click,token);
    }

    @Override
    public void successChange(int num) {
        mView.successChange(num);
    }

    @Override
    public void buyDialog(int click) {
        mView.buyDialog(click);
    }

    @Override
    public void buyRequest(int click,String token) {
        mModel.buyRequest(click,token);
    }

    @Override
    public void noCoin() {
        mView.noCoin();
    }

    @Override
    public void error() {
        mView.error();
    }
}
