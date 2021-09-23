package com.bignerdranch.android.sc.settings.contract;

public interface BackgroundContract {
    interface M{
        void haveRequest(int click,String token);
        void buyRequest(int click,String token,int[] have);
        void haveRq(String token);
    }
    interface VP{
        void haveRq(String token);
        void haveRequest(int click, String token);
        void successChange(int click);
        void buyDialog(int click,int[] have);
        void buyRequest(int click, String token,int[] have);
        void changeImage(int[] have);

        void noCoin();
        void error();

    }
}
