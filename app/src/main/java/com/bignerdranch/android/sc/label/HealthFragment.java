package com.bignerdranch.android.sc.label;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;


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

    private ImageButton fankui;

    private int[] flag = {0};
    private int times1 = 0;

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

    SendValue mSendValue;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSendValue= (SendValue) getActivity();
    }

    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view=inflater .inflate(R.layout.health_pager,container,false) ;
        mchishuiguo = (ImageButton) view.findViewById(R.id.chishuiguo);
        mchishuiguo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[0] == 0){
                    mchishuiguo.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[0] = 1;
                    final TextView textView = view.findViewById(R.id.chishuiguo_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                }else{
                    mchishuiguo.setBackgroundResource(R.mipmap.chishuiguo);
                    flag[0] = 0;
                }
            }
        });

        mchizaocan = (ImageButton)view.findViewById(R.id.chizaocan);
        mchizaocan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[1] == 0){
                    mchizaocan.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[1] =1;
                    final TextView textView = view.findViewById(R.id.chizaocan_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                }else{
                    mchizaocan.setBackgroundResource(R.mipmap.chizaocan);
                    flag[1] = 0;
                }
            }
        });

        mduoheshui = (ImageButton)view.findViewById(R.id.duoheshui);
        mduoheshui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[2] == 0){
                    mduoheshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[2] =1;
                    final TextView textView = view.findViewById(R.id.duoheshui_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                }else{
                    mduoheshui.setBackgroundResource(R.mipmap.duoheshui);
                    flag[2] = 0;
                }
            }
        });

        mjujueyexiao = (ImageButton)view.findViewById(R.id.jujueyexiao);
        mjujueyexiao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[3] == 0){
                    mjujueyexiao.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[3] =1;
                    final TextView textView = view.findViewById(R.id.jujueyexiao_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                }else{
                    mjujueyexiao.setBackgroundResource(R.mipmap.jujueyexiao);
                    flag[3] = 0;
                }
            }
        });

        mjujueyinliao = (ImageButton)view.findViewById(R.id.jujueyinliao);
        mjujueyinliao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[4] == 0){
                    mjujueyinliao.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[4] =1;
                    final TextView textView = view.findViewById(R.id.jujueyinliao_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                }else{
                    mjujueyinliao.setBackgroundResource(R.mipmap.jujueyinliao);
                    flag[4] = 0;
                }
            }
        });

        mjujuejiuzuo = (ImageButton)view.findViewById(R.id.jujuejiuzuo);
        mjujuejiuzuo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[5] == 0){
                    mjujuejiuzuo.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[5] =1;
                    final TextView textView = view.findViewById(R.id.jujuejiuzuo_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                }else{
                    mjujuejiuzuo.setBackgroundResource(R.mipmap.jujuejiuzuo);
                    flag[5] = 0;
                }
            }
        });

        mzaoqi = (ImageButton)view.findViewById(R.id.zaoqi);
        mzaoqi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[6] == 0){
                    mzaoqi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[6] =1;
                    final TextView textView = view.findViewById(R.id.zaoqi_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                }else{
                    mzaoqi.setBackgroundResource(R.mipmap.zaoqi);
                    flag[6] = 0;
                }
            }
        });

        mzaoshui = (ImageButton)view.findViewById(R.id.zaoshui);
        mzaoshui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[7] == 0){
                    mzaoshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[7] =1;
                    final TextView textView = view.findViewById(R.id.zaoshui_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                }else{
                    mzaoshui.setBackgroundResource(R.mipmap.zaoshui);
                    flag[7] = 0;
                }
            }
        });

        mbuqiaoerlangtui = (ImageButton)view.findViewById(R.id.buqiaoerlangtui);
        mbuqiaoerlangtui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[8] == 0){
                    mbuqiaoerlangtui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[8] =1;
                    final TextView textView = view.findViewById(R.id.buqiaoerlangtui_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                }else{
                    mbuqiaoerlangtui.setBackgroundResource(R.mipmap.buqiaoerlangtui);
                    flag[8] = 0;
                }
            }
        });

        mzaoqikongfuheshui = (ImageButton)view.findViewById(R.id.zaoqikongfuheshui);
        mzaoqikongfuheshui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag[9] == 0){
                    mzaoqikongfuheshui.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[9] =1;
                    final TextView textView = view.findViewById(R.id.zaoqikongfuheshui_textView);
                    times1++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                    mSendValue.Send2(times1);
                }else{
                    mzaoqikongfuheshui.setBackgroundResource(R.mipmap.zaoqikongfuheshui);
                    flag[9] = 0;
                }
            }
        });

        fankui = (ImageButton)view.findViewById(R.id.fankui_imageButton);

        return view;
    }

    public interface SendValue{
        void Send1(String s);
        void Send2(int times1);
    }

}