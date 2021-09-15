package com.bignerdranch.android.sc.settings.API;


import com.bignerdranch.android.sc.settings.bean.BackgroundItem;
import com.bignerdranch.android.sc.settings.bean.MyBackdrops;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface BackgroundAPI {
    interface M{
        void changeBackground(int click,String token);
    }
    interface VP{
        void haveRequest(int click, String token);
        void successChange(int click);
        void buyDialog(int click);
        void buyRequest(int click, String token);

        void noCoin();
        void error();

    }
    @GET("backdrops")
    Observable<MyBackdrops> getMyBack(@Header("token") String token);

    @PUT("backdrop")
    Observable<Response<BackgroundItem.Buy>> buyBack(@Header("token") String token, @Body BackgroundItem.Buy mBuy);


}
