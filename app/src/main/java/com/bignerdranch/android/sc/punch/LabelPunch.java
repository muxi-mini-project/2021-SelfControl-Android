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

    public int getImgID(String title) {

        if(title.equals("吃水果") ) mImgID = R.mipmap.chishuiguo;
        if(title.equals("吃早餐")) mImgID = R.mipmap.chizaocan;
        if(title.equals("多喝水")) mImgID = R.mipmap.duoheshui;
        if(title.equals("拒绝夜宵")) mImgID = R.mipmap.jujueyexiao;
        if(title.equals("拒绝饮料")) mImgID = R.mipmap.jujueyinliao;
        if(title.equals("拒绝久坐")) mImgID = R.mipmap.jujuejiuzuo;
        if(title.equals("早起")) mImgID = R.mipmap.zaoqi;
        if(title.equals("早睡")) mImgID = R.mipmap.zaoshui;
        if(title.equals("不翘二郎腿")) mImgID = R.mipmap.buqiaoerlangtui;
        if(title.equals("早起空腹喝水")) mImgID = R.mipmap.zaoqikongfuheshui;
        if(title.equals("跑步")) mImgID = R.mipmap.paobu;
        if(title.equals("俯卧撑")) mImgID = R.mipmap.fuwocheng;
        if(title.equals("跳绳")) mImgID = R.mipmap.tiaosheng;
        if(title.equals("仰卧起坐")) mImgID = R.mipmap.yangwuoqizuo;
        if(title.equals("散步")) mImgID = R.mipmap.sanbu;
        if(title.equals("拉伸")) mImgID = R.mipmap.lashen;
        if(title.equals("打篮球")) mImgID = R.mipmap.dalanqiu;
        if(title.equals("健身")) mImgID = R.mipmap.jianshen;
        if(title.equals("骑车")) mImgID = R.mipmap.qiche;
        if(title.equals("自习")) mImgID = R.mipmap.zixi;
        if(title.equals("阅读新闻")) mImgID = R.mipmap.yueduxinwen;
        if(title.equals("练习乐器"))mImgID = R.mipmap.lianxiyueqi;
        if(title.equals("学习新语言")) mImgID = R.mipmap.xuexixinyvyan;
        if(title.equals("背单词")) mImgID = R.mipmap.beidanci;
        if(title.equals("看纪录片")) mImgID = R.mipmap.kanjilupian;
        if(title.equals("做今日计划")) mImgID = R.mipmap.zuojinrijihua;
        if(title.equals("听力训练")) mImgID = R.mipmap.tinglilianxi;
        if(title.equals("练字")) mImgID = R.mipmap.lianzi;
        if(title.equals("英语阅读训练")) mImgID = R.mipmap.yingyuyueduxunlian;
        return mImgID;
    }

    public void setImgID(String title) {
        this.title = title;
        if(title == "吃水果") mImgID = R.mipmap.chishuiguo;
        if(title == "吃早餐") mImgID = R.mipmap.chizaocan;
        if(title == "多喝水") mImgID = R.mipmap.duoheshui;
        if(title == "拒绝夜宵") mImgID = R.mipmap.jujueyexiao;
        if(title == "拒绝饮料") mImgID = R.mipmap.jujueyinliao;
        if(title == "拒绝久坐") mImgID = R.mipmap.jujuejiuzuo;
        if(title == "早起") mImgID = R.mipmap.zaoqi;
        if(title == "早睡") mImgID = R.mipmap.zaoshui;
        if(title == "不翘二郎腿") mImgID = R.mipmap.buqiaoerlangtui;
        if(title == "早起空腹喝水") mImgID = R.mipmap.zaoqikongfuheshui;
        if(title == "跑步") mImgID = R.mipmap.paobu;
        if(title == "俯卧撑") mImgID = R.mipmap.fuwocheng;
        if(title == "跳绳") mImgID = R.mipmap.tiaosheng;
        if(title == "仰卧起坐") mImgID = R.mipmap.yangwuoqizuo;
        if(title == "散步") mImgID = R.mipmap.sanbu;
        if(title == "拉伸") mImgID = R.mipmap.lashen;
        if(title == "打篮球") mImgID = R.mipmap.dalanqiu;
        if(title == "健身") mImgID = R.mipmap.jianshen;
        if(title == "骑车") mImgID = R.mipmap.qiche;
        if(title == "自习") mImgID = R.mipmap.zixi;
        if(title == "阅读新闻") mImgID = R.mipmap.yueduxinwen;
        if(title == "练习乐器") mImgID = R.mipmap.lianxiyueqi;
        if(title == "学习新语言") mImgID = R.mipmap.xuexixinyvyan;
        if(title == "背单词") mImgID = R.mipmap.beidanci;
        if(title == "看纪录片") mImgID = R.mipmap.kanjilupian;
        if(title == "做今日计划") mImgID = R.mipmap.zuojinrijihua;
        if(title == "听力训练") mImgID = R.mipmap.tinglilianxi;
        if(title == "练字") mImgID = R.mipmap.lianzi;
        if(title == "英语阅读训练") mImgID = R.mipmap.yingyuyueduxunlian;


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

