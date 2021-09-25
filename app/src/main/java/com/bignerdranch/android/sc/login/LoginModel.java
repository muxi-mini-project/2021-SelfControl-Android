package com.bignerdranch.android.sc.login;

import android.content.Intent;

import com.bignerdranch.android.sc.net.NetUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModel implements LoginAPI.M{

    private LoginPresenter mPresenter;



    public LoginModel(LoginPresenter mPresenter){
        this.mPresenter = mPresenter;
    }

    @Override
    public void request(String id,String password){
        NetUtil.getInstance().getApi().getCall(new User.DataDTO(id,password)).enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    mPresenter.onSuccess(response);
                }else {
                    mPresenter.fail();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                mPresenter.error(throwable);
            }

        });

    }
}
