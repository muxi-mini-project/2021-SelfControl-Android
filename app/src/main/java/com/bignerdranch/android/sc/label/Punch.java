package com.bignerdranch.android.sc.label;

public class Punch {
    private String title;
    private int number;

    public Punch(String title,int number){
        this.title = title;
        this.number = number;
    }

    public Punch(String title){
        this.title = title;
    }

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
}
