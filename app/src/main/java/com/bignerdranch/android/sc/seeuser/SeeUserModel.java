package com.bignerdranch.android.sc.seeuser;

import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.seeuser.bean.UserPunch;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SeeUserModel implements SeeUserContract.M {

    private SeeUserPresenter mP;

    public SeeUserModel(SeeUserPresenter p) {
        this.mP = p;
    }

    @Override
    public void getList(String id) {
        List<UserPunch.DataBean> mList = new ArrayList<>();
        NetUtil.getInstance().getApi().requestList(id).subscribeOn(Schedulers.io())
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
