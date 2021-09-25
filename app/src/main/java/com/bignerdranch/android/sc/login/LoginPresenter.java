package com.bignerdranch.android.sc.login;


import com.bignerdranch.android.sc.login.LoginActivity;
import com.bignerdranch.android.sc.login.LoginAPI;
import com.bignerdranch.android.sc.login.LoginModel;
import com.bignerdranch.android.sc.login.LoginResponse;

import retrofit2.Response;

public class LoginPresenter implements LoginAPI.VP{

    private LoginActivity mView;
    private LoginModel mModel;

    public LoginPresenter(){
        this.mModel = getModelInstance();
    }

    public LoginModel getModelInstance(){
        return new LoginModel(this);
    }

    public void bindView(LoginActivity mView) {
        this.mView = mView;
    }

    @Override
    public void login(String id, String password){
        mModel.request(id, password);
    }

    @Override
    public void fail(){
        mView.fail();
    }

    @Override
    public void error(Throwable throwable){
        mView.error(throwable);
    }

    @Override
    public void onSuccess(Response<LoginResponse> response){
        mView.onSuccess(response);
    }


}
