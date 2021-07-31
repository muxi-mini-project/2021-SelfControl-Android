package com.bignerdranch.android.sc.user.model;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.Bean.Report;
import com.bignerdranch.android.sc.user.Bean.Week;
import com.bignerdranch.android.sc.user.View.CoinQueryActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
/**
 * 这个类专门用来处理网络请求的发送部分
 */

public interface UserAPI_send {
    @GET("punch/week/{month}")
    Call<List<Week>> getWeekNumber(@Header("token") String token, @Path("month") int month);


    @GET("user/goldhistory")
    Call<List<CoinQueryActivity.GoldHistory>> getGoldHistory(@Header("token") String token);

    @GET("user/")
    Call<User> mUser(@Header("token") String token);


    @GET("punch/month")
    Call<List<Report>> getMonthReport(@Header("token") String token);

    @PUT("user/")
    Call<User> changName(@Header("token")String token, @Body User mUser);

}
