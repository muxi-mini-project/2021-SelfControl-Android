package com.bignerdranch.android.sc.label;

import com.bignerdranch.android.sc.Message;
import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PunchAPI {
    @POST("/api/v1/punch/create")
    Call<Message> create(@Header("token") String token, @Body Punch mPunch);

    @HTTP(method = "DELETE", path = "api/v1/punch/", hasBody = true)
    Call<Message> delete(@Header("token") String token, @Body Punch mPunch);

    @GET("/api/v1/punch/")
    Call<List<LabelPunch>> getPunch(@Header("token")String token);
}
