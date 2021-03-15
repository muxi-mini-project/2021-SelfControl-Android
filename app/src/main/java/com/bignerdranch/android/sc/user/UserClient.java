package com.bignerdranch.android.sc.user;

import com.bignerdranch.android.sc.login.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface UserClient{
    @GET("user/")
    @Headers("token")
    Call<User> mUser();
}