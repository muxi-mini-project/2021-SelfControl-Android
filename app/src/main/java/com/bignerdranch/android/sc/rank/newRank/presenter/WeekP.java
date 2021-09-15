package com.bignerdranch.android.sc.rank.newRank.presenter;

import com.bignerdranch.android.sc.rank.newRank.API.WeekAPI;
import com.bignerdranch.android.sc.rank.newRank.model.WeekM;
import com.bignerdranch.android.sc.rank.newRank.view.WeekFragment;

import java.util.List;

public class WeekP implements WeekAPI.VP {
    private WeekM mModel;
    private WeekFragment mView;

    public WeekP(){
        this.mModel = getModelInstance();
    }
    public void bindView(WeekFragment mView){ //绑定View
        this.mView = mView;
    }
    public void unBindView(){
        this.mView = null;
    }

    public WeekM getModelInstance() {
        return new WeekM(this);
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
        mView.ListFail();
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
}
