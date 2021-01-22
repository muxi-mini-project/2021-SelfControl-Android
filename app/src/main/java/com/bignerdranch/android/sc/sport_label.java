package com.bignerdranch.android.sc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.bignerdranch.android.sc.study_label.makeStatusBarTransparent;

public class sport_label extends AppCompatActivity {

    private ImageButton health;
    private ImageButton sport;
    private ImageButton study;

    private ImageButton paobu;
    private ImageButton fuwocheng;
    private ImageButton tiaosheng;
    private ImageButton yangwoqizuo;
    private ImageButton sanbu;
    private ImageButton lashen;
    private ImageButton dalanqiu;
    private ImageButton jianshen;
    private ImageButton qiche;

    private ImageButton add;
    private ImageButton chosen;

    private TextView paobu1;
    private TextView fuwocheng1;
    private TextView tiaosheng1;
    private TextView yangwoqizuo1;
    private TextView sanbu1;
    private TextView lashen1;
    private TextView dalanqiu1;
    private TextView jianshen1;
    private TextView qiche1;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sport_label);

        imageButtonInit();
        textViewInit();
    }

    public void imageButtonInit(){
        health = (ImageButton)findViewById(R.id.health_button);
        sport = (ImageButton)findViewById(R.id.sport_button);
        study = (ImageButton)findViewById(R.id.study_button);

        paobu = (ImageButton)findViewById(R.id.paobu_imagebutton);
        paobu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1 == 0) {
                    paobu.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag1 = 1;
                } else {
                    paobu.setBackgroundResource(R.drawable.paobu);
                    flag1 = 0;
                }
            }
        });


        fuwocheng = (ImageButton)findViewById(R.id.fuwocheng_imageButton);
        fuwocheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2 == 0) {
                    fuwocheng.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag2 = 1;
                } else {
                    fuwocheng.setBackgroundResource(R.drawable.fuwocheng);
                    flag2 = 0;
                }
            }
        });

        tiaosheng = (ImageButton)findViewById(R.id.tiaosheng_imageButton);
        tiaosheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag3 == 0) {
                    tiaosheng.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag3 = 1;
                } else {
                    tiaosheng.setBackgroundResource(R.drawable.tiaosheng);
                    flag3 = 0;
                }
            }
        });

        yangwoqizuo = (ImageButton)findViewById(R.id.yangwoqizuo_imageButton);
        yangwoqizuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag4 == 0) {
                    yangwoqizuo.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag4 = 1;
                } else {
                    yangwoqizuo.setBackgroundResource(R.drawable.yangwuoqizuo);
                    flag4 = 0;
                }
            }
        });

        sanbu = (ImageButton)findViewById(R.id.sanbu_imageButton);
        sanbu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag5 == 0) {
                    sanbu.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag5 = 1;
                } else {
                    sanbu.setBackgroundResource(R.drawable.sanbu);
                    flag5 = 0;
                }
            }
        });

        lashen = (ImageButton)findViewById(R.id.lashen_imageButton);
        lashen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag6 == 0) {
                    lashen.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag6 = 1;
                } else {
                    lashen.setBackgroundResource(R.drawable.yangwuoqizuo);
                    flag6 = 0;
                }
            }
        });

        dalanqiu = (ImageButton)findViewById(R.id.dalanqiu_imageButton);
        dalanqiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag7 == 0) {
                    dalanqiu.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag7 = 1;
                } else {
                    dalanqiu.setBackgroundResource(R.drawable.yangwuoqizuo);
                    flag7 = 0;
                }
            }
        });

        jianshen = (ImageButton)findViewById(R.id.jianshen_imageButton);
        jianshen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag8 == 0) {
                    jianshen.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag8 = 1;
                } else {
                    jianshen.setBackgroundResource(R.drawable.jianshen);
                    flag8 = 0;
                }
            }
        });

        qiche = (ImageButton)findViewById(R.id.qiche_imageButton);
        qiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag9 == 0) {
                    qiche.setBackgroundResource(R.drawable.yixuanbiaoqian);
                    flag9 = 1;
                } else {
                    qiche.setBackgroundResource(R.drawable.qiche);
                    flag9 = 0;
                }
            }
        });

        add = (ImageButton)findViewById(R.id.add_imageButton);

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }



    public void textViewInit(){
        paobu1 = (TextView)findViewById(R.id.paobu_textView);
        fuwocheng1 = (TextView)findViewById(R.id.fuwocheng_textView);
        tiaosheng1 = (TextView)findViewById(R.id.tiaosheng_textView);
        yangwoqizuo1 = (TextView)findViewById(R.id.yangwoqizuo_textView);
        sanbu1 = (TextView)findViewById(R.id.sanbu_textView);
        lashen1 = (TextView)findViewById(R.id.lashen_textView);
        dalanqiu1 = (TextView)findViewById(R.id.dalanqiu_textView);
        jianshen1= (TextView)findViewById(R.id.jianshen_textView);
        qiche1 = (TextView)findViewById(R.id.qiche_textView);

        add1 = (TextView)findViewById(R.id.textView4);
        add2 = (TextView)findViewById(R.id.textView5);
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