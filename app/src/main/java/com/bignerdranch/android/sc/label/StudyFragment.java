package com.bignerdranch.android.sc.label;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.clockin.ClockinActivity;

public class StudyFragment extends Fragment {

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

    int[] flag = {0};
    int times3 = 0;

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

    SendValue mSendValue;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSendValue= (SendValue) getActivity();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.study_pager, container, false);
        Button button = (Button) getActivity().findViewById(R.id.next);
        button.setOnClickListener((View.OnClickListener) this);

        mzixi = (ImageButton) view.findViewById(R.id.zixi_B);
        mzixi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // IB_PullDown.setBackgroundResource(R.drawable.pulldown_button_image);
                if (flag[0] == 0) {
                    // TODO Auto-generated method stub
                    mzixi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    //ll_AirItem.setVisibility(View.VISIBLE);
                    flag[0] = 1;
                    final TextView textView = view.findViewById(R.id.zixi_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                } else {
                    mzixi.setBackgroundResource(R.mipmap.zixi);
                    //ll_AirItem.setVisibility(View.GONE);
                    flag[0] = 0;
                }
            }

        });

        myueduxinwen = (ImageButton) view.findViewById(R.id.yueduxinwen_B);
        myueduxinwen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag[1] == 0) {
                    myueduxinwen.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[1] = 1;
                    final TextView textView = view.findViewById(R.id.yueduxinwen_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                } else {
                    myueduxinwen.setBackgroundResource(R.mipmap.yueduxinwen);
                    flag[1] = 0;
                }
            }
        });

        mlianxiyueqi = (ImageButton) view.findViewById(R.id.lianxiyueqi_B);
        mlianxiyueqi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag[2] == 0) {
                    mlianxiyueqi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[2] = 1;
                    final TextView textView = view.findViewById(R.id.lianxiyueqi_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                } else {
                    mlianxiyueqi.setBackgroundResource(R.mipmap.lianxiyueqi);
                    flag[2] = 0;
                }
            }
        });

        mxuexixinyuyan = (ImageButton) view.findViewById(R.id.xuexixinyuyan_B);
        mxuexixinyuyan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag[3] == 0) {
                    mxuexixinyuyan.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[3] = 1;
                    final TextView textView = view.findViewById(R.id.xuexixinyuyan_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                } else {
                    mxuexixinyuyan.setBackgroundResource(R.mipmap.xuexixinyvyan);
                    flag[3] = 0;
                }
            }
        });

        mbeidanci = (ImageButton) view.findViewById(R.id.beidanci_B);
        mbeidanci.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag[4] == 0) {
                    mbeidanci.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[4] = 1;
                    final TextView textView = view.findViewById(R.id.beidanci_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                } else {
                    mbeidanci.setBackgroundResource(R.mipmap.beidanci);
                    flag[4] = 0;
                }
            }
        });

        mkanjilupian = (ImageButton) view.findViewById(R.id.kanjilupian_B);
        mkanjilupian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag[5] == 0) {
                    mkanjilupian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[5] = 1;
                    final TextView textView = view.findViewById(R.id.kanjilupian_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                } else {
                    mkanjilupian.setBackgroundResource(R.mipmap.kanjilupian);
                    flag[5] = 0;
                }
            }
        });

        mzuojinrijihua = (ImageButton) view.findViewById(R.id.zuojinrijihua_B);
        mzuojinrijihua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag[6] == 0) {
                    mzuojinrijihua.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[6] = 1;
                    final TextView textView = view.findViewById(R.id.zuojinrijihua_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                } else {
                    mzuojinrijihua.setBackgroundResource(R.mipmap.zuojinrijihua);
                    flag[6] = 0;
                }
            }
        });

        mtinglixunlian = (ImageButton) view.findViewById(R.id.tinglixunjian_B);
        mtinglixunlian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag[7] == 0) {
                    mtinglixunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[7] = 1;
                    final TextView textView = view.findViewById(R.id.tinglixunjian_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                } else {
                    mtinglixunlian.setBackgroundResource(R.mipmap.tinglilianxi);
                    flag[7] = 0;
                }
            }
        });

        mlianzi = (ImageButton) view.findViewById(R.id.lianzi_B);
        mlianzi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag[8] == 0) {
                    mlianzi.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[8] = 1;
                    final TextView textView = view.findViewById(R.id.lianzi_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                } else {
                    mlianzi.setBackgroundResource(R.mipmap.lianzi);
                    flag[8] = 0;
                }
            }
        });

        myingyuyueduxunlian = (ImageButton) view.findViewById(R.id.yingyuyueduxunlian_B);
        myingyuyueduxunlian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (flag[9] == 0) {
                    myingyuyueduxunlian.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                    flag[9] = 1;
                    final TextView textView = view.findViewById(R.id.yingyuyueduxunlian_T);
                    times3++;
                    String s=textView.getText().toString();
                    mSendValue.Send1(s);
                    mSendValue.Send2(times3);
                } else {
                    myingyuyueduxunlian.setBackgroundResource(R.mipmap.yingyuyueduxunlian);
                    flag[9] = 0;
                }
            }
        });

        mButton = (Button) getActivity().findViewById(R.id.next);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), ClockinActivity.class);
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

    public interface SendValue{
        void Send1(String s);
        void Send2(int times3);
    }

}
