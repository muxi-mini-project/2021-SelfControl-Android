package com.bignerdranch.android.sc.seeuser;

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

public class SeeUserModel implements SeeUserAPI.M {

    private SeeUserPresenter mP;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8:2333/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public SeeUserModel(SeeUserPresenter p) {
        this.mP = p;
    }

    @Override
    public void getList(String id) {
        List<UserPunch.DataBean> mList = new ArrayList<>();
        SeeUserAPI mApi = retrofit.create(SeeUserAPI.class);
        mApi.requestList(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserPunch>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserPunch body) {
                        mList.addAll(body.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mP.Fail();
                    }

                    @Override
                    public void onComplete() {
                        if(mList != null ){
                            mP.haveList(mList);
                        }else {
                            mP.listNull();
                        }
                    }
                });
    }
}
