package com.bignerdranch.android.sc.settings.Private;

import com.bignerdranch.android.sc.login.User;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

public interface PrivateAPI {
    interface VP{
        void trueRequest();
        void falseRequest();
        void success();
        void fail();
    }
    interface M{
        void request(int type);
    }

    @PUT("/user")
    Observable<User> putPrivacy(@Body User mUser, @Header("token") String token);
}
