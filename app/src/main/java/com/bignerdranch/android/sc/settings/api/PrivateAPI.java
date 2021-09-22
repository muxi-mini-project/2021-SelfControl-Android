package com.bignerdranch.android.sc.settings.api;

import com.bignerdranch.android.sc.rank.newrank.bean.Message;
import com.bignerdranch.android.sc.settings.bean.Privacy;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface PrivateAPI {
    interface VP{
        void trueRequest(String token);
        void falseRequest(String token);
        void success();
        void fail();
    }
    interface M{
        void request(Integer type,String token);
    }

    @PUT("user/")
    Observable<Message> putPrivacy(@Body Privacy mP, @Header("token") String token);
}
