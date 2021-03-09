package com.bignerdranch.android.sc.login;

public class user {
    /**
     * student_id : string
     * password : string
     */

    private String password;
    private String student_id;

    public user(String student_id, String password) {
        this.student_id = student_id;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
