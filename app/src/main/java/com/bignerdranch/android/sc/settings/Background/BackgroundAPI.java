package com.bignerdranch.android.sc.settings.Background;


import com.bignerdranch.android.sc.settings.Background.Items.myBackground1;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface BackgroundAPI {
    interface M{
        void changeBackground();
    }
    interface VP{
        void onRequest();
        void successChange();
        void noCoin();
        void error();

    }
    @GET("/backdrops")
    Observable<myBackground1> getMyBack(@Header("token") String token);

    @GET("/backdrop")
    Observable<Items.Background1> getPrice(@Header("token") String token);
}
