package com.bignerdranch.android.sc.user.MonthReport;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public class MonthReportActivity extends StatusBar {
    private ImageButton mBack;

    private RecyclerView recyclerView;
    private List<Report> reportList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_report);

        mBack = findViewById(R.id.month_report_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initList();

        recyclerView = findViewById(R.id.month_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MonthReportActivity.this);
        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        ReportAdapter adapter = new ReportAdapter(reportList);
        recyclerView.setAdapter(adapter);

//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("http://39.102.42.156:2333/api/v1/")
//                .addConverterFactory(GsonConverterFactory.create());
//
//        Retrofit retrofit = builder.build();
//
//        myPunchAPI client = retrofit.create(myPunchAPI.class);
//        Call<List<Report>> call = client.getMyPunch();
//
//        call.enqueue(new Callback<List<Report>>() {
//
//            @Override
//            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
//                reportList = response.body();
//                initList();
//            }
//
//            @Override
//            public void onFailure(Call<List<Report>> call, Throwable t) {
//
//            }
//        });

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void initList() {
        for (int i=0;i<3;i++){
            Report paobu = new Report("10","跑步");
            reportList.add(paobu);
            Report heshui = new Report("3","喝水");
            reportList.add(heshui);
            Report beidanci = new Report("1","背单词");
            reportList.add(beidanci);
        }
    }

    public interface myPunchAPI {
        @GET("user/history")
        @Headers("")
        Call<List<Report>> getMyPunch();
    }
}
