package com.bignerdranch.android.sc.settings.model;

import com.bignerdranch.android.sc.settings.API.BackgroundAPI;
import com.bignerdranch.android.sc.settings.bean.BackgroundItem;
import com.bignerdranch.android.sc.settings.bean.MyBackdrops;
import com.bignerdranch.android.sc.settings.presenter.BackgroundP;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class  BackgroundM implements BackgroundAPI.M {

    private BackgroundP mP;
    private BackgroundItem.Buy mBuy = new BackgroundItem.Buy();
    public BackgroundM(BackgroundP backgroundP) {
        this.mP = backgroundP;
    }
    int[] have = new int[]{0,0,0,0,0,0};
    int i = 1;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8:2333/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    public synchronized void haveRequest(int click,String token){
        i=1;
        BackgroundAPI mApi = retrofit.create(BackgroundAPI.class);
        mApi.getMyBack(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBackdrops>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MyBackdrops myBackdrops) {
                        have[0] = 1;
                        have[1] = myBackdrops.getData().getB_1();
                        have[2] = myBackdrops.getData().getB_2();
                        have[3] = myBackdrops.getData().getB_3();
                        have[4] = myBackdrops.getData().getB_4();
                        have[5] = myBackdrops.getData().getB_5();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if(have[click] == 1){
                            mP.successChange(click);
                        }else{
                            mP.buyDialog(click);
                        }

                        for(int i = 1 ; i < 6 ; i++){
                            have[i] = 0;
                        }
                    }
                });
    }

    public void buyRequest(int click,String token){
        BackgroundAPI mApi = retrofit.create(BackgroundAPI.class);
        mBuy.setBackdrop_id(click);
        mApi.buyBack(token,mBuy).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BackgroundItem.Buy>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BackgroundItem.Buy> response) {
                        if(response.code() == 200){
                            mP.successChange(click);
                        }else if(response.code() == 203){
                            mP.noCoin();
                        }else{
                            mP.error();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void changeBackground(int click,String token) {
        haveRequest(click,token);

    }
}
