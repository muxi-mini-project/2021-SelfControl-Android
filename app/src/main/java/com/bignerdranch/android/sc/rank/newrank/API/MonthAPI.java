package com.bignerdranch.android.sc.rank.newrank.API;

import com.bignerdranch.android.sc.rank.newrank.bean.ChangeRank;
import com.bignerdranch.android.sc.rank.newrank.bean.RankItem;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

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

    @PUT("list/month")
    Observable<ChangeRank> putMonth(@Header("token")String token, @Body RankItem.RankDataBean rankItem);

    @GET("lists/month/")
    Observable<RankItem> getMonth();
}
