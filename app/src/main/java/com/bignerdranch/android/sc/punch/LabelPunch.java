package com.bignerdranch.android.sc.punch;

import com.bignerdranch.android.sc.StatusBar;

public class LabelPunch extends StatusBar {
    private int mImgID;
    private String mLabe_Title;
    private String mLabel_Time;

    public LabelPunch(int imgID,String label_V,String label_Time){
        this.mImgID = imgID;
        this.mLabel_Time = label_Time;
        this.mLabe_Title = label_V;
    }

    public int getImgID() {
        return mImgID;
    }

    public void setImgID(int imgID) {
        mImgID = imgID;
    }

    public String getLabel_Title() {
        return mLabe_Title;
    }

    public void setLabel_Title(String label_V) {
        mLabe_Title = label_V;
    }

    public String getLabel_Time() {
        return mLabel_Time;
    }

    public void setLabel_Time(String label_Time) {
        mLabel_Time = label_Time;
    }
}
