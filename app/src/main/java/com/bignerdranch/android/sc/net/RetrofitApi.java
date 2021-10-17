package com.bignerdranch.android.sc.net;

import com.bignerdranch.android.sc.clockpage.model.FlowerResponse;
import com.bignerdranch.android.sc.login.LoginResponse;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.punch.bean.LabelPunch;
import com.bignerdranch.android.sc.punch.bean.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.bean.ResponseData;
import com.bignerdranch.android.sc.punch.bean.SingleMessage;
import com.bignerdranch.android.sc.rank.newrank.bean.ChangeRank;
import com.bignerdranch.android.sc.rank.newrank.bean.RankItem;
import com.bignerdranch.android.sc.seeuser.bean.UserPunch;
import com.bignerdranch.android.sc.settings.bean.BackgroundItem;
import com.bignerdranch.android.sc.settings.bean.MyBackdrops;
import com.bignerdranch.android.sc.settings.bean.Privacy;
import com.bignerdranch.android.sc.user.bean.GoldHistory;
import com.bignerdranch.android.sc.user.bean.Rank;
import com.bignerdranch.android.sc.user.bean.Report;
import com.bignerdranch.android.sc.user.bean.Week;
import com.bignerdranch.android.sc.rank.newrank.bean.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import io.reactivex.Observable;
import retrofit2.http.Url;

public interface RetrofitApi {

    /**
     * BackDrop
     */
    @PUT("backdrop") //兑换背景
    Observable<Response<BackgroundItem.Buy>> buyBack(@Header("token") String token, @Body BackgroundItem.Buy mBuy);

    @GET("backdrops") //我的背景
    Observable<MyBackdrops> getMyBack(@Header("token") String token);


    /**
     * List
     */
    @PUT("list/month")  //兑换月排名
    Observable<ChangeRank> putMonth(@Header("token")String token, @Body RankItem.RankDataBean rankItem);

    @PUT("list/week")//兑换周排名
    Observable<ChangeRank> putWeek(@Header("token")String token, @Body RankItem.RankDataBean rankItem);

    @GET("list/history")//获得兑换排名历史
    Observable<Rank> getMyRank(@Header("token") String token);

    @GET("lists/month")//获得月排名数据
    Observable<RankItem> getMonth();

    @GET("lists/week")//获得周排名数据
    Observable<RankItem> getWeek();

    /**
     * punch
     */
    @HTTP(method = "DELETE", path = "punch", hasBody = true)//删除标签
    Call<SingleMessage> removeLabel(@Header("token") String token, @Body LabelPunchTitle clockInLabelTitle);

    @GET("punch")//我的打卡
    Call<ResponseData<List<LabelPunch>>> getLabels(@Header("token") String token);

    @POST("punch")//完成打卡
    Call<SingleMessage> toClockIn(@Header("token") String token, @Body LabelPunchTitle clockInLabelTitle);

    @GET("punch/all/{day}")//判断某天是否全部打卡
    Call<FlowerResponse> ifDayAllPunch(@Header("token") String token, @Path("day")int day);

    @POST("punch/create")//增加标签
    Call<SingleMessage> create(@Header("token") String token, @Body LabelPunchTitle clockInLabelTitle);

    @GET("punch/all/{day}")//判断某天是否全部打卡
    Call<ResponseData<Integer>> getDatAllPunch(@Header("token") String token, @Path("day")int day);

    @GET("punch/month")//获取某用户月报
    Observable<Report> getMonthReport(@Header("token") String token);

    @GET("")//判断某天某卡是否已被打卡
    Observable<ResponseData<Boolean>> isClockInToday(@Header("token") String token, @Url String url);

    @GET("")
    Call<ResponseData<List<LabelPunch>>> getClockDayList(@Header("token") String token, @Url String url);

    @GET("punch/punch/{id}")//获取某用户标签
    Observable<UserPunch> requestList(@Path("id")String id);

    @GET("punch/week/{month}")//获取用户月报的周数据
    Observable<Week> getWeekNumber(@Header("token") String token, @Path("month") int month);

    /**
     * user
     */
    @GET("user")
    Observable<User> userInfo(@Header("token") String token);

    @POST("user")//登录
    Call<LoginResponse> getCall (@Body User.DataDTO user);

    @PUT("user")
    Observable<User> changName(@Header("token") String token, @Body User.DataDTO mUser);

    @PUT("user")
    Observable<Message> putPrivacy(@Body Privacy mP, @Header("token") String token);

    @PUT("user")
    Observable<Message> putMyBack(@Body User.DataDTO mUser, @Header("token") String token);

    @GET("user/goldhistory")//金币历史
    Observable<GoldHistory> getGoldHistory(@Header("token") String token);

    @GET("user/privacy/{id}")//隐私
    Call<Message> askPrivacy(@Path("id") String id);

}
