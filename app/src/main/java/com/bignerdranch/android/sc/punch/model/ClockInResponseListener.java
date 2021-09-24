package com.bignerdranch.android.sc.punch.model;

import com.bignerdranch.android.sc.punch.bean.LabelPunch;

import java.util.List;

public interface ClockInResponseListener {
    default void clockInRequestLabelSuccess(List<LabelPunch> list) {}

    default void removeLabelSuccess(String message){}

    default void CheckLabelStatus(boolean status){}

    void clockInRequestFail(String message);
}
