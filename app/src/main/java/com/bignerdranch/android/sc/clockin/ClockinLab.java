package com.bignerdranch.android.sc.clockin;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClockinLab {
    private static ClockinLab sClockinLab;

    private List<Clockin> mClockins;

    int mTimes;

    public static ClockinLab get(Context context){
        if(sClockinLab == null){
            sClockinLab = new ClockinLab(context);
        }
        return sClockinLab;
    }

    private ClockinLab(Context context){
        mClockins = new ArrayList<>();
    }

    public List<Clockin> getClockins(){
        return mClockins;
    }

    public Clockin getClockin(UUID id){
        for(Clockin clockin : mClockins){
            if (clockin.getId().equals(id)){
                return clockin;
            }
        }

        return null;
    }

}