package com.bignerdranch.android.sc.rank.newrank.model;

import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.rank.newrank.bean.ChangeRank;
import com.bignerdranch.android.sc.rank.newrank.presenter.MonthPresenter;
import com.bignerdranch.android.sc.rank.newrank.bean.RankItem;

import com.bignerdranch.android.sc.rank.newrank.api.MonthAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MonthModel implements MonthAPI.M {

    private MonthPresenter mP;

    public MonthModel(MonthPresenter monthP) {
        this.mP = monthP;
    }

    @Override
    public void requestRank() {
        List<RankItem.RankDataBean> mList = new ArrayList();
        NetUtil.getInstance().getApi().getMonth().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RankItem>() {
                    @Override
                    public void onSubscribe(@androidx.annotation.NonNull Disposable d) {

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
        NetUtil.getInstance().getApi().putMonth(token, new RankItem.RankDataBean(ranking))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangeRank>() {
                    @Override
                    public void onSubscribe(@androidx.annotation.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ChangeRank response) {
                        if(response.getCode()==203){
                            mP.noCoin();
                        }else if(response.getCode() == 200){
                            mP.changeSuccess();
                            requestRank();
                        }else if(response.getCode() == 201){
                            mP.noRank();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mP.changeFail();
                    }


                    @Override
                    public void onComplete() {

                    }
                });
    }
}
