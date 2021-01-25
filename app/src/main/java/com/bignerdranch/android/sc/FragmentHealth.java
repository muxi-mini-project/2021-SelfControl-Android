package com.bignerdranch.android.sc;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class FragmentHealth extends Fragment {
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

    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view=inflater .inflate(R.layout.jiankang_test,container,false) ;
        mchishuiguo = (ImageButton) view.findViewById(R.id.chishuiguo);
        mchishuiguo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag1 == 0){
                    mchishuiguo.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag1 = 1;
                }else{
                    mchishuiguo.setBackgroundResource(R.mipmap.chishuiguo);
                    flag1 = 0;
                }
            }
        });

        mchizaocan = (ImageButton)view.findViewById(R.id.chizaocan);
        mchizaocan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag2 == 0){
                    mchizaocan.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag2 =1;
                }else{
                    mchizaocan.setBackgroundResource(R.mipmap.chizaocan);
                    flag2 = 0;
                }
            }
        });

        mduoheshui = (ImageButton)view.findViewById(R.id.duoheshui);
        mduoheshui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag3 == 0){
                    mduoheshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag3 =1;
                }else{
                    mduoheshui.setBackgroundResource(R.mipmap.duoheshui);
                    flag3 = 0;
                }
            }
        });

        mjujueyexiao = (ImageButton)view.findViewById(R.id.jujueyexiao);
        mjujueyexiao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag4 == 0){
                    mjujueyexiao.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag4 =1;
                }else{
                    mjujueyexiao.setBackgroundResource(R.mipmap.jujueyexiao);
                    flag4 = 0;
                }
            }
        });

        mjujueyinliao = (ImageButton)view.findViewById(R.id.jujueyinliao);
        mjujueyinliao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag5 == 0){
                    mjujueyinliao.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag5 =1;
                }else{
                    mjujueyinliao.setBackgroundResource(R.mipmap.jujueyinliao);
                    flag5 = 0;
                }
            }
        });

        mjujuejiuzuo = (ImageButton)view.findViewById(R.id.jujuejiuzuo);
        mjujuejiuzuo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag6 == 0){
                    mjujuejiuzuo.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag6 =1;
                }else{
                    mjujuejiuzuo.setBackgroundResource(R.mipmap.jujuejiuzuo);
                    flag6 = 0;
                }
            }
        });

        mzaoqi = (ImageButton)view.findViewById(R.id.zaoqi);
        mzaoqi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag7 == 0){
                    mzaoqi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag7 =1;
                }else{
                    mzaoqi.setBackgroundResource(R.mipmap.zaoqi);
                    flag7 = 0;
                }
            }
        });

        mzaoshui = (ImageButton)view.findViewById(R.id.zaoshui);
        mzaoshui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag8 == 0){
                    mzaoshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag8 =1;
                }else{
                    mzaoshui.setBackgroundResource(R.mipmap.zaoshui);
                    flag8 = 0;
                }
            }
        });

        mbuqiaoerlangtui = (ImageButton)view.findViewById(R.id.buqiaoerlangtui);
        mbuqiaoerlangtui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag9 == 0){
                    mbuqiaoerlangtui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag9 =1;
                }else{
                    mbuqiaoerlangtui.setBackgroundResource(R.mipmap.buqiaoerlangtui);
                    flag9 = 0;
                }
            }
        });

        mzaoqikongfuheshui = (ImageButton)view.findViewById(R.id.zapqikongfuheshui);
        mzaoqikongfuheshui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag10 == 0){
                    mzaoqikongfuheshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag10 =1;
                }else{
                    mzaoqikongfuheshui.setBackgroundResource(R.mipmap.zaoqikongfuheshui);
                    flag10 = 0;
                }
            }
        });
        chishuiguo = (TextView)view.findViewById(R.id.chishuiguo_textView);
        chizaocan = (TextView)view.findViewById(R.id.chizaocan_textView);
        duoheshui = (TextView)view.findViewById(R.id.duoheshui_textView);
        jujueyexiao = (TextView)view.findViewById(R.id.jujueyexiao_textView);
        jujueyinliao = (TextView)view.findViewById(R.id.jujueyinliao_textView);
        jujuejiuzuo = (TextView)view.findViewById(R.id.jujuejiuzuo_textView);
        zaoqi = (TextView)view.findViewById(R.id.zaoqi_textView);
        zaoshui = (TextView)view.findViewById(R.id.zaoshui_textView);
        buqiaoerlangtui = (TextView)view.findViewById(R.id.buqiaoerlangtui_textView);
        zaoqikongfuheshui = (TextView)view.findViewById(R.id.zapqikongfuheshui_textView);

        add = (ImageButton)view.findViewById(R.id.add);

        return view;
    }

}