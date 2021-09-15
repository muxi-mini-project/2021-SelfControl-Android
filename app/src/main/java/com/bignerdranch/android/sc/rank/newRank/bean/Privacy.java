package com.bignerdranch.android.sc.rank.newRank.bean;


public class Privacy {
    /**
     * code : 200
     * msg : 默认为1 若要修改隐私 直接使用修改用户信息
     * data : 1
     */

    private int code;
    private String msg;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    private int data;
}
