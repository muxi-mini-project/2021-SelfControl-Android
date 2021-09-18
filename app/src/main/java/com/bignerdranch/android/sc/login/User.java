package com.bignerdranch.android.sc.login;

public class User {

    /**
     * code : 200
     * msg : 获取成功
     * data : {"student_id":"2020213790","name":"小樨","password":"q1234567@","user_picture":"www.baidu.com","gold":0,"privacy":1,"current_backdrop":6}
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
         * name : 小樨
         * password : q1234567@
         * user_picture : www.baidu.com
         * gold : 0
         * privacy : 1
         * current_backdrop : 6
         */

        private String student_id;
        private String name;
        private String password;
        private String user_picture;
        private int gold;
        private Integer privacy;
        private int current_backdrop;

        public DataDTO(String id, String password) {
            this.student_id = id;
            this.password = password;
        }

        public DataDTO(int current_backdrop) {
            this.current_backdrop = current_backdrop;
        }

        public DataDTO() {

        }

        public String getStudent_id() {
            return student_id;
        }

        public DataDTO(Integer privacy){this.privacy = privacy;}

        public void setStudent_id(String student_id) {
            this.student_id = student_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUser_picture() {
            return user_picture;
        }

        public void setUser_picture(String user_picture) {
            this.user_picture = user_picture;
        }

        public int getGold() {
            return gold;
        }

        public void setGold(int gold) {
            this.gold = gold;
        }

        public int getPrivacy() {
            return privacy;
        }

        public void setPrivacy(int privacy) {
            this.privacy = privacy;
        }

        public int getCurrent_backdrop() {
            return current_backdrop;
        }

        public void setCurrent_backdrop(int current_backdrop) {
            this.current_backdrop = current_backdrop;
        }
    }
}




























