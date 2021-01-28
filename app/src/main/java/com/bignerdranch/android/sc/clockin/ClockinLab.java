package com.bignerdranch.android.sc.clockin;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ClockinLab {
    private static ClockinLab sClockinLab;

    private List<Clockin> mClockins;

    public static ClockinLab get(Context context){
        if(sClockinLab == null){
            sClockinLab = new ClockinLab(context);
        }
        return sClockinLab;
    }

    private ClockinLab(Context context){
        mClockins = new ArrayList<>();
        for (int i = 0;i < 100;i++){
            Clockin clockin = new Clockin();
        }
    }

    public List<Clockin> getmClockins(){
        return mClockins;
    }

}