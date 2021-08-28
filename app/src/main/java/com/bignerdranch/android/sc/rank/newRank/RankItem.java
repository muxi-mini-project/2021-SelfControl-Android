package com.bignerdranch.android.sc.rank.newRank;


public class RankItem {

    /**
     * name : string
     * number : 0
     * ranking : 0
     * student_id : string
     * user_picture : string
     */

    private String name;
    private int number;
    private int ranking;
    private String student_id;
    private String user_picture;

    public RankItem(int ranking) {
        this.ranking = ranking;
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
}
