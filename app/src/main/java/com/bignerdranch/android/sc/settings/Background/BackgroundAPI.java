package com.bignerdranch.android.sc.settings.Background;

public interface BackgroundAPI {
    interface M{
        void changeBackground();
    }
    interface VP{
        void onRequest();
        void successChange();
        void noCoin();
        void error();

    }
}
