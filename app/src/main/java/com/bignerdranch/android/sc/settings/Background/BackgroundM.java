package com.bignerdranch.android.sc.settings.Background;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackgroundM implements BackgroundAPI.M {

    private BackgroundP mP;
    private BackgroundItem.Buy mBuy;
    public BackgroundM(BackgroundP backgroundP) {
        this.mP = backgroundP;
    }
    int[] have = new int[]{0,0,0,0,0};
    int i = 1;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    public void haveRequest(int click,String token){

        BackgroundAPI mApi = retrofit.create(BackgroundAPI.class);
        mApi.getMyBack(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BackgroundItem.Background>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BackgroundItem.Background background) {
                        have[i] = background.get(i++);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

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
        if(have[click] == 1){
            mP.successChange(click);
        }else{
            mP.buyDialog(click);
        }
        for(int i = 0 ; i < 5 ; i++){
            have[i] = 0;
        }
    }
}
