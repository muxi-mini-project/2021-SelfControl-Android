package com.bignerdranch.android.sc.ClockPage.ClockPresenter;

public interface MainContract {

    interface View{
        void showWhiteFlower();

        void showRedFlower();
    }

    interface Presenter{
        void loadFlower();
    }

}
