package com.bignerdranch.android.sc.rank.newRank.presenter;
import com.bignerdranch.android.sc.rank.newRank.API.MonthAPI;
import com.bignerdranch.android.sc.rank.newRank.model.MonthM;
import com.bignerdranch.android.sc.rank.newRank.view.MonthFragment;

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
    public void requestList() {
        mModel.requestRank();
    }

    @Override
    public void changeRank(int rank,String token) {
        mModel.exchange(rank,token);
    }

    @Override
    public void haveList(List mList) {
        mView.haveList(mList);
    }

    @Override
    public void ListFail() {
        mView.ListNull();
    }

    @Override
    public void changeSuccess() {
        mView.changeSuccess();
    }

    @Override
    public void changeFail() {
        mView.changeFail();
    }

    @Override
    public void ListNull() {
        mView.ListNull();
    }

    @Override
    public void noCoin() {
        mView.noCoin();
    }

    @Override
    public void noRank() {
        mView.noRank();
    }

}
