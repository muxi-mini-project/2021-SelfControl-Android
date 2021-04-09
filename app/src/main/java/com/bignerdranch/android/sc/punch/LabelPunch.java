package com.bignerdranch.android.sc.punch;

import com.bignerdranch.android.sc.R;

public class LabelPunch {
    private int mImgID;
    private String mLabe_Title;
    private int mLabel_Time;
    private int number;
    private String title;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LabelPunch(int imgID,String label_V,int label_Time){
        this.mLabel_Time = label_Time;
        this.mLabe_Title = label_V;
        this.mImgID = imgID;
    }

    public int getImgID() {
        return mImgID;
    }

    public void setImgID(String title) {
        this.title = title;
        if(title == "吃水果") mImgID = (R.mipmap.chishuiguo);
        if(title == "吃早餐") mImgID = Integer.parseInt(String.valueOf(R.mipmap.chizaocan));
        if(title == "多喝水") mImgID = Integer.parseInt(String.valueOf(R.mipmap.duoheshui));
        if(title == "拒绝夜宵") mImgID = Integer.parseInt(String.valueOf(R.mipmap.jujueyexiao));
        if(title == "拒绝饮料") mImgID = Integer.parseInt(String.valueOf(R.mipmap.jujueyinliao));
        if(title == "拒绝久坐") mImgID = Integer.parseInt(String.valueOf(R.mipmap.jujuejiuzuo));
        if(title == "早起") mImgID = Integer.parseInt(String.valueOf(R.mipmap.zaoqi));
        if(title == "早睡") mImgID = Integer.parseInt(String.valueOf(R.mipmap.zaoshui));
        if(title == "不翘二郎腿") mImgID = Integer.parseInt(String.valueOf(R.mipmap.buqiaoerlangtui));
        if(title == "早起空腹喝水") mImgID = Integer.parseInt(String.valueOf(R.mipmap.zaoqikongfuheshui));
        if(title == "跑步") mImgID = Integer.parseInt(String.valueOf(R.mipmap.paobu));
        if(title == "俯卧撑") mImgID = Integer.parseInt(String.valueOf(R.mipmap.fuwocheng));
        if(title == "跳绳") mImgID = Integer.parseInt(String.valueOf(R.mipmap.tiaosheng));
        if(title == "仰卧起坐") mImgID = Integer.parseInt(String.valueOf(R.mipmap.yangwuoqizuo));
        if(title == "散步") mImgID = Integer.parseInt(String.valueOf(R.mipmap.sanbu));
        if(title == "拉伸") mImgID = Integer.parseInt(String.valueOf(R.mipmap.lashen));
        if(title == "打篮球") mImgID = Integer.parseInt(String.valueOf(R.mipmap.dalanqiu));
        if(title == "健身") mImgID = Integer.parseInt(String.valueOf(R.mipmap.jianshen));
        if(title == "骑车") mImgID = Integer.parseInt(String.valueOf(R.mipmap.qiche));
        if(title == "自习") mImgID = Integer.parseInt(String.valueOf(R.mipmap.zixi));
        if(title == "阅读新闻") mImgID = Integer.parseInt(String.valueOf(R.mipmap.yueduxinwen));
        if(title == "练习乐器") mImgID = Integer.parseInt(String.valueOf(R.mipmap.lianxiyueqi));
        if(title == "学习新语言") mImgID = Integer.parseInt(String.valueOf(R.mipmap.xuexixinyvyan));
        if(title == "背单词") mImgID = Integer.parseInt(String.valueOf(R.mipmap.beidanci));
        if(title == "看纪录片") mImgID = Integer.parseInt(String.valueOf(R.mipmap.kanjilupian));
        if(title == "做今日计划") mImgID = Integer.parseInt(String.valueOf(R.mipmap.zuojinrijihua));
        if(title == "听力训练") mImgID = Integer.parseInt(String.valueOf(R.mipmap.tinglilianxi));
        if(title == "练字") mImgID = Integer.parseInt(String.valueOf(R.mipmap.lianzi));
        if(title == "英语阅读训练") mImgID = Integer.parseInt(String.valueOf(R.mipmap.yingyuyueduxunlian));


    }

    public String getLabel_Title() {
        return mLabe_Title;
    }

    public void setLabel_Title(String label_V) {
        mLabe_Title = label_V;
    }

    public int getLabel_Time() {
        return mLabel_Time;
    }

    public void setLabel_Time(int number) {
        mLabel_Time = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

