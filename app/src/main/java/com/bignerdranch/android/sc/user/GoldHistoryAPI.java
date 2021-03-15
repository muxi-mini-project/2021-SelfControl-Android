package com.bignerdranch.android.sc.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GoldHistoryAPI{
    @GET("user/history")
    @Headers("token")
    Call<List<CoinQueryActivity.GoldHistory>> getGoldHistory();
}