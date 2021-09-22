package com.bignerdranch.android.sc.settings.model;

import com.bignerdranch.android.sc.settings.api.PrivateAPI;
import com.bignerdranch.android.sc.settings.bean.Privacy;
import com.bignerdranch.android.sc.settings.presenter.PrivatePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrivateModel implements PrivateAPI.M {
    private PrivatePresenter mP;

    public PrivateModel(PrivatePresenter mP){
        this.mP = mP;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8:2333/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @Override
    public void request(Integer type,String token) {
        PrivateAPI mApi = retrofit.create(PrivateAPI.class);
        Privacy mPrivacy = new Privacy();
        mPrivacy.setPrivacy(type);
        mApi.putPrivacy(mPrivacy,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<com.bignerdranch.android.sc.rank.newrank.bean.Message>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull com.bignerdranch.android.sc.rank.newrank.bean.Message message) {

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
