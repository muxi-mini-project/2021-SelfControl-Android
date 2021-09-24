package com.bignerdranch.android.sc.settings.model;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.rank.newrank.bean.Message;
import com.bignerdranch.android.sc.settings.contract.BackgroundContract;
import com.bignerdranch.android.sc.settings.bean.BackgroundItem;
import com.bignerdranch.android.sc.settings.bean.MyBackdrops;
import com.bignerdranch.android.sc.settings.presenter.BackgroundPresenter;

import io.reactivex.Observer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class BackgroundModel implements BackgroundContract.M {

    private BackgroundPresenter mP;
    private BackgroundItem.Buy mBuy = new BackgroundItem.Buy();

    public BackgroundModel(BackgroundPresenter backgroundP) {
        this.mP = backgroundP;
    }

    int[] have = new int[]{0, 0, 0, 0, 0, 0, 0};
    int i = 1;

    public void haveRequest(int click, String token) {
        i = 1;
        NetUtil.getInstance().getApi().getMyBack(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBackdrops>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MyBackdrops myBackdrops) {
                        have[1] = 1;
                        have[2] = myBackdrops.getData().getB_1();
                        have[3] = myBackdrops.getData().getB_2();
                        have[4] = myBackdrops.getData().getB_3();
                        have[5] = myBackdrops.getData().getB_4();
                        have[6] = myBackdrops.getData().getB_5();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (have[click] == 1) {
                            mP.successChange(click);
                            changeMyBack(click);
                            mP.changeImage(have);
                        } else {
                            mP.buyDialog(click, have);
                        }

                        for (int i = 1; i < 6; i++) {
                            have[i] = 0;
                        }
                    }
                });
    }

    public void changeMyBack(int click) {
        User.DataDTO u = new User.DataDTO(click);
        NetUtil.getInstance().getApi().putMyBack(u, token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Message>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Message msg) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mP.error();

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void buyRequest(int click, String token, int[] have) {
        mBuy.setBackdrop_id(click);
        NetUtil.getInstance().getApi().buyBack(token, mBuy).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BackgroundItem.Buy>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<BackgroundItem.Buy> response) {
                        if (response.code() == 200) {
                            have[click] = 1;
                            mP.successChange(click);
                            changeMyBack(click);
                            mP.changeImage(have);
                        } else if (response.code() == 203) {
                            mP.noCoin();
                        } else {
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
    public void haveRq(String token) {
        i = 1;
        NetUtil.getInstance().getApi().getMyBack(token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyBackdrops>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MyBackdrops myBackdrops) {
                        have[1] = 1;
                        have[2] = myBackdrops.getData().getB_1();
                        have[3] = myBackdrops.getData().getB_2();
                        have[4] = myBackdrops.getData().getB_3();
                        have[5] = myBackdrops.getData().getB_4();
                        have[6] = myBackdrops.getData().getB_5();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mP.changeImage(have);
                        for (int i = 1; i < 6; i++) {
                            have[i] = 0;
                        }
                    }
                });

    }


}
