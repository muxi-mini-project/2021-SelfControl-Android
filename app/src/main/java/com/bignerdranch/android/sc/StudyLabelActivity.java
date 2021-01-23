package com.bignerdranch.android.sc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

public class StudyLabelActivity extends AppCompatActivity {
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
    private ImageButton myundong;
    private ImageButton mjiankang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_label);

        mzixi=(ImageButton)findViewById(R.id.zixi_B);
        mzixi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // IB_PullDown.setBackgroundResource(R.drawable.pulldown_button_image);
                if (f1 == 0) {
                    // TODO Auto-generated method stub
                    mzixi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    //ll_AirItem.setVisibility(View.VISIBLE);
                    f1 = 1;
                } else {
                    mzixi.setBackgroundResource(R.mipmap.zixi);
                    //ll_AirItem.setVisibility(View.GONE);
                    f1 = 0;
                }
            }


        });

        myueduxinwen=(ImageButton)findViewById(R.id.yueduxinwen_B);
        myueduxinwen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (f2 == 0) {
                    myueduxinwen.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f2 = 1;
                } else {
                    myueduxinwen.setBackgroundResource(R.mipmap.yueduxinwen);
                    f2 = 0;
                }
            }
        });

        mlianxiyueqi=(ImageButton)findViewById(R.id.lianxiyueqi_B);
        mlianxiyueqi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (f3 == 0) {
                    mlianxiyueqi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f3 = 1;
                } else {
                    mlianxiyueqi.setBackgroundResource(R.mipmap.lianxiyueqi);
                    f3 = 0;
                }
            }
        });

        mxuexixinyuyan=(ImageButton)findViewById(R.id.xuexixinyuyan_B);
        mxuexixinyuyan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (f4 == 0) {
                    mxuexixinyuyan.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f4 = 1;
                } else {
                    mxuexixinyuyan.setBackgroundResource(R.mipmap.xuexixinyvyan);
                    f4 = 0;
                }
            }
        });

        mbeidanci=(ImageButton)findViewById(R.id.beidanci_B);
        mbeidanci.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (f5 == 0) {
                    mbeidanci.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f5 = 1;
                } else {
                    mbeidanci.setBackgroundResource(R.mipmap.beidanci);
                    f5 = 0;
                }
            }
        });

        mkanjilupian=(ImageButton)findViewById(R.id.kanjilupian_B);
        mkanjilupian.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (f6 == 0) {
                    mkanjilupian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f6 = 1;
                } else {
                    mkanjilupian.setBackgroundResource(R.mipmap.kanjilupian);
                    f6 = 0;
                }
            }
        });

        mzuojinrijihua=(ImageButton)findViewById(R.id.zuojinrijihua_B);
        mzuojinrijihua.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (f7 == 0) {
                    mzuojinrijihua.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f7 = 1;
                } else {
                    mzuojinrijihua.setBackgroundResource(R.mipmap.zuojinrijihua);
                    f7 = 0;
                }
            }
        });

        mtinglixunlian=(ImageButton)findViewById(R.id.tinglixunjian_B);
        mtinglixunlian.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (f8 == 0) {
                    mtinglixunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f8 = 1;
                } else {
                    mtinglixunlian.setBackgroundResource(R.mipmap.tinglilianxi);
                    f8 = 0;
                }
            }
        });

        mlianzi=(ImageButton)findViewById(R.id.lianzi_B);
        mlianzi.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (f9 == 0) {
                    mlianzi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f9 = 1;
                } else {
                    mlianzi.setBackgroundResource(R.mipmap.lianzi);
                    f9 = 0;
                }
            }
        });

        myingyuyueduxunlian=(ImageButton)findViewById(R.id.yingyuyueduxunlian_B);
        myingyuyueduxunlian.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                if (f10 == 0) {
                    myingyuyueduxunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f10 = 1;
                } else {
                    myingyuyueduxunlian.setBackgroundResource(R.mipmap.yingyuyueduxunlian);
                    f10 = 0;
                }
            }
        });

        myundong= (ImageButton)findViewById(R.id.yundong_normal);
        myundong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudyLabelActivity.this, SportLabelActivity.class);
                startActivity(intent);
            }
        });

        mjiankang=(ImageButton)findViewById(R.id.jiankang_normal);
        mjiankang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StudyLabelActivity.this, HealthyLabelActivity.class);
                startActivity(intent);
            }
        });
        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}