package com.bignerdranch.android.sc.settings.presenter;

import com.bignerdranch.android.sc.settings.api.PrivateAPI;
import com.bignerdranch.android.sc.settings.model.PrivateModel;
import com.bignerdranch.android.sc.settings.view.PrivateActivity;

public class PrivatePresenter implements PrivateAPI.VP {
    private PrivateModel mModel;
    private PrivateActivity mView;

    public PrivatePresenter(){
        this.mModel = getModelInstance();
    }
    public void bindView(PrivateActivity mView){ //绑定View
        this.mView = mView;
    }
    public void unBindView(){
        this.mView = null;
    }

    public PrivateModel getModelInstance() {
        return new PrivateModel(this);
    }

    @Override
    public void trueRequest(String token) {
        mModel.request(1,token);
    }

    @Override
    public void falseRequest(String token) {
        mModel.request(2,token);
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
