package com.bignerdranch.android.sc.rank.newrank.bean;


import java.util.List;

public class RankItem {


    /**
     * code : 200
     * msg : 获取所有用户
     * data : [{"student_id":"2020213717","name":"朱朱不知好歹","number":7,"ranking":1,"user_picture":"string"},{"student_id":"2020213664","name":"小樨","number":2,"ranking":2,"user_picture":"www.baidu.com"}]
     */

    private int code;
    private String msg;
    private List<RankDataBean> data;

    public List<RankDataBean> getData() {
        return data;
    }

    public void setData(List<RankDataBean> data) {
        this.data = data;
    }

    public static class RankDataBean {

        /**
         * student_id : 2020213717
         * name : 朱朱不知好歹
         * number : 7
         * ranking : 1
         * user_picture : string
         */

        private String student_id;
        private String name;
        private int number;
        private int ranking;
        private String user_picture;

        public RankDataBean(int ranking){
            this.ranking = ranking;
        }


        public String getStudent_id() {
            return student_id;
        }

        public void setStudent_id(String student_id) {
            this.student_id = student_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getRanking() {
            return ranking;
        }

        public void setRanking(int ranking) {
            this.ranking = ranking;
        }

        public String getUser_picture() {
            return user_picture;
        }

        public void setUser_picture(String user_picture) {
            this.user_picture = user_picture;
        }

    }
}
