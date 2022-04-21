package com.bignerdranch.android.sc.clockpage.model;

import com.bignerdranch.android.sc.net.NetUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource implements FlowerDataSource {

    private static RemoteDataSource INSTANCE;
    public static Boolean STATUS = false;

    public static RemoteDataSource getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getFlowerStatus(String token, int day, LoadFlowerCallback callback) {
        NetUtil.getInstance().getApi().ifDayAllPunch(token, day).enqueue(new Callback<FlowerResponse>() {
            @Override
            public void onResponse(Call<FlowerResponse> call, Response<FlowerResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMsg().equals("已全部完成且数量为返回的值")) {
                        callback.onSmileFlowerLoaded();
                    } else {
                        //callback.onWhiteFlowerLoaded();
                    }
                } else {
                    callback.getText();
                }
            }

            @Override
            public void onFailure(Call<FlowerResponse> call, Throwable t) {
                callback.onDataNotAvailable();
            }

        });
    }
}
