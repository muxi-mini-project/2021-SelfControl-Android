package com.bignerdranch.android.sc.punch.view;

import com.bignerdranch.android.sc.punch.bean.LabelPunch;

import java.util.List;

public interface ClockInView {
    void showLabelInfo(List<LabelPunch> list);
    void showError(String message);
    void showRemoveSuccess(String message);
    void ifDayAllPunch(int number);
}
