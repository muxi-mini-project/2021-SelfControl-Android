package com.bignerdranch.android.sc.user.MonthReport;

public class Report {
    private String mPunch_Title;
    private String mPunch_Time;

    public Report(String punch_Time,String punch_Title){
        this.mPunch_Time = punch_Time;
        this.mPunch_Title = punch_Title;
    }

    public String getPunch_Title() {
        return mPunch_Title;
    }

    public void setPunch_Title(String punch_Title) {
        mPunch_Title = punch_Title;
    }

    public String getPunch_Time() {
        return mPunch_Time;
    }

    public void setPunch_Time(String punch_Time) {
        mPunch_Time = punch_Time;
    }
}
