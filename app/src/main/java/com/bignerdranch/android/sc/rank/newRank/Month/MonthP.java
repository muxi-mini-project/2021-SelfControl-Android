package com.bignerdranch.android.sc.rank.newRank.Month;
import java.util.List;

public class MonthP implements MonthAPI.VP {

    private MonthM mModel;
    private MonthFragment mView;

    public MonthP(){
        this.mModel = getModelInstance();
    }
    public void bindView(MonthFragment mView){ //绑定View
        this.mView = mView;
    }
    public void unBindView(){
        this.mView = null;
    }

    public MonthM getModelInstance() {
        return new MonthM(this);
    }



    @Override
    public List requestList() {

    }

    @Override
    public void changeRank() {

    }

    @Override
    public void haveList() {

    }

    @Override
    public void ListFail() {

    }

    @Override
    public void changeSuccess() {
        mView.changeSuccess();
    }

    @Override
    public void changeFail() {
        mView.changeFail();
    }
}
