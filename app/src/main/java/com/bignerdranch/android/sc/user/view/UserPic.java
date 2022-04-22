package com.bignerdranch.android.sc.user.view;

public class UserPic {

    /**
     * code : 200
     * msg : 修改成功
     * data : {"url":"http://r9rosthyx.hn-bkt.clouddn.com/2020213790-1649781132.jpg"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public static class DataBean  {
        /**
         * url : http://r9rosthyx.hn-bkt.clouddn.com/2020213790-1649781132.jpg
         */

        private String url;

        public String getUrl() {
            return url;
        }
    }
}
