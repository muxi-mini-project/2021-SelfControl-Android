package com.bignerdranch.android.sc.punch;

import com.bignerdranch.android.sc.R;

public class LabelPunch {
    private int id;
    private int number;
    private String title;

    public LabelPunch(int imgID,String label_V,int label_Time){
        this.id = imgID;
        this.number = label_Time;
        this.title = label_V;
    }

    public int getId() {
        return id;
    }

    public void setID(String title) {
        this.title = title;
        if(title == "吃水果") id = Integer.parseInt(String.valueOf(R.mipmap.chishuiguo));
        if(title == "吃早餐") id = Integer.parseInt(String.valueOf(R.mipmap.chizaocan));
        if(title == "多喝水") id = Integer.parseInt(String.valueOf(R.mipmap.duoheshui));
        if(title == "拒绝夜宵") id = Integer.parseInt(String.valueOf(R.mipmap.jujueyexiao));
        if(title == "拒绝饮料") id = Integer.parseInt(String.valueOf(R.mipmap.jujueyinliao));
        if(title == "拒绝久坐") id = Integer.parseInt(String.valueOf(R.mipmap.jujuejiuzuo));
        if(title == "早起") id = Integer.parseInt(String.valueOf(R.mipmap.zaoqi));
        if(title == "早睡") id = Integer.parseInt(String.valueOf(R.mipmap.zaoshui));
        if(title == "不翘二郎腿") id = Integer.parseInt(String.valueOf(R.mipmap.buqiaoerlangtui));
        if(title == "早起空腹喝水") id = Integer.parseInt(String.valueOf(R.mipmap.zaoqikongfuheshui));
        if(title == "跑步") id = Integer.parseInt(String.valueOf(R.mipmap.paobu));
        if(title == "俯卧撑") id = Integer.parseInt(String.valueOf(R.mipmap.fuwocheng));
        if(title == "跳绳") id = Integer.parseInt(String.valueOf(R.mipmap.tiaosheng));
        if(title == "仰卧起坐") id = Integer.parseInt(String.valueOf(R.mipmap.yangwuoqizuo));
        if(title == "散步") id = Integer.parseInt(String.valueOf(R.mipmap.sanbu));
        if(title == "拉伸") id = Integer.parseInt(String.valueOf(R.mipmap.lashen));
        if(title == "打篮球") id = Integer.parseInt(String.valueOf(R.mipmap.dalanqiu));
        if(title == "健身") id = Integer.parseInt(String.valueOf(R.mipmap.jianshen));
        if(title == "骑车") id = Integer.parseInt(String.valueOf(R.mipmap.qiche));
        if(title == "自习") id = Integer.parseInt(String.valueOf(R.mipmap.zixi));
        if(title == "阅读新闻") id = Integer.parseInt(String.valueOf(R.mipmap.yueduxinwen));
        if(title == "练习乐器") id = Integer.parseInt(String.valueOf(R.mipmap.lianxiyueqi));
        if(title == "学习新语言") id = Integer.parseInt(String.valueOf(R.mipmap.xuexixinyvyan));
        if(title == "背单词") id = Integer.parseInt(String.valueOf(R.mipmap.beidanci));
        if(title == "看纪录片") id = Integer.parseInt(String.valueOf(R.mipmap.kanjilupian));
        if(title == "做今日计划") id = Integer.parseInt(String.valueOf(R.mipmap.zuojinrijihua));
        if(title == "听力训练") id = Integer.parseInt(String.valueOf(R.mipmap.tinglilianxi));
        if(title == "练字") id = Integer.parseInt(String.valueOf(R.mipmap.lianzi));
        if(title == "英语阅读训练") id = Integer.parseInt(String.valueOf(R.mipmap.yingyuyueduxunlian));


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

