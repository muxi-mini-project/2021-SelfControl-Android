package com.bignerdranch.android.sc.label;

import android.content.Context;
import android.icu.text.UFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private ImageButton mfankui;

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

    int[] flag = {0};
    int times2 = 0;
    SendValue mSendValue;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSendValue= (SendValue) getActivity();
    }


    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view=inflater .inflate(R.layout.sport_pager,container,false) ;

            mpaobu = (ImageButton)view.findViewById(R.id.paobu_imagebutton);
            mpaobu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag[0] == 0) {
                        mpaobu.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag[0] = 1;
                        final TextView textView = view.findViewById(R.id.paobu_textView);
                        times2++;
                        String s=textView.getText().toString();
                        mSendValue.Send1(s);
                    } else {
                        mpaobu.setBackgroundResource(R.mipmap.paobu);
                        flag[0] = 0;
                    }
                }
            });


            mfuwocheng = (ImageButton)view.findViewById(R.id.fuwocheng_imageButton);
            mfuwocheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag[1] == 0) {
                        mfuwocheng.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag[1] = 1;
                        final TextView textView = view.findViewById(R.id.fuwocheng_textView);
                        times2++;
                        String s=textView.getText().toString();
                        mSendValue.Send1(s);
                    } else {
                        mfuwocheng.setBackgroundResource(R.mipmap.fuwocheng);
                        flag[1] = 0;
                    }
                }
            });

            mtiaosheng = (ImageButton)view.findViewById(R.id.tiaosheng_imageButton);
            mtiaosheng.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag[2] == 0) {
                        mtiaosheng.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag[2] = 1;
                        final TextView textView = view.findViewById(R.id.tiaosheng_textView);
                        times2++;
                        String s=textView.getText().toString();
                        mSendValue.Send1(s);
                    } else {
                        mtiaosheng.setBackgroundResource(R.mipmap.tiaosheng);
                        flag[2] = 0;
                    }
                }
            });

            myangwoqizuo = (ImageButton)view.findViewById(R.id.yangwoqizuo_imageButton);
            myangwoqizuo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag[3] == 0) {
                        myangwoqizuo.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag[3] = 1;
                        final TextView textView = view.findViewById(R.id.yangwoqizuo_textView);
                        times2++;
                        String s=textView.getText().toString();
                        mSendValue.Send1(s);
                    } else {
                        myangwoqizuo.setBackgroundResource(R.mipmap.yangwuoqizuo);
                        flag[3] = 0;
                    }
                }
            });

            msanbu = (ImageButton)view.findViewById(R.id.sanbu_imageButton);
            msanbu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag[4] == 0) {
                        msanbu.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag[4] = 1;
                        final TextView textView = view.findViewById(R.id.sanbu_textView);
                        times2++;
                        String s=textView.getText().toString();
                        mSendValue.Send1(s);
                    } else {
                        msanbu.setBackgroundResource(R.mipmap.sanbu);
                        flag[4] = 0;
                    }
                }
            });

            mlashen = (ImageButton)view.findViewById(R.id.lashen_imageButton);
            mlashen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag[5] == 0) {
                        mlashen.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag[5] = 1;
                        final TextView textView = view.findViewById(R.id.lashen_textView);
                        times2++;
                        String s=textView.getText().toString();
                        mSendValue.Send1(s);
                    } else {
                        mlashen.setBackgroundResource(R.mipmap.yangwuoqizuo);
                        flag[5] = 0;
                    }
                }
            });

            mdalanqiu = (ImageButton)view.findViewById(R.id.dalanqiu_imageButton);
            mdalanqiu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag[6] == 0) {
                        mdalanqiu.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag[6] = 1;
                        final TextView textView = view.findViewById(R.id.dalanqiu_textView);
                        times2++;
                        String s=textView.getText().toString();
                        mSendValue.Send1(s);
                    } else {
                        mdalanqiu.setBackgroundResource(R.mipmap.yangwuoqizuo);
                        flag[6] = 0;
                    }
                }
            });

            mjianshen = (ImageButton)view.findViewById(R.id.jianshen_imageButton);
            mjianshen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag[7] == 0) {
                        mjianshen.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag[7] = 1;
                        final TextView textView = view.findViewById(R.id.jianshen_textView);
                        times2++;
                        String s=textView.getText().toString();
                        mSendValue.Send1(s);
                    } else {
                        mjianshen.setBackgroundResource(R.mipmap.jianshen);
                        flag[7] = 0;
                    }
                }
            });

            mqiche = (ImageButton)view.findViewById(R.id.qiche_imageButton);
            mqiche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag[8] == 0) {
                        mqiche.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag[8] = 1;
                        final TextView textView = view.findViewById(R.id.qiche_textView);
                        times2++;
                        String s=textView.getText().toString();
                        mSendValue.Send1(s);
                        mSendValue.Send2(times2);
                    } else {
                        mqiche.setBackgroundResource(R.mipmap.qiche);
                        flag[8] = 0;
                    }
                }
            });

            mfankui = (ImageButton)view.findViewById(R.id.fankui_imageButton);

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

    public interface SendValue{
        void Send1(String s);
        void Send2(int times2);
    }

}