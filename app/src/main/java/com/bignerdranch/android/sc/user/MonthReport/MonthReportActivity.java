package com.bignerdranch.android.sc.user.MonthReport;


import android.graphics.Color;
import android.graphics.Point;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.GetBackdropAPI;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import com.bignerdranch.android.sc.login.User;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class MonthReportActivity extends StatusBar {
    private ImageButton mBack;
    private TextView mMonth_number;
    private LineChart lineChart;

    private RecyclerView recyclerView;
    private ReportAdapter adapter;
    private List<Report> reportList = new ArrayList<>();
    private List<Week> weekList = new ArrayList<>();

    private ConstraintLayout mLayout;
    private User mUser;

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
        initList();

        lineChart = findViewById(R.id.linechart);
        linechart();

        mLayout = findViewById(R.id.month_report_layout);
        request();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void initList() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        MonthReportAPI client = retrofit.create(MonthReportAPI.class);
        Call<List<Report>> call = client.getMonthReport(token);

        call.enqueue(new Callback<List<Report>>() {

            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                reportList = response.body();
                if(response.body() != null) {
                    UpUI();
                }
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {

            }
        });
    }
  
    public interface MonthReportAPI {
        @GET("punch/month")
        Call<List<Report>> getMonthReport(@Header("token") String token);
    }

    private void UpUI(){
        adapter = new ReportAdapter(reportList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MonthReportActivity.this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(MonthReportActivity.this,DividerItemDecoration.HORIZONTAL));
    }

    private void linechart(){
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
        lineChart.setExtraOffsets(15f,0f,25f,25f);

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
        GetWeekData();

        List<Entry> list = new ArrayList<>();
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
    }

    private void GetWeekData(){
        Calendar calendar = Calendar.getInstance();
        List<Entry> list = new ArrayList<>();


        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build());

        Retrofit retrofit = builder.build();

        GetWeekAPI client = retrofit.create(GetWeekAPI.class);
        Call<List<Week>> call = client.getWeekNumber(token,calendar.get(Calendar.MONTH));

        call.enqueue(new Callback<List<Week>>() {
                         @Override
                         public void onResponse(Call<List<Week>> call, Response<List<Week>> response) {
                             weekList = response.body();

                         }

                         @Override
                         public void onFailure(Call<List<Week>> call, Throwable t) {

                         }
                     });


//                Log.d("tag", "Response: " + response);
//
//                try {
//                    JSONObject jsonObject = new JSONObject(String.valueOf(response));
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        int value = jsonObject.getInt("week");
//                        int time = jsonObject.getInt("number");
//                        list.add(new Entry(value, i));
//                    }
//
//                    //将数据赋给数据集,一个数据集表示一条线
//                    LineDataSet lineDataSet = new LineDataSet(list,"每周打卡总次数");
//                    LineData lineData = new LineData(lineDataSet);
//                    //线条颜色
//                    lineDataSet.setColor(Color.parseColor("#FDD682"));
//                    //线宽度
//                    lineDataSet.setLineWidth(3.0f);
//                    //显示圆点
//                    lineDataSet.setDrawCircles(true);
//                    lineDataSet.setDrawCircleHole(true);
//                    lineDataSet.setCircleHoleRadius(3);
//                    //设置圆点颜色(外圈)
//                    lineDataSet.setCircleColor(Color.parseColor("#FDD682"));
//                    //不显示曲线点的具体数值
//                    lineData.setDrawValues(false);
//                    //lineData.setValueTextSize(15f);
//
//                    lineChart.setData(lineData);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


    }
    private void request() {


        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder1.build();
        GetBackdropAPI client1 = retrofit1.create(GetBackdropAPI.class);
        Call<User> call1 = client1.getCurrentBackdrop(token);

        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mUser = response.body();
                if (mUser != null) {
                    if (mUser.getCurrent_backdrop() == 6) {
                        mLayout.setBackgroundResource(R.color.purple);
                    }
                    if (mUser.getCurrent_backdrop() == 1) {
                        mLayout.setBackgroundResource(R.color.theme2);
                    }
                    if (mUser.getCurrent_backdrop() == 2) {
                        mLayout.setBackgroundResource(R.color.theme3);
                    }
                    if (mUser.getCurrent_backdrop() == 3) {
                        mLayout.setBackgroundResource(R.mipmap.theme_31);
                    }
                    if (mUser.getCurrent_backdrop() == 4) {
                        mLayout.setBackgroundResource(R.mipmap.theme_41);
                    }
                    if (mUser.getCurrent_backdrop() == 5) {
                        mLayout.setBackgroundResource(R.mipmap.theme_51);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}