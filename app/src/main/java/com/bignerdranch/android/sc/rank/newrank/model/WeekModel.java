package com.bignerdranch.android.sc.rank.newrank.model;

import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.rank.newrank.api.WeekAPI;
import com.bignerdranch.android.sc.rank.newrank.bean.ChangeRank;
import com.bignerdranch.android.sc.rank.newrank.bean.RankItem;
import com.bignerdranch.android.sc.rank.newrank.presenter.WeekPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WeekModel implements WeekAPI.M {
    private WeekPresenter mP;

    public WeekModel(WeekPresenter weekP) {
        this.mP = weekP;
    }

    @Override
    public void requestRank() {
        List<RankItem.RankDataBean> mList = new ArrayList();
        NetUtil.getInstance().getApi().getWeek().subscribeOn(Schedulers.io())
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
        NetUtil.getInstance().getApi().putWeek(token, new RankItem.RankDataBean(ranking))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ChangeRank>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

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
