package com.bignerdranch.android.sc.SeeUser.newSeeUser;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeeUserM implements SeeUserAPI.M {

    private SeeUserP mP;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8:2333/api/v1")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public SeeUserM(SeeUserP p) {
        this.mP = p;
    }

    @Override
    public List<LabelPunch> getList(String id) {
        List<LabelPunch> mList = new ArrayList<>();
        SeeUserAPI mApi = retrofit.create(SeeUserAPI.class);
        mApi.requestList(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LabelPunch>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull LabelPunch labelPunch) {
                        mList.add(labelPunch);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mP.Fail();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return mList;
    }
}
