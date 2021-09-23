package com.bignerdranch.android.sc.rank.newrank.api;

import com.bignerdranch.android.sc.rank.newrank.bean.ChangeRank;
import com.bignerdranch.android.sc.rank.newrank.bean.RankItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface WeekAPI {
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

    @PUT("list/week")
    Observable<ChangeRank> putWeek(@Header("token")String token, @Body RankItem.RankDataBean rankItem);

    @GET("lists/week/")
    Observable<RankItem> getWeek();
}
