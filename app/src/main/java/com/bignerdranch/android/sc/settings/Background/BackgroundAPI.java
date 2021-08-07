package com.bignerdranch.android.sc.settings.Background;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface BackgroundAPI {
    interface M{
        void changeBackground(int click);
    }
    interface VP{
        void haveRequest(int click);
        void successChange(int click);
        void buyDialog(int click);
        void buyRequest(int click);
        void noCoin();
        void error();

    }
    @GET("/backdrops/")
    Observable<BackgroundItem.Background> getMyBack(@Header("token") String token);

    @PUT("/backdrop/")
    Observable<Response<BackgroundItem.Buy>> buyBack(@Header("token") String token, @Body BackgroundItem.Buy mBuy);


}
