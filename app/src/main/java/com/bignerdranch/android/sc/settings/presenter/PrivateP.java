package com.bignerdranch.android.sc.settings.presenter;

import com.bignerdranch.android.sc.settings.API.PrivateAPI;
import com.bignerdranch.android.sc.settings.model.PrivateM;
import com.bignerdranch.android.sc.settings.view.PrivateV;

public class PrivateP implements PrivateAPI.VP {
    private PrivateM mModel;
    private PrivateV mView;

    public PrivateP(){
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
    public void trueRequest(String token) {
        mModel.request(1,token);
    }

    @Override
    public void falseRequest(String token) {
        mModel.request(0,token);
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
