package com.bignerdranch.android.sc.punch.bean;

public class ResponseData<T> {
    /**
     * code : 200
     * msg : 获取成功
     * data : [{"title":"跑步","number":0,"id":1500026},{"title":"喝水","number":0,"id":0}]
     */

    private int code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
