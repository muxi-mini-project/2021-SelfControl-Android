package com.bignerdranch.android.sc.login;

public class LoginResponse {

    /**
     * code : 200
     * msg : 将student_id作为token保留
     * data : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzQ4MiIsImV4cCI6MTYzMTU3Mjc1NSwiaWF0IjoxNjMwODUyNzU1fQ.ED6OnHxYoAqlEaUJc-NVa7imAVD-NgUSDx6_5ATLCgA
     */

    private int code;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
