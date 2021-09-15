package com.bignerdranch.android.sc.clockpage.model;

public class FlowerResponse {
    /**
     * code : 200
     * msg : 已全部完成且数量为返回的值
     * data : 2
     */

    private int code;
    private String msg;
    private int data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
