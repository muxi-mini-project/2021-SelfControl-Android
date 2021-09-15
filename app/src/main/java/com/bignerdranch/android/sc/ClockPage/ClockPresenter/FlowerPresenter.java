package com.bignerdranch.android.sc.clockpage.clockpresenter;

import com.bignerdranch.android.sc.clockpage.model.FlowerDataSource;
import com.bignerdranch.android.sc.clockpage.model.RemoteDataSource;

public class FlowerPresenter implements MainContract.Presenter{

    private final MainContract.View mView;

    private final RemoteDataSource mData;

    public FlowerPresenter(MainContract.View mView, RemoteDataSource mData) {
        this.mView = mView;
        this.mData = mData;
        mView.serPresenter(this);
    }

    @Override
    public void loadFlower(String token,int day) {
        mData.getFlowerStatus(token, day, new FlowerDataSource.LoadFlowerCallback() {
            @Override
            public void onWhiteFlowerLoaded() {
                mView.showWhiteFlower();
            }

            @Override
            public void onSmileFlowerLoaded() {
                mView.showSmileFlower();
            }

            @Override
            public void onDataNotAvaliable() {
                mView.showWhiteFlower();
            }
        });
    }
}
