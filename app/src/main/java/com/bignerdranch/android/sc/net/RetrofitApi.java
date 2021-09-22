package com.bignerdranch.android.sc.net;

import com.bignerdranch.android.sc.clockpage.model.FlowerResponse;
import com.bignerdranch.android.sc.label.Punch;
import com.bignerdranch.android.sc.login.LoginResponse;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.punch.LabelPunch;
import com.bignerdranch.android.sc.punch.ResponseData;
import com.bignerdranch.android.sc.rank.newrank.bean.ChangeRank;
import com.bignerdranch.android.sc.rank.newrank.bean.RankItem;
import com.bignerdranch.android.sc.user.bean.GoldHistory;
import com.bignerdranch.android.sc.user.bean.Message;
import com.bignerdranch.android.sc.user.bean.Rank;
import com.bignerdranch.android.sc.user.bean.Report;
import com.bignerdranch.android.sc.user.bean.Week;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface RetrofitApi {

    //login
    @POST("user/")
    Call<LoginResponse> getCall (@Body User.DataDTO user);

    //label
    @POST("punch/create")
    Call<Message> create(@Header("token") String token, @Body Punch mPunch);

    @HTTP(method = "DELETE", path = "punch/", hasBody = true)
    Call<Message> delete(@Header("token") String token, @Body Punch mPunch);

    @GET("punch/")
    Call<ResponseData<List<LabelPunch>>> getPunch(@Header("token")String token);

    @POST("punch/")
    Call<Message> punch(@Header("token") String token,@Body Punch mPUnch);

    //punch
    @GET("punch/all/{day}")
    Call<FlowerResponse> ifDayAllPunch(@Header("token") String token, @Path("day")int day);

    @GET("user/")
    Call<User> getCurrentBackdrop(@Header("token")String token);

    @GET("punch/week/{month}")
    Observable<Week> getWeekNumber(@Header("token") String token, @Path("month") int month);

    @GET("user/goldhistory")
    Observable<GoldHistory> getGoldHistory(@Header("token") String token);

    @GET("user/")
    Observable<User> mUser(@Header("token") String token);

    @GET("punch/month")
    Observable<Report> getMonthReport(@Header("token") String token);

    @PUT("user/")
    Observable<User> changName(@Header("token") String token, @Body User.DataDTO mUser);

    @GET("list/history")
    Observable<Rank> getMyRank(@Header("token") String token);


    @PUT("list/month")
    io.reactivex.Observable<ChangeRank> putMonth(@Header("token")String token, @Body RankItem.RankDataBean rankItem);

    @GET("lists/month/")
    io.reactivex.Observable<RankItem> getMonth();


    @PUT("list/week")
    io.reactivex.Observable<ChangeRank> putWeek(@Header("token")String token, @Body RankItem.RankDataBean rankItem);

    @GET("lists/week/")
    io.reactivex.Observable<RankItem> getWeek();
}
