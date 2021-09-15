package com.bignerdranch.android.sc.rank.newRank.model;

import com.bignerdranch.android.sc.rank.newRank.API.WeekAPI;
import com.bignerdranch.android.sc.rank.newRank.bean.RankItem;
import com.bignerdranch.android.sc.rank.newRank.presenter.WeekP;

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

public class WeekM implements WeekAPI.M {
    private WeekP mP;

    public WeekM(WeekP weekP) {
        this.mP = weekP;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8:2333/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    WeekAPI mApi = retrofit.create(WeekAPI.class);

    @Override
    public void requestRank() {
        List<RankItem.RankDataBean> mList = new ArrayList();
        mApi.getWeek().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RankItem>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull RankItem rankItem) {
                        mList.addAll(rankItem.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mP.ListFail();
                    }

                    @Override
                    public void onComplete() {
                        if(mList.size() == 0){
                            mP.ListNull();
                        }else{
                            mP.haveList(mList);
                        }
                    }
                });
    }

    @Override
    public void exchange(int ranking,String token) {
        mApi.putWeek(token, new RankItem.RankDataBean(ranking))
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
