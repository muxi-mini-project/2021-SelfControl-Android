package com.bignerdranch.android.sc.settings.presenter;

import com.bignerdranch.android.sc.settings.contract.BackgroundContract;
import com.bignerdranch.android.sc.settings.model.BackgroundModel;
import com.bignerdranch.android.sc.settings.view.BackgroundActivity;

public class BackgroundPresenter implements BackgroundContract.VP {
    private BackgroundActivity mView;
    private BackgroundModel mModel;

    public BackgroundPresenter(){
        this.mModel = getModelInstance();
    }
    public void bindView(BackgroundActivity mView){ //绑定View
        this.mView = mView;
    }
    public void unBindView(){
        this.mView = null;
    }

    public BackgroundModel getModelInstance() {
        return new BackgroundModel(this);
    }

    @Override
    public void haveRq(String token) {
        mModel.haveRq(token);
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
    public void buyDialog(int click,int[] have) {
        mView.buyDialog(click,have);
    }

    @Override
    public void buyRequest(int click,String token,int[] have) {
        mModel.buyRequest(click,token,have);
    }

    @Override
    public void changeImage(int[] have) {
        mView.changeImage(have);
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
