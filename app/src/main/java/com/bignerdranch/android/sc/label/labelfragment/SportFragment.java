package com.bignerdranch.android.sc.label.labelfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.punch.bean.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.bean.ResponseData;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.punch.bean.LabelPunch;
import com.bignerdranch.android.sc.punch.bean.SingleMessage;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

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

    private int flag1 = 0;
    private int flag2 = 0;
    private int flag3 = 0;
    private int flag4 = 0;
    private int flag5 = 0;
    private int flag6 = 0;
    private int flag7 = 0;
    private int flag8 = 0;
    private int flag9 = 0;
    private List<LabelPunch> mLabelPunchList;


    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.sport_pager,container,false) ;

            mpaobu = (ImageButton)view.findViewById(R.id.paobu_imagebutton);
            mpaobu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag1 == 0) {
                        mpaobu.setBackgroundResource(R.mipmap.yixuanbiaoqian);
                        flag1 = 1;
                        createRequest("跑步");

                    } else {
                        mpaobu.setBackgroundResource(R.mipmap.paobu);
                        flag1 = 0;
                        deleteRequest("跑步");

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
                        createRequest("俯卧撑");

                    } else {
                        mfuwocheng.setBackgroundResource(R.mipmap.fuwocheng);
                        flag2 = 0;
                        deleteRequest("俯卧撑");

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
                        createRequest("跳绳");

                    } else {
                        mtiaosheng.setBackgroundResource(R.mipmap.tiaosheng);
                        flag3 = 0;
                        deleteRequest("跳绳");

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
                        createRequest("仰卧起坐");

                    } else {
                        myangwoqizuo.setBackgroundResource(R.mipmap.yangwuoqizuo);
                        flag4 = 0;
                        deleteRequest("仰卧起坐");

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
                        createRequest("散步");

                    } else {
                        msanbu.setBackgroundResource(R.mipmap.sanbu);
                        flag5 = 0;
                        deleteRequest("散步");

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
                        createRequest("拉伸");

                    } else {
                        mlashen.setBackgroundResource(R.mipmap.yangwuoqizuo);
                        flag6 = 0;
                        deleteRequest("拉伸");

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
                        createRequest("打篮球");

                    } else {
                        mdalanqiu.setBackgroundResource(R.mipmap.yangwuoqizuo);
                        flag7 = 0;
                        deleteRequest("打篮球");

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
                        createRequest("健身");

                    } else {
                        mjianshen.setBackgroundResource(R.mipmap.jianshen);
                        flag8 = 0;
                        deleteRequest("健身");

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
                        createRequest("骑车");

                    } else {
                        mqiche.setBackgroundResource(R.mipmap.qiche);
                        flag9 = 0;
                        deleteRequest("骑车");

                    }
                }
            });
        getMyPunch();
        return view;
    }
    public void createRequest(String title) {
        NetUtil.getInstance().getApi().create(token,new LabelPunchTitle(title)).enqueue(new Callback<SingleMessage>() {

            @Override
            public void onResponse(Call<SingleMessage> call, Response<SingleMessage> response) {

            }

            @Override
            public void onFailure(Call<SingleMessage> call, Throwable t) {

            }
        });
    }
    public void deleteRequest(String title) {
        NetUtil.getInstance().getApi().removeLabel(token, new LabelPunchTitle(title)).enqueue(new Callback<SingleMessage>() {

            @Override
            public void onResponse(Call<SingleMessage> call, Response<SingleMessage> response) {

            }

            @Override
            public void onFailure(Call<SingleMessage> call, Throwable t) {

            }
        });
    }

    private void getMyPunch() {
        NetUtil.getInstance().getApi().getLabels(token).enqueue(new Callback<ResponseData<List<LabelPunch>>>() {

            @Override
            public void onResponse(Call<ResponseData<List<LabelPunch>>> call, Response<ResponseData<List<LabelPunch>>> response) {
                mLabelPunchList = response.body().getData();
                if(response.body() != null) {
                    for (int i = 0; i < mLabelPunchList.size() ; i++ ){
                        if(mLabelPunchList.get(i).getTitle().equals("跑步")) {mpaobu.setBackgroundResource(R.mipmap.yixuanbiaoqian); flag1 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("俯卧撑")) {mfuwocheng.setBackgroundResource(R.mipmap.yixuanbiaoqian); flag2 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("跳绳")) {mtiaosheng.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag3 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("仰卧起坐")) {myangwoqizuo.setBackgroundResource(R.mipmap.yixuanbiaoqian); flag4 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("散步")){ msanbu.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag5 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("拉伸")) {mlashen.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag6 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("打篮球")) {mdalanqiu.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag7 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("健身")) {mjianshen.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag8 = 1;}
                        if(mLabelPunchList.get(i).getTitle().equals("骑车")) {mqiche.setBackgroundResource(R.mipmap.yixuanbiaoqian);flag9 = 1;}
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseData<List<LabelPunch>>> call, Throwable t) {
            }
        });
    }

}