package com.bignerdranch.android.sc.punch.model;

import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.List;

public interface ClockInResponseListener {
    default void clockInRequestLabelSuccess(List<LabelPunch> list) {}

    default void removeLabelSuccess(String message){}

    default void isClockInToday(boolean isClockIn){}

    void clockInRequestFail(String message);
}
