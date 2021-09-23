package com.bignerdranch.android.sc.rank.newrank.bean;


public class Message {
    /**
     * code : 200
     * msg : 默认为1 若要修改隐私 直接使用修改用户信息
     * data : 1
     */

    private int code;
    private String msg;
    private int data;

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
