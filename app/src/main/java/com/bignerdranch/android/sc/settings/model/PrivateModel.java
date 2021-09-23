package com.bignerdranch.android.sc.settings.model;

import androidx.annotation.NonNull;

import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.settings.contract.PrivateContract;
import com.bignerdranch.android.sc.settings.bean.Privacy;
import com.bignerdranch.android.sc.settings.presenter.PrivatePresenter;
import com.bignerdranch.android.sc.user.bean.Message;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PrivateModel implements PrivateContract.M {
    private PrivatePresenter mP;

    public PrivateModel(PrivatePresenter mP){
        this.mP = mP;
    }

    @Override
    public void request(Integer type, String token) {
        Privacy mPrivacy = new Privacy();
        mPrivacy.setPrivacy(type);
        NetUtil.getInstance().getApi().putPrivacy(mPrivacy,token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Message>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Message message) {

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
