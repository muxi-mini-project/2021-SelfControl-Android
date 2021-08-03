package com.bignerdranch.android.sc.user.model;

import com.bignerdranch.android.sc.user.Bean.GoldHistory;
import com.bignerdranch.android.sc.user.View.CoinQueryActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GoldHistoryAPI{
    @GET("user/goldhistory")
    Call<List<GoldHistory>> getGoldHistory(@Header("token") String token);
}