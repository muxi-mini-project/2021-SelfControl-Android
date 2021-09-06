package com.bignerdranch.android.sc.user.bean;

import java.util.List;

public class Report {
    /**
     * code : 200
     * msg : 获取成功
     * data : [{"title":"string","number":7,"id":0},{"title":"run","number":4,"id":0}]
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
         * title : string
         * number : 7
         * id : 0
         */

        private String title;
        private int number;
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    /**
     * number : 0
     * title : string
     */


}
