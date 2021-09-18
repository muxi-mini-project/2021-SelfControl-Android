package com.bignerdranch.android.sc.seeuser;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SeeUserAPI {

    interface VP{
        void getLabel(String id);
        void Fail();
        void haveList();
        void listNull();
    }

    interface M{
        void getList(String id);

    }

    @GET("punch/punch/{id}")
    Observable<UserPunch> requestList(@Path("id")String id);
}
