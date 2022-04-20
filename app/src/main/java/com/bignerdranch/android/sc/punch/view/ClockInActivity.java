package com.bignerdranch.android.sc.punch.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.clockpage.model.FlowerResponse;
import com.bignerdranch.android.sc.clockpage.view.ClockActivity;
import com.bignerdranch.android.sc.clockpage.weekcalendar.CalendarUtils;
import com.bignerdranch.android.sc.label.LabelPagerActivity;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.punch.bean.LabelPunch;
import com.bignerdranch.android.sc.punch.bean.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.bean.ResponseData;
import com.bignerdranch.android.sc.punch.bean.SingleMessage;
import com.bignerdranch.android.sc.punch.presenter.ClockInPresenter;
import com.bignerdranch.android.sc.rank.newrank.view.RankActivity;
import com.bignerdranch.android.sc.user.bean.Week;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import static com.bignerdranch.android.sc.R.drawable.popup_background;
import static com.bignerdranch.android.sc.clockpage.model.RemoteDataSource.STATUS;
import static com.bignerdranch.android.sc.login.LoginActivity.key;

public class ClockInActivity extends AppCompatActivity{
    private final static String TAG = "aaaClockInActivity";
    List<LabelPunch> mClockInLabelList = new ArrayList<>();
    Calendar mCalendar = Calendar.getInstance();
    int yearDay = 0;    //指的是今天
    int viewDay = 0;    //指的是从主页面点进来查看的一天
    RecyclerView mRecyclerView;
    ClockInAdapter mClockInAdapter;
    ClockInPresenter mClockInPresenter;
    String token;
    ImageView back, addLabel, loading;
    ConstraintLayout mLayout;
    boolean connection;
    boolean isAllFinish;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clockin_pager);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            viewDay = CalendarUtils.selectedDate.getDayOfYear();
        }

        initView();
        Listener();
        Log.d(TAG, "onCreate");
    }

    /**
     * 绑定与监听
     */
    public void initView() {
        yearDay = mCalendar.get(Calendar.DAY_OF_YEAR);

        SharedPreferences sharedPreferences = getSharedPreferences("Token", 0);
        token = sharedPreferences.getString("Token", null);

        mLayout = findViewById(R.id.clockin_pager);

        back = findViewById(R.id.back);
        addLabel = findViewById(R.id.add);
        loading = findViewById(R.id.loading);

        mClockInPresenter = new ClockInPresenter(token);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mClockInAdapter = new ClockInAdapter(viewDay, yearDay);

        connection();
        setBG();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void Listener() {
        back.setOnClickListener( v-> {
            finish();
        });

        addLabel.setOnClickListener( v -> {
            if(!isAllFinish) {
                key = 1;
                Intent intent = new Intent(ClockInActivity.this, LabelPagerActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this,"今日任务已经全部完成，无法增加咯~",Toast.LENGTH_SHORT).show();
            }
        });

        loading.setOnClickListener( v -> {
            loading.setImageResource(R.mipmap.loading_foreground);
            connection();
        });

        mClockInAdapter.setPopupWindowCallBack( label -> {
            Button ok, no;
            TextView sayingText;
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.clockin_popupwindow, null, false);
            final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
            popupWindow.setFocusable(true);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    backgroundAlpha(1.0f);
                }
            });

            ok = view.findViewById(R.id.delete_yes);
            no = view.findViewById(R.id.delete_no);
            sayingText = view.findViewById(R.id.sayings);
            setSayingText(sayingText);

            backgroundAlpha(0.5f);
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backgroundAlpha(1f);
                    popupWindow.dismiss();
                }
            });

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    backgroundAlpha(1f);
                    removeLabel(new LabelPunchTitle(label.getTitle()));
                    mClockInLabelList.remove(label);
                    popupWindow.dismiss();
                    updateRVUI();
                }
            });

            popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        });

        mClockInAdapter.setClockInCallBack(this::toClockIn);
    }

    /**
     * mvp：获取信息后更新列表、获取错误返回错误信息
     *
     * @param clockInLabels
     */

    public void showLabelInfo(List<LabelPunch> clockInLabels) {
        for (LabelPunch labelPunch : clockInLabels) {
            labelPunch.setLabelStatus(3);
        }
        String url = "http://self-control.muxixyz.com/api/v1/punch/day/" + String.valueOf(viewDay);
        NetUtil.getInstance().getApi().getClockDayList(token, url).enqueue(new Callback<ResponseData<List<LabelPunch>>>() {
            @Override
            public void onResponse(Call<ResponseData<List<LabelPunch>>> call, Response<ResponseData<List<LabelPunch>>> response) {
                if (response.body().getData() != null)
                    mClockInLabelList = response.body().getData();
                else
                    mClockInLabelList = new ArrayList<>();
                //判断该天已打卡是否在获取的标签中
                if (mClockInLabelList != null)
                    for (LabelPunch alreadyClockIn : mClockInLabelList) {
                        boolean isContain = false;
                        for (LabelPunch labelPunch : clockInLabels) {
                            if (alreadyClockIn.getId() == labelPunch.getId()) {
                                isContain = true;
                                alreadyClockIn.setLabelStatus(1);
                                break;
                            }
                        }
                        if (!isContain) alreadyClockIn.setLabelStatus(2);
                    }

                //将所有打卡合并道list中
                for (LabelPunch labelPunch : clockInLabels) {
                    boolean isAdded = false;
                    if (mClockInLabelList != null)
                        for (LabelPunch alreadyClockIn : mClockInLabelList) {
                            if (labelPunch.getId() == alreadyClockIn.getId()) {
                                isAdded = true;
                                break;
                            }
                        }
                    if (!isAdded) mClockInLabelList.add(labelPunch);
                }
                updateRVUI();
            }

            @Override
            public void onFailure(Call<ResponseData<List<LabelPunch>>> call, Throwable t) {

            }
        });
        loading.setVisibility(View.GONE);
    }

    /**
     * 网络请求: 删除、打卡、获取标签、检查打卡
     */
    // 删除标签
    public void removeLabel(LabelPunchTitle labelPunchTitle){
        mClockInPresenter.removeLabels(labelPunchTitle, message -> {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            getClockInLabel();
        });
    }

    public void getClockInLabel() {
        mClockInPresenter.getLabels(this::showLabelInfo);
    }

    //每次打卡之后都判断一次是否全部打完卡了，全部打完卡则弹出弹窗
    @SuppressLint("NotifyDataSetChanged")
    public void toClockIn(LabelPunch label){
        mClockInPresenter.toClockIn(new LabelPunchTitle(label.getTitle()), isFinish -> {
            label.setLabelStatus(1);
            int temp = label.getNumber() + 1;
            label.setNumber(temp);
            mClockInAdapter.notifyDataSetChanged();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ifDayAllPunch();
        });
    }

    public void ifDayAllPunch() {
        mClockInPresenter.ifDayAllPunch(viewDay, isFinish -> {
            isAllFinish = isFinish;
            if(isFinish){
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.clockin_all, null, false);
                final PopupWindow popupWindow = new PopupWindow(view, 950, 1550);
                //参数为1.View 2.宽度 3.高度
                popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                popupWindow.setFocusable(true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        backgroundAlpha(1.0f);
                    }
                });
                backgroundAlpha(0.5f);
                popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
            }
        });
    }

    /**
     * 列表相关
     */
    public void updateRVUI() {
        if (mRecyclerView.getChildCount() > 0) {
            mRecyclerView.setAdapter(null);
        }
        mClockInAdapter.setClockInLabels(mClockInLabelList);
        mRecyclerView.setAdapter(mClockInAdapter);
    }

    public void openRank(View view) {
        Intent intent = new Intent(ClockInActivity.this, RankActivity.class);
        startActivity(intent);
    }

    public void setSayingText(TextView sayingText) {
        Random random = new Random();
        switch (random.nextInt(9)) {
            case 1:
                sayingText.setText(R.string.saying1);
                break;
            case 2:
                sayingText.setText(R.string.saying2);
                break;
            case 3:
                sayingText.setText(R.string.saying3);
                break;
            case 4:
                sayingText.setText(R.string.saying4);
                break;
            case 5:
                sayingText.setText(R.string.saying5);
                break;
            case 6:
                sayingText.setText(R.string.saying6);
                break;
            case 7:
                sayingText.setText(R.string.saying7);
                break;
            case 8:
                sayingText.setText(R.string.saying8);
                break;
            case 9:
                sayingText.setText(R.string.saying9);
                break;
            case 0:
                sayingText.setText(R.string.saying0);
                break;
        }
    }

    //设置主题背景
    public void setBG() {
        mClockInPresenter.setBG(data -> {
            switch (data){
                case 1: mLayout.setBackgroundResource(R.mipmap.background_default); break;
                case 2: mLayout.setBackgroundResource(R.mipmap.theme_1); break;
                case 3: mLayout.setBackgroundResource(R.mipmap.theme_2); break;
                case 4: mLayout.setBackgroundResource(R.mipmap.theme_3); break;
                case 5: mLayout.setBackgroundResource(R.mipmap.theme_4); break;
                case 6: mLayout.setBackgroundResource(R.mipmap.theme_5); break;
            }
        });
    }

    //检查网络连接
    public void connection() {
        ConnectivityManager conn = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = conn.getActiveNetworkInfo();
        if (net != null && net.isConnected()) {
            connection = true;
        } else {
            connection = false;
        }

        if (connection)
            getClockInLabel();
        else {
            loading.setImageResource(R.mipmap.connection_failed_foreground);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mRecyclerView.getChildCount() > 0) {
            mRecyclerView.setAdapter(null);
        }
        loading.setVisibility(View.VISIBLE);
        loading.setImageResource(R.mipmap.loading_foreground);
        connection();
    }

    public void backgroundAlpha(float v) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = v; //0.0-1.0
        getWindow().setAttributes(lp);
    }
}
