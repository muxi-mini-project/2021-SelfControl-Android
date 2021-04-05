package com.bignerdranch.android.sc.label;

import com.bignerdranch.android.sc.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PunchAPI {
    @POST("/api/v1/punch/create")
    Call<Message> create(@Header("token") String token, @Body Punch mPunch);

    @DELETE("/api/v1/punch/")
    Call<Message> delete(@Header("token") String token, @Body Punch mPunch);

    @GET("/api/v1/punch")
    Call<List<Punch>> getMyPunch(@Header("token")String token);
}
