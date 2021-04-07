package com.bignerdranch.android.sc.punch;

import com.bignerdranch.android.sc.R;

public class LabelPunch {
    private int mImgID;
    private String mLabe_Title;
    private int mLabel_Time;
    private int number;
    private String title;

    public LabelPunch(int imgID,String label_V,int label_Time){
        this.mImgID = imgID;
        this.mLabel_Time = label_Time;
        this.mLabe_Title = label_V;
    }

    public int getImgID() {
        return mImgID;
    }

    public void setImgID(String title) {
        this.title = title;
//        if(title == "吃水果") mImgID = Integer.parseInt(String.valueOf(1500045));
//        if(title == "吃早餐") mImgID = Integer.parseInt(String.valueOf(R.mipmap.chi));
//        if(title == "多喝水") mImgID = Integer.parseInt(String.valueOf(R.mipmap.duo));
//        if(title == "拒绝夜宵") mImgID = Integer.parseInt(String.valueOf(R.mipmap.jujuey));
//        if(title == "拒绝饮料") mImgID = Integer.parseInt(String.valueOf(R.mipmap.juju));
//        if(title == "拒绝久坐") mImgID = Integer.parseInt(String.valueOf(R.mipmap.juj));
//        if(title == "早起") mImgID = Integer.parseInt(String.valueOf(R.mipmap.zao));
//        if(title == "早睡") mImgID = Integer.parseInt(String.valueOf(R.mipmap.zao));
//        if(title == "不翘二郎腿") mImgID = Integer.parseInt(String.valueOf(R.mipmap.bu));
//        if(title == "早起空腹喝水") mImgID = Integer.parseInt(String.valueOf(R.mipmap.zao));
//        if(title == "跑步") mImgID = Integer.parseInt(String.valueOf(R.mipmap.pao));
//        if(title == "俯卧撑") mImgID = Integer.parseInt(String.valueOf(R.mipmap.fu));
//        if(title == "跳绳") mImgID = Integer.parseInt(String.valueOf(R.mipmap.tiao));
//        if(title == "仰卧起坐") mImgID = Integer.parseInt(String.valueOf(R.mipmap.yan));
//        if(title == "散步") mImgID = Integer.parseInt(String.valueOf(R.mipmap.san));
//        if(title == "拉伸") mImgID = Integer.parseInt(String.valueOf(R.mipmap.la));
//        if(title == "打篮球") mImgID = Integer.parseInt(String.valueOf(R.mipmap.));
//        if(title == "健身") mImgID = Integer.parseInt(String.valueOf(R.mipmap.jian));
//        if(title == "骑车") mImgID = Integer.parseInt(String.valueOf(R.mipmap.));
//        if(title == "自习") mImgID = Integer.parseInt(String.valueOf(R.mipmap.));
//        if(title == "阅读新闻") mImgID = Integer.parseInt(String.valueOf(R.mipmap.));
//        if(title == "练习乐器") mImgID = Integer.parseInt(String.valueOf(R.mipmap.lia));
//        if(title == "学习新语言") mImgID = Integer.parseInt(String.valueOf(R.mipmap.xue));
//        if(title == "背单词") mImgID = Integer.parseInt(String.valueOf(R.mipmap.bei));
//        if(title == "看纪录片") mImgID = Integer.parseInt(String.valueOf(R.mipmap.kan));
//        if(title == "做今日计划") mImgID = Integer.parseInt(String.valueOf(R.mipmap.zuo));
//        if(title == "听力训练") mImgID = Integer.parseInt(String.valueOf(R.mipmap.ting));
//        if(title == "练字") mImgID = Integer.parseInt(String.valueOf(R.mipmap.lian));
//        if(title == "英语阅读训练") mImgID = Integer.parseInt(String.valueOf(R.mipmap.ying));


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

