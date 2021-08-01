package com.bignerdranch.android.sc.user.model;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.Bean.Report;
import com.bignerdranch.android.sc.user.Bean.Week;
import com.bignerdranch.android.sc.user.View.CoinQueryActivity;
import com.bignerdranch.android.sc.user.View.RankQueryActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 这个类专门用来处理网络请求的发送部分的api接口
 */

public interface UserAPI_send {
    //GetWeekAPI部分
    @GET("punch/week/{month}")
    Observable<List<Week>> getWeekNumber(@Header("token") String token, @Path("month") int month);

    //
    @GET("user/goldhistory")
    Observable<List<CoinQueryActivity.GoldHistory>> getGoldHistory(@Header("token") String token);

    //
    @GET("user/")
    Observable<User> mUser(@Header("token") String token);

    //
    @GET("punch/month")
    Call<List<Report>> getMonthReport(@Header("token") String token);

    //
    @PUT("user/")
    Call<User> changName(@Header("token") String token, @Body User mUser);


    //
    @GET("list/history")
    Call<RankQueryActivity.Rank> getMyRank(@Header("token") String token);


}
