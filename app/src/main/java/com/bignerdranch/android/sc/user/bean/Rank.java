package com.bignerdranch.android.sc.user.bean;

public class Rank {

    /**
     * code : 200
     * msg : 获取成功
     * data : {"student_id":"2020213790","week_former":0,"week_after":0,"month_former":0,"month_after":0}
     */

    private int code;
    private String msg;
    private DataDTO data;

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

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        /**
         * student_id : 2020213790
         * week_former : 0
         * week_after : 0
         * month_former : 0
         * month_after : 0
         */

        private String student_id;
        private int week_former;
        private int week_after;
        private int month_former;
        private int month_after;

        public String getStudent_id() {
            return student_id;
        }

        public void setStudent_id(String student_id) {
            this.student_id = student_id;
        }

        public int getWeek_former() {
            return week_former;
        }

        public void setWeek_former(int week_former) {
            this.week_former = week_former;
        }

        public int getWeek_after() {
            return week_after;
        }

        public void setWeek_after(int week_after) {
            this.week_after = week_after;
        }

        public int getMonth_former() {
            return month_former;
        }

        public void setMonth_former(int month_former) {
            this.month_former = month_former;
        }

        public int getMonth_after() {
            return month_after;
        }

        public void setMonth_after(int month_after) {
            this.month_after = month_after;
        }
    }
}
