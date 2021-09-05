package com.bignerdranch.android.sc.label;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.ClockPage.View.ClockActivity;
import com.bignerdranch.android.sc.user.Bean.Message;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.Utils;

import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class StudyFragment extends Fragment {
    int f1=0;int f2=0;int f3=0;int f4=0;int f5=0;int f6=0;int f7=0;int f8=0;int f9=0;int f10=0;
    private ImageButton mzixi;
    private ImageButton myueduxinwen;
    private ImageButton mlianxiyueqi;
    private ImageButton mxuexixinyuyan;
    private ImageButton mbeidanci;
    private ImageButton mkanjilupian;
    private ImageButton mzuojinrijihua;
    private ImageButton mtinglixunlian;
    private ImageButton mlianzi;
    private ImageButton myingyuyueduxunlian;
    private Button mButton;
    private List<LabelPunch> mLabelPunchList;


    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view=inflater .inflate(R.layout.study_pager,container,false) ;

            mzixi=(ImageButton)view.findViewById(R.id.zixi_B);
            mzixi.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // IB_PullDown.setBackgroundResource(R.drawable.pulldown_button_image);
                    if (f1 == 0) {
                        mzixi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        //ll_AirItem.setVisibility(View.VISIBLE);
                        f1 = 1;
                        createRequest("自习");
                    } else {
                        mzixi.setBackgroundResource(R.mipmap.zixi);
                        //ll_AirItem.setVisibility(View.GONE);
                        f1 = 0;
                        deleteRequest("自习");
                    }
                }


            });

            myueduxinwen=(ImageButton)view.findViewById(R.id.yueduxinwen_B);
            myueduxinwen.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (f2 == 0) {
                        myueduxinwen.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        f2 = 1;
                        createRequest("阅读新闻");

                    } else {
                        myueduxinwen.setBackgroundResource(R.mipmap.yueduxinwen);
                        f2 = 0;
                        deleteRequest("阅读新闻");

                    }
                }
            });

            mlianxiyueqi=(ImageButton)view.findViewById(R.id.lianxiyueqi_B);
            mlianxiyueqi.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (f3 == 0) {
                        mlianxiyueqi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        f3 = 1;
                        createRequest("练习乐器");

                    } else {
                        mlianxiyueqi.setBackgroundResource(R.mipmap.lianxiyueqi);
                        f3 = 0;
                        deleteRequest("练习乐器");

                    }
                }
            });

            mxuexixinyuyan=(ImageButton)view.findViewById(R.id.xuexixinyuyan_B);
            mxuexixinyuyan.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (f4 == 0) {
                        mxuexixinyuyan.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        f4 = 1;
                        createRequest("学习新语言");

                    } else {
                        mxuexixinyuyan.setBackgroundResource(R.mipmap.xuexixinyvyan);
                        f4 = 0;
                        deleteRequest("学习新语言");

                    }
                }
            });

            mbeidanci=(ImageButton)view.findViewById(R.id.beidanci_B);
            mbeidanci.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (f5 == 0) {
                        mbeidanci.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        f5 = 1;
                        createRequest("背单词");

                    } else {
                        mbeidanci.setBackgroundResource(R.mipmap.beidanci);
                        f5 = 0;
                        deleteRequest("背单词");

                    }
                }
            });

            mkanjilupian=(ImageButton)view.findViewById(R.id.kanjilupian_B);
            mkanjilupian.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (f6 == 0) {
                        mkanjilupian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        f6 = 1;
                        createRequest("看纪录片");

                    } else {
                        mkanjilupian.setBackgroundResource(R.mipmap.kanjilupian);
                        f6 = 0;
                        deleteRequest("看纪录片");

                    }
                }
            });

            mzuojinrijihua=(ImageButton)view.findViewById(R.id.zuojinrijihua_B);
            mzuojinrijihua.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (f7 == 0) {
                        mzuojinrijihua.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        f7 = 1;
                        createRequest("做今日计划");

                    } else {
                        mzuojinrijihua.setBackgroundResource(R.mipmap.zuojinrijihua);
                        f7 = 0;
                        deleteRequest("做今日计划");

                    }
                }
            });

            mtinglixunlian=(ImageButton)view.findViewById(R.id.tinglixunjian_B);
            mtinglixunlian.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (f8 == 0) {
                        mtinglixunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        f8 = 1;
                        createRequest("听力训练");

                    } else {
                        mtinglixunlian.setBackgroundResource(R.mipmap.tinglilianxi);
                        f8 = 0;
                        deleteRequest("听力训练");

                    }
                }
            });

            mlianzi=(ImageButton)view.findViewById(R.id.lianzi_B);
            mlianzi.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (f9 == 0) {
                        mlianzi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        f9 = 1;
                        createRequest("练字");

                    } else {
                        mlianzi.setBackgroundResource(R.mipmap.lianzi);
                        f9 = 0;
                        deleteRequest("练字");

                    }
                }
            });

            myingyuyueduxunlian=(ImageButton)view.findViewById(R.id.yingyuyueduxunlian_B);
            myingyuyueduxunlian.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (f10 == 0) {
                        myingyuyueduxunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        f10 = 1;
                        createRequest("英语阅读训练");

                    } else {
                        myingyuyueduxunlian.setBackgroundResource(R.mipmap.yingyuyueduxunlian);
                        f10 = 0;
                        deleteRequest("英语阅读训练");

                    }
                }
            });

            mButton= view.findViewById(R.id.complete);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.isFastClick()){
                        Intent intent=new Intent(getActivity() , ClockActivity.class);
                        startActivity(intent);
                    }

                }
            });
        getMyPunch();
        return view;
    }
    public void createRequest(String title) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<Message> call = client.create(token, new Punch(title));

        call.enqueue(new Callback<Message>() {

            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
    public void deleteRequest(String title) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<Message> call = client.delete(token, new Punch(title));

        call.enqueue(new Callback<Message>() {

            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
    private void getMyPunch() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<List<LabelPunch>> call = client.getPunch(token);

        call.enqueue(new Callback<List<LabelPunch>>() {

            @Override
            public void onResponse(Call<List<LabelPunch>> call, Response<List<LabelPunch>> response) {
                mLabelPunchList = response.body();
                if(response.body() != null) {
                    for (int i = 0; i < mLabelPunchList.size() ; i++ ){
                        if(mLabelPunchList.get(i).getTitle().equals("自习")) {mzixi.setBackgroundResource(R.mipmap.yixuanbiaoqian); f1 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("阅读新闻")) {myueduxinwen.setBackgroundResource(R.mipmap.yixuanbiaoqian); f2 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("练习乐器")) {mlianxiyueqi.setBackgroundResource(R.mipmap.yixuanbiaoqian);f3 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("学习新语言")) {mxuexixinyuyan.setBackgroundResource(R.mipmap.yixuanbiaoqian); f4 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("背单词")){ mbeidanci.setBackgroundResource(R.mipmap.yixuanbiaoqian);f5 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("看纪录片")) {mkanjilupian.setBackgroundResource(R.mipmap.yixuanbiaoqian);f6 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("做今日计划")) {mzuojinrijihua.setBackgroundResource(R.mipmap.yixuanbiaoqian);f7 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("听力训练")) {mtinglixunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);f8 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("练字")) {mlianzi.setBackgroundResource(R.mipmap.yixuanbiaoqian);f9 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("英语阅读训练")) {myingyuyueduxunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);f10 = 1;}

                    }
                }
            }

            @Override
            public void onFailure(Call<List<LabelPunch>> call, Throwable t) {
            }
        });
    }

}

