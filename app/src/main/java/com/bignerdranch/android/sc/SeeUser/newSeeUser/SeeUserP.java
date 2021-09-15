package com.bignerdranch.android.sc.SeeUser.newSeeUser;

public class SeeUserP implements SeeUserAPI.VP {

    private SeeUserM mModel;
    private SeeUserV mView;

    public void bindView(SeeUserV mView){
        this.mView = mView;
    }

    public SeeUserP(){
        mModel = getModelInstance();
    }

    private SeeUserM getModelInstance() {
        return new SeeUserM(this);
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
    public void haveList() {
        mView.haveList();
    }

    @Override
    public void listNull() {
        mView.listNull();
    }
}
