package com.bignerdranch.android.sc.rank.newRank.Month;

import com.bignerdranch.android.sc.rank.newRank.RankItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface MonthAPI {
    interface VP{
        List requestList();
        void changeRank();
        void haveList();
        void ListFail();
        void changeSuccess();
        void changeFail();
    }
    interface M{
        void requestRank();
        void exchange(int ranking);
    }

    @PUT
    Observable<RankItem> putMonth(@Header("token")String token, @Body RankItem rankItem);
}
