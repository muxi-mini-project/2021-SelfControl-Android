package com.bignerdranch.android.sc.punch.model;

import com.bignerdranch.android.sc.punch.LabelPunch;
import com.bignerdranch.android.sc.punch.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.Message;
import com.bignerdranch.android.sc.punch.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ClockInAPI {
    @GET("api/v1/punch/")
    Call<ResponseData<List<LabelPunch>>> getLabels(@Header("token") String token);

    @POST("api/v1/punch/")
    Call<Message> toClockIn(@Header("token") String token,
                            @Body LabelPunchTitle clockInLabelTitle);

    @HTTP(method = "DELETE", path = "api/v1/punch/", hasBody = true)
    Call<Message> removeLabel(@Header("token") String token,
                              @Body LabelPunchTitle clockInLabelTitle);

    @GET("")
    Call<ResponseData<Boolean>> isClockInToday(@Header("token") String token,
                                               @Url String url);
}
