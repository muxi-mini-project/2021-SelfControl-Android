package com.bignerdranch.android.sc.SeeUser.newSeeUser;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SeeUserAPI {

    interface VP{
        List<LabelPunch> getLabel(String id);
        void Fail();
    }

    interface M{
        List<LabelPunch> getList(String id);

    }

    @GET("/punch/punch/{id}")
    Observable<LabelPunch> requestList(@Path("id")String id);
}
