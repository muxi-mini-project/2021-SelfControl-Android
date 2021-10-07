package com.bignerdranch.android.sc.clockpage.model;

public interface FlowerDataSource {

    interface LoadFlowerCallback{

        void onWhiteFlowerLoaded();

        void onSmileFlowerLoaded();

        void onDataNotAvaliable();

        void getText();
    }

    void getFlowerStatus(String token, int day, LoadFlowerCallback callback);
}
