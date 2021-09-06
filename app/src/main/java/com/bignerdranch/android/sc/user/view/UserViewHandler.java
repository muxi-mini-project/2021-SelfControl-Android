package com.bignerdranch.android.sc.user.view;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.bean.GoldHistory;
import com.bignerdranch.android.sc.user.bean.Rank;
import com.bignerdranch.android.sc.user.bean.Report;
import com.bignerdranch.android.sc.user.bean.Week;

import java.util.List;

public interface UserViewHandler {
    void showTheWeekPicture(List<Week.DataDTO> list);
    void showGoldHistory(List<GoldHistory.DataDTO> goldList);
    void showChangedName();
    void getUser(User.DataDTO u);
    void showRank(Rank.DataDTO rank);
    void showMonthReport(List<Report.DataDTO> report);
}
