package com.bignerdranch.android.sc.user.model;

import com.bignerdranch.android.sc.login.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface UserClient{
    @GET("user/")
    Call<User> mUser(@Header("token") String token);
}