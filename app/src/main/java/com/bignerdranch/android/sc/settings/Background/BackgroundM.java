package com.bignerdranch.android.sc.settings.Background;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BackgroundM implements BackgroundAPI.M {

    private BackgroundP mP;
    public BackgroundM(BackgroundP backgroundP) {
        this.mP = backgroundP;
    }


    public void request(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        API mApi = retrofit.create(API.class);
        mApi.getList(language, "weekly").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ItemsBean<ItemsBean.Items>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ItemsBean<ItemsBean.Items> itemsItemsBean) {
                        mList.clear();
                        for (int i = 0; i < itemsItemsBean.getItems().size(); i++) {
                            mList.add(itemsItemsBean.getItems().get(i));
                        }
                        mPresenter.onSuccess(mList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mP.error();
                    }

                    @Override
                    public void onComplete() {
                        mPresenter.setData(mList);
                    }
                });
    }
    @Override
    public void changeBackground() {

    }
}
