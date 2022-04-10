package com.bignerdranch.android.sc.punch.presenter;

import com.bignerdranch.android.sc.punch.bean.LabelPunch;

import java.util.List;

public interface CallBackInPresenter {
    // 删除标签
    interface RemoveLabelCallback{ void setRemoveMessage(String message);}

    interface BGCallBack{void setBG(int data);}

    interface IfAllPunchCallBack{void setIsAllPunch(boolean isFinish);}

    interface LabelListCallBack{void setList(List<LabelPunch> list);}

    interface ClockInCallBack{void setIsClockIn(boolean isFinish);}
}
