package com.bignerdranch.android.sc.user.bean;

import java.util.List;

public class Week {

    /**
     * code : 200
     * msg : 获取成功
     * data : [{"week":1,"number":0},{"week":2,"number":0},{"week":3,"number":0},{"week":4,"number":0},{"week":5,"number":0},{"week":6,"number":0}]
     */

    private int code;
    private String msg;
    private List<DataDTO> data;

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

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * week : 1
         * number : 0
         */

        private int week;
        private int number;

        public int getWeek() {
            return week;
        }

        public void setWeek(int week) {
            this.week = week;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}


