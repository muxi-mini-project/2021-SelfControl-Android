package com.bignerdranch.android.sc.user.View;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.Bean.GoldHistory;
import com.bignerdranch.android.sc.user.Bean.Rank;
import com.bignerdranch.android.sc.user.Bean.Report;
import com.bignerdranch.android.sc.user.Bean.Week;

import java.util.List;

public interface UserViewHandler {
    void showTheWeekPicture(List<Week.DataDTO> list);
    void showGoldHistory(List<GoldHistory.DataDTO> goldList);
    void showChangedName();
    void getUser(User.DataDTO u);
    void showRank(Rank.DataDTO rank);
    void showMonthReport(List<Report.DataDTO> report);
}
