package com.bignerdranch.android.sc.user.MonthReport;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import androidx.recyclerview.widget.DividerItemDecoration;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.GetBackdropAPI;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class MonthReportActivity extends StatusBar {
    private ImageButton mBack;
    private TextView mMonth_number;

    private RecyclerView recyclerView;
    private ReportAdapter adapter;
    private List<Report> reportList = new ArrayList<>();
    private User mUser;
    private ConstraintLayout mLayout;

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

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MonthReportActivity.this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        ReportAdapter adapter = new ReportAdapter(reportList);
//        recyclerView.setAdapter(adapter);


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

    private void UpUI() {
        adapter = new ReportAdapter(reportList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MonthReportActivity.this, RecyclerView.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(MonthReportActivity.this, DividerItemDecoration.HORIZONTAL));
    }

    public interface MonthReportAPI {
        @GET("punch/month")
        Call<List<Report>> getMonthReport(@Header("token") String token);
    }
}