package com.bignerdranch.android.sc.clockpage.model;

import com.bignerdranch.android.sc.clockpage.RetrofitApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements FlowerDataSource {

    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getINSTANCE(){
        if (INSTANCE == null){
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getFlowerStatus(String token,int day,LoadFlowerCallback callback) {

        RetrofitApi api = new Retrofit.Builder()
                .baseUrl("http://39.99.53.8:2333/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(RetrofitApi.class);

        Call<FlowerResponse> call = api.ifDayAllPunch(token,day);

        call.enqueue(new Callback<FlowerResponse>() {
            @Override
            public void onResponse(Call<FlowerResponse> call, Response<FlowerResponse> response) {
                if(response.body().getMsg().equals("已全部完成且数量为返回的值")){
                    callback.onSmileFlowerLoaded();
                }else{
                    callback.onWhiteFlowerLoaded();
                }
            }

            @Override
            public void onFailure(Call<FlowerResponse> call, Throwable t) {
                callback.onDataNotAvaliable();
            }

        });
    }
}
