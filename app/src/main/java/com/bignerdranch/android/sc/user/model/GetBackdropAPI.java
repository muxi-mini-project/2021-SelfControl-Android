package com.bignerdranch.android.sc.user.model;

import com.bignerdranch.android.sc.login.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface GetBackdropAPI {
    @GET("api/v1/user/")
    Call<User> getCurrentBackdrop(@Header("token")String token);
}
