package com.bignerdranch.android.sc.user.model;

import com.bignerdranch.android.sc.user.Bean.Week;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GetWeekAPI {
    @GET("punch/week/{month}")
    Call<List<Week>> getWeekNumber(@Header("token") String token, @Path ("month") int month);
}
