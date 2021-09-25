package com.bignerdranch.android.sc.login;


import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginAPI {

    interface M{
        void request(String id, String password);
    }

    interface VP{
        default void login(String id, String password){}
        void onSuccess(Response<LoginResponse> response);
        void error(Throwable throwable);
        void fail();
    }

}
