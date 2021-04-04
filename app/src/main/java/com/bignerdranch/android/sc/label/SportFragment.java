package com.bignerdranch.android.sc.label;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;

public class SportFragment extends Fragment {
    private ImageButton mpaobu;
    private ImageButton mfuwocheng;
    private ImageButton mtiaosheng;
    private ImageButton myangwoqizuo;
    private ImageButton msanbu;
    private ImageButton mlashen;
    private ImageButton mdalanqiu;
    private ImageButton mjianshen;
    private ImageButton mqiche;

    private ImageButton madd;

    private TextView mpaobu1;
    private TextView mfuwocheng1;
    private TextView mtiaosheng1;
    private TextView myangwoqizuo1;
    private TextView msanbu1;
    private TextView mlashen1;
    private TextView mdalanqiu1;
    private TextView mjianshen1;
    private TextView mqiche1;

    private TextView madd1;
    private TextView madd2;

    private int flag1 = 0;
    private int flag2 = 0;
    private int flag3 = 0;
    private int flag4 = 0;
    private int flag5 = 0;
    private int flag6 = 0;
    private int flag7 = 0;
    private int flag8 = 0;
    private int flag9 = 0;


    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.sport_pager,container,false) ;

            mpaobu = (ImageButton)view.findViewById(R.id.paobu_imagebutton);
            mpaobu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag1 == 0) {
                        mpaobu.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag1 = 1;
                    } else {
                        mpaobu.setBackgroundResource(R.mipmap.paobu);
                        flag1 = 0;
                    }
                }
            });


            mfuwocheng = (ImageButton)view.findViewById(R.id.fuwocheng_imageButton);
            mfuwocheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag2 == 0) {
                        mfuwocheng.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag2 = 1;
                    } else {
                        mfuwocheng.setBackgroundResource(R.mipmap.fuwocheng);
                        flag2 = 0;
                    }
                }
            });

            mtiaosheng = (ImageButton)view.findViewById(R.id.tiaosheng_imageButton);
            mtiaosheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag3 == 0) {
                        mtiaosheng.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag3 = 1;
                    } else {
                        mtiaosheng.setBackgroundResource(R.mipmap.tiaosheng);
                        flag3 = 0;
                    }
                }
            });

            myangwoqizuo = (ImageButton)view.findViewById(R.id.yangwoqizuo_imageButton);
            myangwoqizuo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag4 == 0) {
                        myangwoqizuo.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag4 = 1;
                    } else {
                        myangwoqizuo.setBackgroundResource(R.mipmap.yangwuoqizuo);
                        flag4 = 0;
                    }
                }
            });

            msanbu = (ImageButton)view.findViewById(R.id.sanbu_imageButton);
            msanbu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag5 == 0) {
                        msanbu.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag5 = 1;
                    } else {
                        msanbu.setBackgroundResource(R.mipmap.sanbu);
                        flag5 = 0;
                    }
                }
            });

            mlashen = (ImageButton)view.findViewById(R.id.lashen_imageButton);
            mlashen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag6 == 0) {
                        mlashen.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag6 = 1;
                    } else {
                        mlashen.setBackgroundResource(R.mipmap.yangwuoqizuo);
                        flag6 = 0;
                    }
                }
            });

            mdalanqiu = (ImageButton)view.findViewById(R.id.dalanqiu_imageButton);
            mdalanqiu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag7 == 0) {
                        mdalanqiu.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag7 = 1;
                    } else {
                        mdalanqiu.setBackgroundResource(R.mipmap.yangwuoqizuo);
                        flag7 = 0;
                    }
                }
            });

            mjianshen = (ImageButton)view.findViewById(R.id.jianshen_imageButton);
            mjianshen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag8 == 0) {
                        mjianshen.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag8 = 1;
                    } else {
                        mjianshen.setBackgroundResource(R.mipmap.jianshen);
                        flag8 = 0;
                    }
                }
            });

            mqiche = (ImageButton)view.findViewById(R.id.qiche_imageButton);
            mqiche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag9 == 0) {
                        mqiche.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag9 = 1;
                    } else {
                        mqiche.setBackgroundResource(R.mipmap.qiche);
                        flag9 = 0;
                    }
                }
            });

            madd = (ImageButton)view.findViewById(R.id.add_imageButton);

            mpaobu1 = (TextView)view.findViewById(R.id.paobu_textView);
            mfuwocheng1 = (TextView)view.findViewById(R.id.fuwocheng_textView);
            mtiaosheng1 = (TextView)view.findViewById(R.id.tiaosheng_textView);
            myangwoqizuo1 = (TextView)view.findViewById(R.id.yangwoqizuo_textView);
            msanbu1 = (TextView)view.findViewById(R.id.sanbu_textView);
            mlashen1 = (TextView)view.findViewById(R.id.lashen_textView);
            mdalanqiu1 = (TextView)view.findViewById(R.id.dalanqiu_textView);
            mjianshen1= (TextView)view.findViewById(R.id.jianshen_textView);
            mqiche1 = (TextView)view.findViewById(R.id.qiche_textView);

            madd1 = (TextView)view.findViewById(R.id.textView4);
            madd2 = (TextView)view.findViewById(R.id.textView5);

        return view;
    }

}