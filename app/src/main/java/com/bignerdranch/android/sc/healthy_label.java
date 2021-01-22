package com.bignerdranch.android.sc;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.android.sc.R;

import static com.bignerdranch.android.sc.study_label.makeStatusBarTransparent;

public class healthy_label extends AppCompatActivity {

    private ImageButton healthy;
    private ImageButton sport;
    private ImageButton study;

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
    private ImageButton chosen;

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

    private TextView add1;
    private TextView add2;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthy_label);

        mchishuiguo = (ImageButton) findViewById(R.id.chishuiguo);
        mchishuiguo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag1 == 0){
                    mchishuiguo.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag1 = 1;
                }else{
                    mchishuiguo.setBackgroundResource(R.drawable.chishuiguo);
                    flag1 = 0;
                }
            }
        });

        mchizaocan = (ImageButton)findViewById(R.id.chizaocan);
        mchizaocan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag2 == 0){
                    mchizaocan.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag2 =1;
                }else{
                    mchizaocan.setBackgroundResource(R.drawable.chizaocan);
                    flag2 = 0;
                }
            }
        });

        mduoheshui = (ImageButton)findViewById(R.id.duoheshui);
        mduoheshui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag3 == 0){
                    mduoheshui.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag3 =1;
                }else{
                    mduoheshui.setBackgroundResource(R.drawable.duoheshui);
                    flag3 = 0;
                }
            }
        });

        mjujueyexiao = (ImageButton)findViewById(R.id.jujueyexiao);
        mjujueyexiao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag4 == 0){
                    mjujueyexiao.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag4 =1;
                }else{
                    mjujueyexiao.setBackgroundResource(R.drawable.jujueyexiao);
                    flag4 = 0;
                }
            }
        });

        mjujueyinliao = (ImageButton)findViewById(R.id.jujueyinliao);
        mjujueyinliao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag5 == 0){
                    mjujueyinliao.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag5 =1;
                }else{
                    mjujueyinliao.setBackgroundResource(R.drawable.jujueyinliao);
                    flag5 = 0;
                }
            }
        });

        mjujuejiuzuo = (ImageButton)findViewById(R.id.jujuejiuzuo);
        mjujuejiuzuo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag6 == 0){
                    mjujuejiuzuo.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag6 =1;
                }else{
                    mjujuejiuzuo.setBackgroundResource(R.drawable.jujuejiuzuo);
                    flag6 = 0;
                }
            }
        });

        mzaoqi = (ImageButton)findViewById(R.id.zaoqi);
        mzaoqi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag7 == 0){
                    mzaoqi.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag7 =1;
                }else{
                    mzaoqi.setBackgroundResource(R.drawable.zaoqi);
                    flag7 = 0;
                }
            }
        });

        mzaoshui = (ImageButton)findViewById(R.id.zaoshui);
        mzaoshui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag8 == 0){
                    mzaoshui.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag8 =1;
                }else{
                    mzaoshui.setBackgroundResource(R.drawable.zaoshui);
                    flag8 = 0;
                }
            }
        });

        mbuqiaoerlangtui = (ImageButton)findViewById(R.id.buqiaoerlangtui);
        mbuqiaoerlangtui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag9 == 0){
                    mbuqiaoerlangtui.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag9 =1;
                }else{
                    mbuqiaoerlangtui.setBackgroundResource(R.drawable.buqiaoerlangtui);
                    flag9 = 0;
                }
            }
        });

        mzaoqikongfuheshui = (ImageButton)findViewById(R.id.zapqikongfuheshui);
        mzaoqikongfuheshui.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(flag10 == 0){
                    mzaoqikongfuheshui.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag10 =1;
                }else{
                    mzaoqikongfuheshui.setBackgroundResource(R.drawable.zaoqikongfuheshui);
                    flag10 = 0;
                }
            }
        });

        add = (ImageButton)findViewById(R.id.add);

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    public void textViewInit(){

        chishuiguo = (TextView)findViewById(R.id.chishuiguo_textView);
        chizaocan = (TextView)findViewById(R.id.chizaocan_textView);
        duoheshui = (TextView)findViewById(R.id.duoheshui_textView);
        jujueyexiao = (TextView)findViewById(R.id.jujueyexiao_textView);
        jujueyinliao = (TextView)findViewById(R.id.jujueyinliao_textView);
        jujuejiuzuo = (TextView)findViewById(R.id.jujuejiuzuo_textView);
        zaoqi = (TextView)findViewById(R.id.zaoqi_textView);
        zaoshui = (TextView)findViewById(R.id.zaoshui_textView);
        buqiaoerlangtui = (TextView)findViewById(R.id.buqiaoerlangtui_textView);
        zaoqikongfuheshui = (TextView)findViewById(R.id.zapqikongfuheshui_textView);

    }
}