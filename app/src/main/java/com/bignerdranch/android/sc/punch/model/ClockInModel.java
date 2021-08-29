package com.bignerdranch.android.sc.punch.model;

import com.bignerdranch.android.sc.punch.LabelPunch;
import com.bignerdranch.android.sc.punch.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.Message;
import com.bignerdranch.android.sc.punch.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClockInModel {
    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8:2333/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ClockInAPI mClockInAPI = mRetrofit.create(ClockInAPI.class);

    public void getClockInLabels(String token,ClockInResponseListener clockInResponseListener){
        mClockInAPI.getLabels(token).enqueue(new Callback<ResponseData<List<LabelPunch>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<LabelPunch>>> call, Response<ResponseData<List<LabelPunch>>> response) {
                clockInResponseListener.clockInRequestLabelSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseData<List<LabelPunch>>> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }

    public void toClockIn(String token, LabelPunchTitle clockInLabelTitle, ClockInResponseListener clockInResponseListener){
        mClockInAPI.toClockIn(token,clockInLabelTitle).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }

    public void removeLabel(String token, LabelPunchTitle clockInLabelTitle, ClockInResponseListener clockInResponseListener){
        mClockInAPI.removeLabel(token, clockInLabelTitle).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                clockInResponseListener.removeLabelSuccess("删除成功");
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }

    public void isClockInToday(String token, String url, ClockInResponseListener clockInResponseListener){
        mClockInAPI.isClockInToday(token,url).enqueue(new Callback<ResponseData<Boolean>>() {
            @Override
            public void onResponse(Call<ResponseData<Boolean>> call, Response<ResponseData<Boolean>> response) {
                clockInResponseListener.isClockInToday(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseData<Boolean>> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }
}
