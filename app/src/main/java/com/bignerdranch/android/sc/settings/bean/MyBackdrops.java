package com.bignerdranch.android.sc.settings.bean;

public class MyBackdrops {
    /**
     * code : 200
     * msg : 获取成功
     * data : {"b_1":1,"b_2":1,"b_3":0,"b_4":0,"b_5":0}
     */

    private int code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    private DataBean data;

    public static class DataBean {
        public int getB_1() {
            return b_1;
        }

        public void setB_1(int b_1) {
            this.b_1 = b_1;
        }

        public int getB_2() {
            return b_2;
        }

        public void setB_2(int b_2) {
            this.b_2 = b_2;
        }

        public int getB_3() {
            return b_3;
        }

        public void setB_3(int b_3) {
            this.b_3 = b_3;
        }

        public int getB_4() {
            return b_4;
        }

        public void setB_4(int b_4) {
            this.b_4 = b_4;
        }

        public int getB_5() {
            return b_5;
        }

        public void setB_5(int b_5) {
            this.b_5 = b_5;
        }

        /**
         * b_1 : 1
         * b_2 : 1
         * b_3 : 0
         * b_4 : 0
         * b_5 : 0
         */

        private int b_1;
        private int b_2;
        private int b_3;
        private int b_4;
        private int b_5;
    }
}







