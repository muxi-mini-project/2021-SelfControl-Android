package com.bignerdranch.android.sc.user.Presenter;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.Bean.GoldHistory;
import com.bignerdranch.android.sc.user.Bean.Rank;
import com.bignerdranch.android.sc.user.Bean.Report;
import com.bignerdranch.android.sc.user.Bean.Week;

import java.util.List;

public interface UsePresent {
    void transMessageWeek(List<Week.DataDTO> list);
    void transGoldHistory(List<GoldHistory.DataDTO> goldList);
    void transChangedName();
    void transUser(User.DataDTO u);
    void transRank(Rank.DataDTO rank);
    void transMonthReport(List<Report.DataDTO> report);
}
