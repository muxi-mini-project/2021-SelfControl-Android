package com.bignerdranch.android.sc.clockpage.clockpresenter;

public interface MainContract {

    interface View{
        void showWhiteFlower();

        void showRedFlower();
    }

    interface Presenter{
        void loadFlower();
    }

}
