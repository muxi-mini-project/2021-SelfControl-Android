package com.bignerdranch.android.sc.seeuser;

import java.util.List;

public class SeeUserPresenter implements SeeUserContract.VP {

    private SeeUserModel mModel;
    private SeeUserActivity mView;

    public void bindView(SeeUserActivity mView){
        this.mView = mView;
    }

    public SeeUserPresenter(){
        mModel = getModelInstance();
    }

    private SeeUserModel getModelInstance() {
        return new SeeUserModel(this);
    }

    @Override
    public void getLabel(String id) {
        mModel.getList(id);
    }

    @Override
    public void Fail() {
        mView.Fail();
    }

    @Override
    public void haveList(List mList) {
        mView.haveList(mList);
    }

    @Override
    public void listNull() {
        mView.listNull();
    }
}
