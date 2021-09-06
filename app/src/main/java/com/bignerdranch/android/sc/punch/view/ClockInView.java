package com.bignerdranch.android.sc.punch.view;

import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.List;

public interface ClockInView {
    void showLabelInfo(List<LabelPunch> list);
    void showError(String message);
    void showRemoveSuccess(String message);
}
