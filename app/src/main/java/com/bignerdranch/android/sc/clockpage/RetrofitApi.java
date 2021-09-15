package com.bignerdranch.android.sc.clockpage;

import com.bignerdranch.android.sc.clockpage.model.FlowerResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RetrofitApi {

    @GET("api/v1/punch/all/{day}/")
    Call<FlowerResponse> ifDayAllPunch(@Header("token") String token, @Path("day")int day);

}
