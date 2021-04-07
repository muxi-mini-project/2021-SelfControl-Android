package com.bignerdranch.android.sc.user.MonthReport;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.GetBackdropAPI;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class MonthReportActivity extends StatusBar {
    private ImageButton mBack;

    private RecyclerView recyclerView;
    private List<Report> reportList = new ArrayList<>();
    private User mUser;
    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_report);

        mLayout = findViewById(R.id.month_report_layout);
        request();

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
                    if (mUser.getCurrent_backdrop() == 1) {
                        mLayout.setBackgroundResource(R.color.purple);
                    }
                    if (mUser.getCurrent_backdrop() == 2) {
                        mLayout.setBackgroundResource(R.color.theme2);
                    }
                    if (mUser.getCurrent_backdrop() == 3) {
                        mLayout.setBackgroundResource(R.color.theme3);
                    }
                    if (mUser.getCurrent_backdrop() == 4) {
                        mLayout.setBackgroundResource(R.mipmap.theme_31);
                    }
                    if (mUser.getCurrent_backdrop() == 5) {
                        mLayout.setBackgroundResource(R.mipmap.theme_41);
                    }
                    if (mUser.getCurrent_backdrop() == 6) {
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
