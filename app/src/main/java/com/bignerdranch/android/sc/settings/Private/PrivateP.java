package com.bignerdranch.android.sc.settings.Private;

public class PrivateP implements PrivateAPI.VP {
    private PrivateM mModel;
    private PrivateV mView;

    public void PrivateP(){
        this.mModel = getModelInstance();
    }
    public void bindView(PrivateV mView){ //绑定View
        this.mView = mView;
    }
    public void unBindView(){
        this.mView = null;
    }

    public PrivateM getModelInstance() {
        return new PrivateM(this);
    }

    @Override
    public void trueRequest() {
        mModel.request(1);
    }

    @Override
    public void falseRequest() {
        mModel.request(0);
    }

    @Override
    public void success() {
        mView.success();
    }

    @Override
    public void fail() {
        mView.fail();
    }
}
