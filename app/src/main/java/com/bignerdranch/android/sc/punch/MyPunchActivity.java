package com.bignerdranch.android.sc.punch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.user.model.GetBackdropAPI;
import com.bignerdranch.android.sc.user.Bean.Message;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.Utils;

import com.bignerdranch.android.sc.user.Bean.Data;
import com.bignerdranch.android.sc.label.LabelPagerActivity;
import com.bignerdranch.android.sc.label.Punch;
import com.bignerdranch.android.sc.label.PunchAPI;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class MyPunchActivity extends StatusBar {
    private ImageButton mrank;
    private ImageButton mback;
    private ImageButton madd;

    private RecyclerView mRecyclerView;
    private List<LabelPunch> mLabelPunchList = new ArrayList<>();
    private List<LabelPunch> mList;
    private LabelPunchAdapter adapter;
    private User mUser;
    private Data mData;

    private ConstraintLayout mLayout;
    private int date;
    private String mtitle;
    private Calendar mCalendar = Calendar.getInstance();

    private int currentMonth = mCalendar.get(Calendar.MONTH);
    private int currentDay = mCalendar.get(Calendar.DAY_OF_MONTH);

    private int today = getEachDayOfYear(false, currentMonth, currentDay);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clockin_pager);

        mLayout = findViewById(R.id.clockin_pager);
        mRecyclerView = findViewById(R.id.recycler_view);
        request();
        getMyPunch();

        Intent intent = getIntent();
        date = intent.getIntExtra("data", 0);
        if (date == today) {
            getMyPunch();
        } else {

        }

        mback = findViewById(R.id.back);
//        mback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        mrank = findViewById(R.id.rank_ImageButton);
        mrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    Intent intent = new Intent(MyPunchActivity.this, RankBackgroundActivity.class);
                    startActivity(intent);
                }

            }
        });

        madd = findViewById(R.id.add);
        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    ifpunchcomplete();
                }

            }
        });

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void getMyPunch() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<List<LabelPunch>> call = client.getPunch(token);

        call.enqueue(new Callback<List<LabelPunch>>() {

            @Override
            public void onResponse(Call<List<LabelPunch>> call, Response<List<LabelPunch>> response) {
                mLabelPunchList = response.body();
                UpUI();
            }

            @Override
            public void onFailure(Call<List<LabelPunch>> call, Throwable t) {

            }

        });
    }

    private void UpUI() {
        adapter = new LabelPunchAdapter(mLabelPunchList);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new LabelPunchAdapter.OnItemClickListener() {

            @Override
            public void onItemLongClick(View view, int pos) {
                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MyPunchActivity.this);
                normalDialog.setTitle("删除打卡");
                normalDialog.setMessage("确定删除该打卡内容吗?");
                normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mtitle = mLabelPunchList.get(pos).getTitle();
                        mLabelPunchList.remove(pos);
                        adapter.notifyItemRemoved(pos);

                        deletePunch(mtitle);
                        adapter.notifyItemRangeChanged(0, mLabelPunchList.size() - 1);

                    }
                });
                normalDialog.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                normalDialog.show();
            }
        });
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

    private void deletePunch(String title) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client2 = retrofit.create(PunchAPI.class);
        Call<Message> call = client2.delete(token, new Punch(title));

        call.enqueue(new Callback<Message>() {

            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    private void ifpunchcomplete() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client3 = retrofit.create(PunchAPI.class);
        Call<Data> call = client3.ifpunchcomplete(token);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                mData = response.body();
                mData.getData();
                if (mData.getData() > 0) {
                    madd.setEnabled(false);
                    Toast.makeText(MyPunchActivity.this, "今日已完成全部打卡，不能再新增标签", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MyPunchActivity.this, LabelPagerActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

    public int getEachDayOfYear(boolean isLeapyear, int month, int day) {
        int eachDayOfYear = 0;
        int[] mon = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (isLeapyear) {
            mon[1] = 29;
        }
        for (int i = 0; i < month; i++) {
            eachDayOfYear += mon[i];
        }
        eachDayOfYear = eachDayOfYear + day;
        return eachDayOfYear;
    }

    private void getDayPunch() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client3 = retrofit.create(PunchAPI.class);
        Call<List<LabelPunch>> call = client3.getDayPunch(token, date);

        call.enqueue(new Callback<List<LabelPunch>>() {
            @Override
            public void onResponse(Call<List<LabelPunch>> call, Response<List<LabelPunch>> response) {
                mList = response.body();
                if (mList != null) {
                    adapter = new LabelPunchAdapter(mList);

                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MyPunchActivity.this, RecyclerView.VERTICAL, false));
                    mRecyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<LabelPunch>> call, Throwable t) {

            }

        });
    }
}
