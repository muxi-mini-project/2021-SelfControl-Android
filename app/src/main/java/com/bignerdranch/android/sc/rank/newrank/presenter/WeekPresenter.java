package com.bignerdranch.android.sc.rank.newrank.presenter;

import com.bignerdranch.android.sc.rank.newrank.api.WeekAPI;
import com.bignerdranch.android.sc.rank.newrank.model.WeekModel;
import com.bignerdranch.android.sc.rank.newrank.view.WeekFragment;

import java.util.List;

public class WeekPresenter implements WeekAPI.VP {
    private WeekModel mModel;
    private WeekFragment mView;

    public WeekPresenter(){
        this.mModel = getModelInstance();
    }
    public void bindView(WeekFragment mView){ //绑定View
        this.mView = mView;
    }
    public void unBindView(){
        this.mView = null;
    }

    public WeekModel getModelInstance() {
        return new WeekModel(this);
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

    @Override
    public void noCoin() {
        mView.noCoin();
    }

    @Override
    public void noRank() {
        mView.noRank();
    }
}
