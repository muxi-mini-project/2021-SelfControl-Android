package com.bignerdranch.android.sc.login;

public class User {

    /**
     * gold : 0
     * name : string
     * password : string
     * privacy : 0
     * student_id : string
     * user_picture : string
     */

    private Integer gold;
    private String name;
    private String password;
    private int privacy;
    private String student_id;
    private String user_picture;

    public User(String student_id, String password) {
        this.student_id = student_id;
        this.password = password;
    }
    public User(int privacy) {
        this.privacy = privacy;
    }

    public User(Integer gold){
        this.gold = gold;
    }
    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
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

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    @Override
    public String toString() {
        return "User{" +
                "gold=" + gold +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", privacy=" + privacy +
                ", student_id='" + student_id + '\'' +
                ", user_picture='" + user_picture + '\'' +
                '}';
    }
}