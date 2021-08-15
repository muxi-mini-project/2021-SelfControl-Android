package com.bignerdranch.android.sc.rank.newRank.Month;

import com.bignerdranch.android.sc.rank.newRank.RankItem;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MonthM implements MonthAPI.M{

    private MonthP mP;
    public MonthM(MonthP monthP) {
        this.mP = monthP;
    }
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    @Override
    public void requestRank() {

    }

    @Override
    public void exchange(int ranking) {
       MonthAPI mApi = retrofit.create(MonthAPI.class);
       mApi.putMonth(token,new RankItem(ranking))
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Observer<RankItem>() {
                   @Override
                   public void onSubscribe(@NonNull Disposable d) {

                   }

                   @Override
                   public void onNext(@NonNull RankItem rankItem) {

                   }

                   @Override
                   public void onError(@NonNull Throwable e) {
                       mP.changeFail();
                   }

                   @Override
                   public void onComplete() {
                       mP.changeSuccess();
                   }
               });
    }
}
