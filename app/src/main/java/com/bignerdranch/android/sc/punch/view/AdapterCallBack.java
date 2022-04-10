package com.bignerdranch.android.sc.punch.view;

import com.bignerdranch.android.sc.punch.bean.LabelPunch;

public interface AdapterCallBack{
    interface popupCallBack{ void popupCallBack(LabelPunch labelPunch);}
    interface clockInCallBack{ void clockInCallBack(LabelPunch labelPunch);}
}
