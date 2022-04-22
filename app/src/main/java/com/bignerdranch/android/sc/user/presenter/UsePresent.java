package com.bignerdranch.android.sc.user.presenter;

import android.graphics.Bitmap;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.bean.GoldHistory;
import com.bignerdranch.android.sc.user.bean.Rank;
import com.bignerdranch.android.sc.user.bean.Report;
import com.bignerdranch.android.sc.user.bean.Week;

import java.util.List;

public interface UsePresent {
    void transMessageWeek(List<Week.DataDTO> list);
    void transGoldHistory(List<GoldHistory.DataDTO> goldList);
    void transChangedName();
    void transUser(User.DataDTO u, Bitmap bitmap);
    void transRank(Rank.DataDTO rank);
    void transMonthReport(List<Report.DataDTO> report);
}
