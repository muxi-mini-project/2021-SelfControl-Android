package com.bignerdranch.android.sc.user.bean;

import java.util.List;

public class GoldHistory {


    /**
     * code : 200
     * msg : 获取成功
     * data : [{"student_id":"2020213790","time":"2021-08-06 19:05:05","change_number":200,"residual_number":600,"reason":"加二百 个"},{"student_id":"2020213790","time":"2021-08-06 19:05:05","change_number":200,"residual_number":600,"reason":"加二百 个"},{"student_id":"2020213790","time":"2021-08-06 19:08:05","change_number":100,"residual_number":700,"reason":"+100"}]
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
         * student_id : 2020213790
         * time : 2021-08-06 19:05:05
         * change_number : 200
         * residual_number : 600
         * reason : 加二百 个
         */

        private String student_id;
        private String time;
        private int change_number;
        private int residual_number;
        private String reason;

        public String getStudent_id() {
            return student_id;
        }

        public void setStudent_id(String student_id) {
            this.student_id = student_id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getChange_number() {
            return change_number;
        }

        public void setChange_number(int change_number) {
            this.change_number = change_number;
        }

        public int getResidual_number() {
            return residual_number;
        }

        public void setResidual_number(int residual_number) {
            this.residual_number = residual_number;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
