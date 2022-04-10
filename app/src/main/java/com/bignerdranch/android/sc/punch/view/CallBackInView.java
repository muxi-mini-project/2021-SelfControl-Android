package com.bignerdranch.android.sc.punch.view;

import com.bignerdranch.android.sc.punch.bean.LabelPunch;

import java.util.List;

public interface CallBackInView {
    // 删除标签
    interface RemoveCallBack{ void getRemoveMessage(String Message);}
    // 设置背景
    interface BGCallBack{void getBGData(int data);}
    // 判断该日是否全部打卡
    interface IfAllPunchCallBack{void getIsAllPunch(boolean isFinish);}

    interface LabelListCallBack{void getList(List<LabelPunch> list);}

    interface ClockInCallBack{void getIsClockIn(boolean isFinish);}
}
