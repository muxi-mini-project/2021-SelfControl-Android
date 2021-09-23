package com.bignerdranch.android.sc.rank.newrank.presenter;
import com.bignerdranch.android.sc.rank.newrank.api.MonthAPI;
import com.bignerdranch.android.sc.rank.newrank.model.MonthModel;
import com.bignerdranch.android.sc.rank.newrank.view.MonthFragment;

import java.util.List;

public class MonthPresenter implements MonthAPI.VP {

    private MonthModel mModel;
    private MonthFragment mView;

    public MonthPresenter(){
        this.mModel = getModelInstance();
    }
    public void bindView(MonthFragment mView){ //绑定View
        this.mView = mView;
    }
    public void unBindView(){
        this.mView = null;
    }

    public MonthModel getModelInstance() {
        return new MonthModel(this);
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
