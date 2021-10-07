package com.bignerdranch.android.sc.clockpage.clockpresenter;

public interface MainContract {

    interface View {
        void showWhiteFlower();

        void showSmileFlower();

        void serPresenter(MainContract.Presenter presenter);

        void showText();
    }

    interface Presenter {
        void loadFlower(String token, int day);
    }

}

