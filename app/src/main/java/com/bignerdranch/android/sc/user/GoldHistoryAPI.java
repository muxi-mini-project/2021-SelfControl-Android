package com.bignerdranch.android.sc.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface GoldHistoryAPI{
    @GET("user/history")
    Call<List<CoinQueryActivity.GoldHistory>> getGoldHistory(@Header("token") String token);
}