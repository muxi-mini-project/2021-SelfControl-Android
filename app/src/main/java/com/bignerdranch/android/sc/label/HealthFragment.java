package com.bignerdranch.android.sc.label;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.user.bean.Message;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;


public class HealthFragment extends Fragment {
    private ImageButton mchishuiguo;
    private ImageButton mchizaocan;
    private ImageButton mduoheshui;
    private ImageButton mjujueyexiao;
    private ImageButton mjujueyinliao;
    private ImageButton mjujuejiuzuo;
    private ImageButton mzaoqi;
    private ImageButton mzaoshui;
    private ImageButton mbuqiaoerlangtui;
    private ImageButton mzaoqikongfuheshui;

    private ImageButton add;
    private int flag1 = 0;
    private int flag2 = 0;
    private int flag3 = 0;
    private int flag4 = 0;
    private int flag5 = 0;
    private int flag6 = 0;
    private int flag7 = 0;
    private int flag8 = 0;
    private int flag9 = 0;
    private int flag10 = 0;

    private TextView chishuiguo;
    private TextView chizaocan;
    private TextView duoheshui;
    private TextView jujueyexiao;
    private TextView jujueyinliao;
    private TextView jujuejiuzuo;
    private TextView zaoqi;
    private TextView zaoshui;
    private TextView buqiaoerlangtui;
    private TextView zaoqikongfuheshui;
    private List<LabelPunch> mLabelPunchList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_pager, container, false);
        mchishuiguo = view.findViewById(R.id.chishuiguo);
        mchishuiguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1 == 0) {
                    mchishuiguo.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag1 = 1;
                    createRequest("吃水果");
                } else {
                    mchishuiguo.setBackgroundResource(R.mipmap.chishuiguo);
                    flag1 = 0;
                    deleteRequest("吃水果");

                }
            }
        });

        mchizaocan = (ImageButton) view.findViewById(R.id.chizaocan);
        mchizaocan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2 == 0) {
                    mchizaocan.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag2 = 1;
                    createRequest("吃早餐");

                } else {
                    mchizaocan.setBackgroundResource(R.mipmap.chizaocan);
                    flag2 = 0;
                    deleteRequest("吃早餐");

                }
            }
        });

        mduoheshui = (ImageButton) view.findViewById(R.id.duoheshui);
        mduoheshui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag3 == 0) {
                    mduoheshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag3 = 1;
                    createRequest("多喝水");

                } else {
                    mduoheshui.setBackgroundResource(R.mipmap.duoheshui);
                    flag3 = 0;
                    deleteRequest("多喝水");

                }
            }
        });

        mjujueyexiao = (ImageButton) view.findViewById(R.id.jujueyexiao);
        mjujueyexiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag4 == 0) {
                    mjujueyexiao.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag4 = 1;
                    createRequest("拒绝夜宵");

                } else {
                    mjujueyexiao.setBackgroundResource(R.mipmap.jujueyexiao);
                    flag4 = 0;
                    deleteRequest("拒绝夜宵");

                }
            }
        });

        mjujueyinliao = (ImageButton) view.findViewById(R.id.jujueyinliao);
        mjujueyinliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag5 == 0) {
                    mjujueyinliao.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag5 = 1;
                    createRequest("拒绝饮料");

                } else {
                    mjujueyinliao.setBackgroundResource(R.mipmap.jujueyinliao);
                    flag5 = 0;
                    deleteRequest("拒绝饮料");

                }
            }
        });

        mjujuejiuzuo = (ImageButton) view.findViewById(R.id.jujuejiuzuo);
        mjujuejiuzuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag6 == 0) {
                    mjujuejiuzuo.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag6 = 1;
                    createRequest("拒绝久坐");

                } else {
                    mjujuejiuzuo.setBackgroundResource(R.mipmap.jujuejiuzuo);
                    flag6 = 0;
                    deleteRequest("拒绝久坐");

                }
            }
        });

        mzaoqi = (ImageButton) view.findViewById(R.id.zaoqi);
        mzaoqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag7 == 0) {
                    mzaoqi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag7 = 1;
                    createRequest("早起");

                } else {
                    mzaoqi.setBackgroundResource(R.mipmap.zaoqi);
                    flag7 = 0;
                    deleteRequest("早起");

                }
            }
        });

        mzaoshui = (ImageButton) view.findViewById(R.id.zaoshui);
        mzaoshui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag8 == 0) {
                    mzaoshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag8 = 1;
                    createRequest("早睡");

                } else {
                    mzaoshui.setBackgroundResource(R.mipmap.zaoshui);
                    flag8 = 0;
                    deleteRequest("早睡");

                }
            }
        });

        mbuqiaoerlangtui = (ImageButton) view.findViewById(R.id.buqiaoerlangtui);
        mbuqiaoerlangtui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag9 == 0) {
                    mbuqiaoerlangtui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag9 = 1;
                    createRequest("不翘二郎腿");

                } else {
                    mbuqiaoerlangtui.setBackgroundResource(R.mipmap.buqiaoerlangtui);
                    flag9 = 0;
                    deleteRequest("不翘二郎腿");

                }
            }
        });

        mzaoqikongfuheshui = (ImageButton) view.findViewById(R.id.zapqikongfuheshui);
        mzaoqikongfuheshui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag10 == 0) {
                    mzaoqikongfuheshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag10 = 1;
                    createRequest("早起空腹喝水");

                } else {
                    mzaoqikongfuheshui.setBackgroundResource(R.mipmap.zaoqikongfuheshui);
                    flag10 = 0;
                    deleteRequest("早起空腹喝水");

                }
            }
        });

        chishuiguo = (TextView) view.findViewById(R.id.chishuiguo_textView);
        chizaocan = (TextView) view.findViewById(R.id.chizaocan_textView);
        duoheshui = (TextView) view.findViewById(R.id.duoheshui_textView);
        jujueyexiao = (TextView) view.findViewById(R.id.jujueyexiao_textView);
        jujueyinliao = (TextView) view.findViewById(R.id.jujueyinliao_textView);
        jujuejiuzuo = (TextView) view.findViewById(R.id.jujuejiuzuo_textView);
        zaoqi = (TextView) view.findViewById(R.id.zaoqi_textView);
        zaoshui = (TextView) view.findViewById(R.id.zaoshui_textView);
        buqiaoerlangtui = (TextView) view.findViewById(R.id.buqiaoerlangtui_textView);
        zaoqikongfuheshui = (TextView) view.findViewById(R.id.zapqikongfuheshui_textView);

        add = (ImageButton) view.findViewById(R.id.add);
        getMyPunch();

        return view;
    }

    public void createRequest(String title) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<Message> call = client.create(token, new Punch(title));

        call.enqueue(new Callback<Message>() {

            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                String message;
                message = response.body().getMessage();
                if (response.code() == 200) {
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    public void deleteRequest(String title) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<Message> call = client.delete(token, new Punch(title));

        call.enqueue(new Callback<Message>() {

            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.code() == 200) {
                }
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
                        if(mLabelPunchList.get(i).getTitle().equals("吃水果")) {mchishuiguo.setBackgroundResource(R.mipmap.yixuanbiaoqian); flag1 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("吃早餐")) {mchizaocan.setBackgroundResource(R.mipmap.yixuanbiaoqian); flag2 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("多喝水")) {mduoheshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag3 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("拒绝夜宵")) {mjujueyexiao.setBackgroundResource(R.mipmap.yixuanbiaoqian); flag4 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("拒绝饮料")){ mjujueyexiao.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag5 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("拒绝久坐")) {mjujuejiuzuo.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag6 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("早起")) {mzaoqi.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag7 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("早睡")) {mzaoshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag8 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("不翘二郎腿")) {mbuqiaoerlangtui.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag9 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("早起空腹喝水")) { mzaoqikongfuheshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag10 = 1 ;}


                    }
                }
            }

            @Override
            public void onFailure(Call<List<LabelPunch>> call, Throwable t) {
            }
        });
    }


}