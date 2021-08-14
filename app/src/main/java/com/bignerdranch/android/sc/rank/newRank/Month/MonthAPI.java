package com.bignerdranch.android.sc.rank.newRank.Month;

public interface MonthAPI {
    interface VP{
        void requestList();
        void changeRank();
        void haveList();
        void ListFail();
        void changeSuccess();
        void changeFail();
    }
    interface M{
        void requestRank();
        void exchange();
    }
}
