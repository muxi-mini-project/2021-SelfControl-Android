package com.bignerdranch.android.sc.label;

import com.bignerdranch.android.sc.punch.ResponseData;
import com.bignerdranch.android.sc.user.bean.Message;
import com.bignerdranch.android.sc.user.bean.Data;
import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PunchAPI {
    @POST("/api/v1/punch/create")
    Call<Message> create(@Header("token") String token, @Body Punch mPunch);

    @HTTP(method = "DELETE", path = "api/v1/punch/", hasBody = true)
    Call<Message> delete(@Header("token") String token, @Body Punch mPunch);

    @GET("/api/v1/punch/")
    Call<ResponseData<List<LabelPunch>>> getPunch(@Header("token")String token);

    @POST("/api/v1/punch/")
    Call<Message> punch(@Header("token") String token,@Body Punch mPUnch);
}
