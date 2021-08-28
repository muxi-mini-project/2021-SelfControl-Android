package com.bignerdranch.android.sc.settings.Private;

import com.bignerdranch.android.sc.login.User;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrivateM implements PrivateAPI.M {
    private PrivateP mP;
    public PrivateM(PrivateP mP){
        this.mP = mP;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8:2333/api/v1")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @Override
    public void request(int type,String token) {
        PrivateAPI mApi = retrofit.create(PrivateAPI.class);
        mApi.putPrivacy(new User(type),token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mP.fail();
                    }

                    @Override
                    public void onComplete() {
                        mP.success();
                    }
                });

    }
}
