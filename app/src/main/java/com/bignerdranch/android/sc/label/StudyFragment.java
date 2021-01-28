package com.bignerdranch.android.sc.label;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.clockin.Clockin;

public class StudyFragment extends Fragment {
    int f1 = 0;
    int f2 = 0;
    int f3 = 0;
    int f4 = 0;
    int f5 = 0;
    int f6 = 0;
    int f7 = 0;
    int f8 = 0;
    int f9 = 0;
    int f10 = 0;

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

    private ImageButton mfankui;

    private Button mButton;

    private TextView mzixi1;
    private TextView myueduxinwen1;
    private TextView mlianxiyueqi1;
    private TextView mxuexixinyuyan1;
    private TextView mbeidanci1;
    private TextView mkanjilupian1;
    private TextView mzuojinrijihua1;
    private TextView mtinglixunlian1;
    private TextView mlianzi1;
    private TextView myingyuyueduxunlian1;

    private TextView madd1;
    private TextView madd2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.study_pager, container, false);
        Button button = (Button) getActivity().findViewById(R.id.next);
        button.setOnClickListener((View.OnClickListener) this);

        mzixi = (ImageButton) view.findViewById(R.id.zixi_B);
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

        myueduxinwen = (ImageButton) view.findViewById(R.id.yueduxinwen_B);
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

        mlianxiyueqi = (ImageButton) view.findViewById(R.id.lianxiyueqi_B);
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

        mxuexixinyuyan = (ImageButton) view.findViewById(R.id.xuexixinyuyan_B);
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

        mbeidanci = (ImageButton) view.findViewById(R.id.beidanci_B);
        mbeidanci.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (f5 == 0) {
                    mbeidanci.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f5 = 1;
                } else {
                    mbeidanci.setBackgroundResource(R.mipmap.beidanci);
                    f5 = 0;
                }
            }
        });

        mkanjilupian = (ImageButton) view.findViewById(R.id.kanjilupian_B);
        mkanjilupian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (f6 == 0) {
                    mkanjilupian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f6 = 1;
                } else {
                    mkanjilupian.setBackgroundResource(R.mipmap.kanjilupian);
                    f6 = 0;
                }
            }
        });

        mzuojinrijihua = (ImageButton) view.findViewById(R.id.zuojinrijihua_B);
        mzuojinrijihua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (f7 == 0) {
                    mzuojinrijihua.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f7 = 1;
                } else {
                    mzuojinrijihua.setBackgroundResource(R.mipmap.zuojinrijihua);
                    f7 = 0;
                }
            }
        });

        mtinglixunlian = (ImageButton) view.findViewById(R.id.tinglixunjian_B);
        mtinglixunlian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (f8 == 0) {
                    mtinglixunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f8 = 1;
                } else {
                    mtinglixunlian.setBackgroundResource(R.mipmap.tinglilianxi);
                    f8 = 0;
                }
            }
        });

        mlianzi = (ImageButton) view.findViewById(R.id.lianzi_B);
        mlianzi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (f9 == 0) {
                    mlianzi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f9 = 1;
                } else {
                    mlianzi.setBackgroundResource(R.mipmap.lianzi);
                    f9 = 0;
                }
            }
        });

        myingyuyueduxunlian = (ImageButton) view.findViewById(R.id.yingyuyueduxunlian_B);
        myingyuyueduxunlian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (f10 == 0) {
                    myingyuyueduxunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    f10 = 1;
                } else {
                    myingyuyueduxunlian.setBackgroundResource(R.mipmap.yingyuyueduxunlian);
                    f10 = 0;
                }
            }
        });

        mButton = (Button) getActivity().findViewById(R.id.next);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), Clockin.class);
                startActivity(intent);
            }
        });

        mfankui = (ImageButton) view.findViewById(R.id.fankui_imageButton);


        mzixi1 = (TextView) view.findViewById(R.id.zixi_T);
        myueduxinwen1 = (TextView) view.findViewById(R.id.yueduxinwen_T);
        mlianxiyueqi1 = (TextView) view.findViewById(R.id.lianxiyueqi_T);
        mxuexixinyuyan1 = (TextView) view.findViewById(R.id.xuexixinyuyan_T);
        mbeidanci1 = (TextView) view.findViewById(R.id.beidanci_T);
        mkanjilupian1 = (TextView) view.findViewById(R.id.kanjilupian_T);
        mzuojinrijihua1 = (TextView) view.findViewById(R.id.zuojinrijihua_T);
        mtinglixunlian1 = (TextView) view.findViewById(R.id.tinglixunjian_T);
        mlianzi1 = (TextView) view.findViewById(R.id.lianzi_T);
        myingyuyueduxunlian1 = (TextView) view.findViewById(R.id.yingyuyueduxunlian_T);

        madd1 = (TextView) view.findViewById(R.id.textView4);
        madd2 = (TextView) view.findViewById(R.id.textView5);

        return view;
    }

}
