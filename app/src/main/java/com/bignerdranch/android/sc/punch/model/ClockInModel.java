package com.bignerdranch.android.sc.punch.model;

import com.bignerdranch.android.sc.clockpage.model.FlowerDataSource;
import com.bignerdranch.android.sc.clockpage.model.FlowerResponse;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.punch.bean.LabelPunch;
import com.bignerdranch.android.sc.punch.bean.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.bean.SingleMessage;
import com.bignerdranch.android.sc.punch.bean.ResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClockInModel {
    public void getFlowerStatus(String token, int day, ClockInResponseListener clockInResponseListener) {
        NetUtil.getInstance().getApi().ifDayAllPunch(token,day).enqueue(new Callback<FlowerResponse>() {
            @Override
            public void onResponse(Call<FlowerResponse> call, Response<FlowerResponse> response) {
                if(response.body().getMsg().equals("已全部完成且数量为返回的值")){
                    clockInResponseListener.ifDayAllPunch(true);
                }
            }

            @Override
            public void onFailure(Call<FlowerResponse> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }

        });
    }

    public void getClockInLabels(String token,ClockInResponseListener clockInResponseListener){
        NetUtil.getInstance().getApi().getLabels(token).enqueue(new Callback<ResponseData<List<LabelPunch>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<LabelPunch>>> call, Response<ResponseData<List<LabelPunch>>> response) {
                if(response.body() != null)
                    clockInResponseListener.clockInRequestLabelSuccess(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseData<List<LabelPunch>>> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }

    public void toClockIn(String token, LabelPunchTitle clockInLabelTitle, ClockInResponseListener clockInResponseListener){
        NetUtil.getInstance().getApi().toClockIn(token,clockInLabelTitle).enqueue(new Callback<SingleMessage>() {
            @Override
            public void onResponse(Call<SingleMessage> call, Response<SingleMessage> response) {
            }

            @Override
            public void onFailure(Call<SingleMessage> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }

    public void removeLabel(String token, LabelPunchTitle clockInLabelTitle, ClockInResponseListener clockInResponseListener){
        NetUtil.getInstance().getApi().removeLabel(token, clockInLabelTitle).enqueue(new Callback<SingleMessage>() {
            @Override
            public void onResponse(Call<SingleMessage> call, Response<SingleMessage> response) {
                clockInResponseListener.removeLabelSuccess("删除成功");
            }

            @Override
            public void onFailure(Call<SingleMessage> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }

    public void CheckLabelStatus(String token, String url, ClockInResponseListener clockInResponseListener){
        NetUtil.getInstance().getApi().isClockInToday(token,url).enqueue(new Callback<ResponseData<Boolean>>() {
            @Override
            public void onResponse(Call<ResponseData<Boolean>> call, Response<ResponseData<Boolean>> response) {
                clockInResponseListener.CheckLabelStatus(response.body().getData());
            }

            @Override
            public void onFailure(Call<ResponseData<Boolean>> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }
}
