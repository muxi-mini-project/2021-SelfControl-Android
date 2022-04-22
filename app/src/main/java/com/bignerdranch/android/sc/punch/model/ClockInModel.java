package com.bignerdranch.android.sc.punch.model;

import androidx.annotation.NonNull;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.punch.bean.LabelPunch;
import com.bignerdranch.android.sc.punch.bean.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.bean.SingleMessage;
import com.bignerdranch.android.sc.punch.bean.ResponseData;
import com.bignerdranch.android.sc.punch.presenter.CallBackInPresenter;
import com.bignerdranch.android.sc.punch.view.CallBackInView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClockInModel {
    public void getFlowerStatus(String token, int day, CallBackInPresenter.IfAllPunchCallBack ifAllPunchCallBack) {
        NetUtil.getInstance().getApi().getDatAllPunch(token,day).enqueue(new Callback<ResponseData<Integer>>() {
            @Override
            public void onResponse(Call<ResponseData<Integer>> call, Response<ResponseData<Integer>> response) {
                assert response.body() != null;
                ifAllPunchCallBack.setIsAllPunch(response.body().getData() > 0);
            }

            @Override
            public void onFailure(Call<ResponseData<Integer>> call, Throwable t) {

            }
        });
    }

    public void getClockInLabels(String token,String url,CallBackInPresenter.LabelListCallBack labelListCallBack){
        NetUtil.getInstance().getApi().getLabels(token,url).enqueue(new Callback<ResponseData<List<LabelPunch>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<LabelPunch>>> call, Response<ResponseData<List<LabelPunch>>> response) {
                if(response.body() != null)
                    labelListCallBack.setList(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseData<List<LabelPunch>>> call, Throwable t) {

            }
        });
    }

    public void toClockIn(String token, LabelPunchTitle clockInLabelTitle, CallBackInPresenter.ClockInCallBack callBack){
        NetUtil.getInstance().getApi().toClockIn(token,clockInLabelTitle).enqueue(new Callback<SingleMessage>() {
            @Override
            public void onResponse(Call<SingleMessage> call, Response<SingleMessage> response) {
                callBack.setIsClockIn(true);
            }

            @Override
            public void onFailure(Call<SingleMessage> call, Throwable t) {
                callBack.setIsClockIn(false);
            }
        });
    }

    public void removeLabel(String token, LabelPunchTitle clockInLabelTitle, CallBackInPresenter.RemoveLabelCallback callback){
        NetUtil.getInstance().getApi().removeLabel(token, clockInLabelTitle).enqueue(new Callback<SingleMessage>() {
            @Override
            public void onResponse(Call<SingleMessage> call, Response<SingleMessage> response) {
                callback.setRemoveMessage("删除成功");
            }

            @Override
            public void onFailure(Call<SingleMessage> call, Throwable t) {
                callback.setRemoveMessage(t.getMessage());
            }
        });
    }

    public void setBG(CallBackInPresenter.BGCallBack bgCallBack, String token){
        NetUtil.getInstance().getApi().userInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        if (user != null) {
                            bgCallBack.setBG(user.getData().getCurrent_backdrop());
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

    public void getViewDayPunch(CallBackInPresenter.ViewDayLabelCallBack viewDayPunchCallBack, String url, String token){
        NetUtil.getInstance().getApi().getClockDayList(token, url).enqueue(new Callback<ResponseData<List<LabelPunch>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<LabelPunch>>> call, Response<ResponseData<List<LabelPunch>>> response) {
                viewDayPunchCallBack.setList(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseData<List<LabelPunch>>> call, Throwable t) {

            }
        });
    }
}
