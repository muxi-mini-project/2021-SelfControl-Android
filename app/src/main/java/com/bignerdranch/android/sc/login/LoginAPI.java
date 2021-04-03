package com.bignerdranch.android.sc.login;

import retrofit2.Call;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface  LoginAPI {
    @POST("user/")
    Call<LoginResponse> getCall (@Body User user);
}