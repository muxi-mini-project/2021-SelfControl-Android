package com.bignerdranch.android.sc.rank.newrank.api;

import java.util.List;

public interface MonthAPI {
    interface VP{
        void requestList();
        void changeRank(int rank, String token);
        void haveList(List mList);
        void ListFail();
        void changeSuccess();
        void changeFail();
        void ListNull();
        void noCoin();
        void noRank();
    }
    interface M{
        void requestRank();
        void exchange(int ranking, String token);
    }

}
