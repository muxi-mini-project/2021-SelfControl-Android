package com.bignerdranch.android.sc.settings.Background;

public class BackgroundP implements BackgroundAPI.VP {
    private BackgroundView mView;
    private BackgroundM mModel;

    public void BackgroundP(){
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
    public void onRequest() {
        mModel.changeBackground();
    }

    @Override
    public void successChange() {

    }

    @Override
    public void noCoin() {

    }

    @Override
    public void error() {

    }
}
