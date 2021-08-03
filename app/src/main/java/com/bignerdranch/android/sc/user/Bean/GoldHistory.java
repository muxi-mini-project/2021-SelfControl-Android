package com.bignerdranch.android.sc.user.Bean;

public class GoldHistory {
    private int change_number;
    private String reason;
    private int residual_number;
    private String student_id;
    private String time;

    public void setChange_number(int change_number) {
        this.change_number = change_number;
    }

    public int getChange_number() {
        return change_number;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setResidual_number(int residual_number) {
        this.residual_number = residual_number;
    }

    public int getResidual_number() {
        return residual_number;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

}
