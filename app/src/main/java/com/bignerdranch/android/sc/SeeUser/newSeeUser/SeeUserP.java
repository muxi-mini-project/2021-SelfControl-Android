package com.bignerdranch.android.sc.SeeUser.newSeeUser;

import java.util.List;

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
    public List<LabelPunch> getLabel(String id) {
        return mModel.getList(id);
    }

    @Override
    public void Fail() {
        mView.Fail();
    }
}
