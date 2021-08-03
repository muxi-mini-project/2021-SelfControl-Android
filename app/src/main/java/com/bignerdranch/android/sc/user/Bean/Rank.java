package com.bignerdranch.android.sc.user.Bean;

public class Rank {
    private int month_after;

    private int month_former;

    private String student_id;

    private int week_after;

    private int week_former;

    private Rank(int month_after, int month_former, int week_after, int week_former) {

        this.month_after = month_after;
        this.month_former = month_former;
        this.week_former = week_former;
        this.week_after = week_after;
    }

    public void setMonth_after(int month_after) {
        this.month_after = month_after;
    }

    public int getMonth_after() {
        return this.month_after;
    }

    public void setMonth_former(int month_former) {
        this.month_former = month_former;
    }

    public int getMonth_former() {
        return this.month_former;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_id() {
        return this.student_id;
    }

    public void setWeek_after(int week_after) {
        this.week_after = week_after;
    }

    public int getWeek_after() {
        return this.week_after;
    }

    public void setWeek_former(int week_former) {
        this.week_former = week_former;
    }

    public int getWeek_former() {
        return this.week_former;
    }
}
