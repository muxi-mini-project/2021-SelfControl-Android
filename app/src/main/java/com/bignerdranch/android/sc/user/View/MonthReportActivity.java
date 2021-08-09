package com.bignerdranch.android.sc.user.View;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.user.Bean.GoldHistory;
import com.bignerdranch.android.sc.user.Bean.Rank;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.Bean.Report;
import com.bignerdranch.android.sc.user.Bean.Week;
import com.bignerdranch.android.sc.user.Presenter.ReportAdapter;
import com.bignerdranch.android.sc.user.Presenter.UserPresenter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class MonthReportActivity extends StatusBar implements UserViewHandler{
    private ImageButton mBack;
    private TextView mMonth_number;
    private LineChart lineChart;
    private RecyclerView recyclerView;
    private ReportAdapter adapter;
    private List<Report.DataDTO> reportList = new ArrayList<>();
    private List<Week.DataDTO> weekList = new ArrayList<>();
    private ConstraintLayout mLayout;
    private User.DataDTO mUser;

    /*此后为qyh进行修改而添加的变量*/
    private UserPresenter userPresenter = new UserPresenter(MonthReportActivity.this);
    List<Entry> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_report);

        Calendar calendar = Calendar.getInstance();

        mBack = findViewById(R.id.month_report_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mMonth_number = findViewById(R.id.month_number);
        mMonth_number.setText(String.valueOf(calendar.get(Calendar.MONTH)) + "月");

        recyclerView = findViewById(R.id.month_recycler_view);
        //initList();
        userPresenter.GetMonthReport("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzc5MCIsImV4cCI6MTYyOTE5MzMwOSwiaWF0IjoxNjI4NDczMzA5fQ.9pX34Mio1K2p4_2pB_nXMzPj3ShDf_6LzBk_SD4si3I");

        lineChart = findViewById(R.id.linechart);
        linechart();

        mLayout = findViewById(R.id.month_report_layout);
        userPresenter.GetMessageUser("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzc5MCIsImV4cCI6MTYyOTE5MzMwOSwiaWF0IjoxNjI4NDczMzA5fQ.9pX34Mio1K2p4_2pB_nXMzPj3ShDf_6LzBk_SD4si3I");//获取背景颜色
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }



    private void UpUI(){
        adapter = new ReportAdapter(reportList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MonthReportActivity.this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    private void linechart() {
        //无数据时显示
        lineChart.setNoDataText("没有可获取的数据哦~");
        //lineChart.setNoDataTextColor(Color.parseColor("#00BCEF"));
        //取消缩放
        lineChart.setScaleEnabled(false);
        //不显示description
        lineChart.getDescription().setEnabled(false);
        //不显示图例
        lineChart.getLegend().setEnabled(false);
        lineChart.setHighlightPerDragEnabled(true);
        lineChart.setExtraOffsets(15f, 0f, 25f, 25f);

        //获取X轴
        XAxis xAxis = lineChart.getXAxis();
        //xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"0","1","2","3","4","5"}));
        xAxis.setDrawGridLines(false);
        //xAxis.setDrawLabels(true);
        xAxis.setGranularity(1);
        xAxis.setAxisLineWidth(1.5f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(16f);
        xAxis.setAxisMaximum(5);
        xAxis.setAxisMinimum(0);   //X轴最小数值

        //获取Y轴的左右坐标
        YAxis leftYAxis = lineChart.getAxisLeft();
        leftYAxis.setDrawGridLines(false);
        leftYAxis.setAxisLineWidth(1.5f);
        leftYAxis.setAxisMinimum(0);
        leftYAxis.setTextSize(16f);
        leftYAxis.setGranularity(1);
        //leftYAxis.setDrawGridLines(true);//是否显示轴线
        //leftYAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"0","1","2","3","4","5"}));
        //leftYAxis.setEnabled(false);
        YAxis rightYAxis = lineChart.getAxisRight();
        rightYAxis.setEnabled(false);

        //初始化显示数据
        //GetWeekData();qyh开始修改
        userPresenter = new UserPresenter(this);
        Calendar calendar = Calendar.getInstance();
        //userPresenter.GetMessageWeek("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzc5MCIsImV4cCI6MTYyOTE5MzMwOSwiaWF0IjoxNjI4NDczMzA5fQ.9pX34Mio1K2p4_2pB_nXMzPj3ShDf_6LzBk_SD4si3I",calendar.get(Calendar.MONTH));
        userPresenter.GetMessageWeek(token, calendar.get(Calendar.MONTH));
        //qyh这一块修改结束


        /*
        list.add(new Entry(1,8));
        list.add(new Entry(2,7));
        list.add(new Entry(3,8));
        list.add(new Entry(4,0));
        list.add(new Entry(5,6));

        //将数据赋给数据集,一个数据集表示一条线
        LineDataSet lineDataSet = new LineDataSet(list,"每周打卡总次数");
        LineData lineData = new LineData(lineDataSet);
        //线条颜色
        lineDataSet.setColor(Color.parseColor("#FDD682"));
        //线宽度
        lineDataSet.setLineWidth(3.0f);
        //显示圆点
        lineDataSet.setDrawCircles(true);
        lineDataSet.setDrawCircleHole(true);
        lineDataSet.setCircleHoleRadius(3);
        //设置圆点颜色(外圈)
        lineDataSet.setCircleColor(Color.parseColor("#FDD682"));
        //不显示曲线点的具体数值
        lineData.setDrawValues(false);
        //lineData.setValueTextSize(15f);

        lineChart.setData(lineData);

        lineChart.animateY(3000);
    }*/
    }



    @Override
    public void showTheWeekPicture(List<Week.DataDTO> list) {
        weekList = list;
        if(this.weekList!=null){
            for(Week.DataDTO week : weekList){
                this.list.add(new Entry(week.getWeek(),week.getNumber()));
            }
            //将数据赋给数据集,一个数据集表示一条线
            LineDataSet lineDataSet = new LineDataSet(this.list,"每周打卡总次数");
            LineData lineData = new LineData(lineDataSet);
            //线条颜色
            lineDataSet.setColor(Color.parseColor("#FDD682"));
            //线宽度
            lineDataSet.setLineWidth(3.0f);
            //显示圆点
            lineDataSet.setDrawCircles(true);
            lineDataSet.setDrawCircleHole(true);
            lineDataSet.setCircleHoleRadius(3);
            //设置圆点颜色(外圈)
            lineDataSet.setCircleColor(Color.parseColor("#FDD682"));
            //不显示曲线点的具体数值
            lineData.setDrawValues(false);
            //lineData.setValueTextSize(15f);

            lineChart.setData(lineData);

            lineChart.animateY(3000);
        } else{
            Toast.makeText(this,"上个月没有打卡信息呢！要坚持呀！",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void showGoldHistory(List<GoldHistory.DataDTO> goldList) {

    }

    @Override
    public void showChangedName() {

    }

    @Override
    public void getUser(User.DataDTO u) {
        this.mUser = u;
        if(mUser!=null){
            int background = mUser.getCurrent_backdrop();
            if (background == 1){
                mLayout.setBackgroundResource(R.color.purple);
            } else if(background ==2){
                mLayout.setBackgroundResource(R.color.theme2);
            }else if(background ==3){
                mLayout.setBackgroundResource(R.color.theme3);
            }else if(background ==4){
                mLayout.setBackgroundResource(R.mipmap.theme_31);
            }else if(background ==5){
                mLayout.setBackgroundResource(R.mipmap.theme_41);
            }else if(background ==6){
                mLayout.setBackgroundResource(R.mipmap.theme_51);
            }
        }

    }

    @Override
    public void showRank(Rank.DataDTO rank) {
    }

    @Override
    public void showMonthReport(List<Report.DataDTO> report) {
        this.reportList = report;
        if(reportList!=null){
            UpUI();;
        }
    }

}