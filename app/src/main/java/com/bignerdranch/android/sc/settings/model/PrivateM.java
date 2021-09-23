package com.bignerdranch.android.sc.settings.model;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.settings.API.PrivateAPI;
import com.bignerdranch.android.sc.settings.presenter.PrivateP;

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
    private User user = new User();

    public PrivateM(PrivateP mP){
        this.mP = mP;
    }

    @Override
    public void request(Integer type,String token) {
        user.setData(new User.DataDTO(type));
        NetUtil.getInstance().getApi().putPrivacy(user,token)
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
