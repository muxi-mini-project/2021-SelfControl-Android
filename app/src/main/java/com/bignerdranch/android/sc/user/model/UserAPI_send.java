package com.bignerdranch.android.sc.user.model;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.Bean.GoldHistory;
import com.bignerdranch.android.sc.user.Bean.Rank;
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
    Observable<Week> getWeekNumber(@Header("token") String token, @Path("month") int month);

    //
    @GET("user/goldhistory")
    Observable<GoldHistory> getGoldHistory(@Header("token") String token);

    //
    @GET("user/")
    Observable<User.DataDTO> mUser(@Header("token") String token);

    //
    @GET("punch/month")
    Observable<Report> getMonthReport(@Header("token") String token);

    //
    @PUT("user/")
    Observable<User> changName(@Header("token") String token, @Body User.DataDTO mUser);


    //
    @GET("list/history")
    Observable<Rank> getMyRank(@Header("token") String token);

    //
    @GET("api/v1/user/")
    Observable<User> getCurrentBackdrop(@Header("token")String token);


}
