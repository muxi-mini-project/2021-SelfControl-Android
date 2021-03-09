package com.bignerdranch.android.sc.login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {
    //Call<User> getCall (@Field("student_id") String param1, @Field("password") String param2);

    @POST("user")
    Call<LoginResponse> getCall (@Body user user);
}