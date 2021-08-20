package com.bignerdranch.android.sc.rank.newRank.Week;

import com.bignerdranch.android.sc.rank.newRank.RankItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface WeekAPI {
    interface VP{
        List<RankItem> requestList();
        void changeRank(int rank, String token);
        void haveList();
        void ListFail();
        void changeSuccess();
        void changeFail();
        void ListNull();
    }
    interface M{
        List<RankItem> requestRank();
        void exchange(int ranking, String token);
    }

    @PUT("/list/week/")
    Observable<RankItem> putWeek(@Header("token")String token, @Body RankItem rankItem);

    @GET("/lists/week/")
    Observable<RankItem> getWeek();
}
