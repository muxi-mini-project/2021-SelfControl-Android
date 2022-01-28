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

public class ClockInActivity extends AppCompatActivity implements ClockInView {
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
    ImageButton rank;
    ConstraintLayout mLayout;
    boolean connection;

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
        requestBg();

        back = findViewById(R.id.back);
        addLabel = findViewById(R.id.add);
        loading = findViewById(R.id.loading);

        mClockInPresenter = new ClockInPresenter(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        connection();
    }

    public void Listener() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetUtil.getInstance().getApi().ifDayAllPunch(token,yearDay).enqueue(new Callback<FlowerResponse>() {
                    @Override
                    public void onResponse(Call<FlowerResponse> call, Response<FlowerResponse> response) {
                        if(response.body().getData() == mClockInLabelList.size()) STATUS = true;
                        else STATUS = false;
                    }

                    @Override
                    public void onFailure(Call<FlowerResponse> call, Throwable t) {

                    }
                });

                if (!STATUS) {
                    Toast.makeText(ClockInActivity.this, "今日已完成全部打卡，不能再新增标签", Toast.LENGTH_SHORT).show();
                } else {
                    key = 1;
                    Intent intent = new Intent(ClockInActivity.this, LabelPagerActivity.class);
                    startActivity(intent);
                }
            }
        });

        loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setImageResource(R.mipmap.loading_foreground);
                connection();
            }
        });
    }

    /**
     * mvp：获取信息后更新列表、获取错误返回错误信息
     *
     * @param clockInLabels
     */

    @Override
    public void showLabelInfo(List<LabelPunch> clockInLabels) {
        for (LabelPunch labelPunch : clockInLabels) {
            labelPunch.setLabelStatus(3);
        }
        String url = "http://39.99.53.8:2333/api/v1/punch/day/" + String.valueOf(viewDay);
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

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRemoveSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        getClockInLabel();
    }

    @Override
    public void ifDayAllPunchTodo() {
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

    @Override
    public void checkStatusSuccess(LabelPunch labelPunch, boolean status) {
    }

    /**
     * 网络请求: 删除、打卡、获取标签、检查打卡
     */
    public void removeLabel(LabelPunchTitle clockInLabelTitle) {
        mClockInPresenter.removeLabel(token, clockInLabelTitle);
    }

    public void toClockIn(LabelPunchTitle clockInLabelTitle) {
        mClockInPresenter.toClockIn(token, clockInLabelTitle);
    }

    public void getClockInLabel() {
        mClockInPresenter.getLabels(token);
    }

    /*
    public void CheckLabelStatus(String url, LabelPunch clockInLabel) {
        NetUtil.getInstance().getApi().isClockInToday(token, url).enqueue(new Callback<ResponseData<Boolean>>() {
            @Override
            public void onResponse(Call<ResponseData<Boolean>> call, Response<ResponseData<Boolean>> response) {
                    mLabelPunchDao.InsertLabelPunch(new LabelPunch(clockInLabel.id
                            , clockInLabel.number
                            , clockInLabel.getTitle()
                            , response.body().getData()
                            , clockInLabel.getImgID(clockInLabel.getTitle())));
            }

            @Override
            public void onFailure(Call<ResponseData<Boolean>> call, Throwable t) {
            }
        });

        mClockInPresenter.CheckLabelStatus(token, url, clockInLabel);

    }

     */

    public void ifDayAllPunch() {
        mClockInPresenter.ifDayAllPunch(token, viewDay);
    }

    /**
     * 列表相关
     */
    public void updateRVUI() {
        if (mRecyclerView.getChildCount() > 0) {
            mRecyclerView.setAdapter(null);
        }
        mClockInAdapter = new ClockInAdapter(mClockInLabelList);
        mRecyclerView.setAdapter(mClockInAdapter);
    }

    public void openRank(View view) {
        Intent intent = new Intent(ClockInActivity.this, RankActivity.class);
        startActivity(intent);
    }

    private class ClockInHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;
        TextView clockIn_title, clockIn_times;
        Button clockIn_button, clockIn_delete;
        ImageView clockIn_image;

        public ClockInHolder(@NonNull View itemView) {
            super(itemView);
            clockIn_title = itemView.findViewById(R.id.title);
            clockIn_times = itemView.findViewById(R.id.time);
            clockIn_button = itemView.findViewById(R.id.punch);
            clockIn_image = itemView.findViewById(R.id.Label);
            clockIn_delete = itemView.findViewById(R.id.delete);
            mConstraintLayout = itemView.findViewById(R.id.linearLayout15);
        }

        public void bind(LabelPunch clockInLabel) {
            clockIn_title.setText(clockInLabel.getTitle());
            clockIn_times.setText("累计打卡：" + String.valueOf(clockInLabel.getNumber()) + "次");
            clockIn_image.setImageResource(clockInLabel.getImgID(clockInLabel.getTitle()));

            switch (clockInLabel.getLabelStatus()) {
                case 2:
                    clockIn_title.setText(clockInLabel.getTitle() + "(已删除)");
                case 1:
                    clockIn_button.setBackgroundResource(R.drawable.punch_done);
                    clockIn_button.setTextColor(Color.parseColor("#FDD682"));
                    clockIn_button.setEnabled(false);
                    clockIn_button.setText("已打卡");
                    break;
                case 3:
                    if (viewDay < yearDay) {
                        clockIn_button.setBackgroundResource(R.drawable.punch_missed);
                        clockIn_button.setEnabled(false);
                        clockIn_button.setText("未打卡");
                    } else if (viewDay > yearDay) {
                        clockIn_button.setBackgroundResource(R.drawable.punch_missed);
                        clockIn_button.setEnabled(false);
                        clockIn_button.setText("未到打卡日");
                    }
                    break;
                default:
                    break;
            }
            /*
            if (clockInLabel.getLabelStatus() == 1) {
                clockIn_button.setBackgroundResource(R.drawable.punch_done);
                clockIn_button.setTextColor(Color.parseColor("#FDD682"));
                clockIn_button.setEnabled(false);
                clockIn_button.setText("已打卡");
            } else if(clockInLabel.getLabelStatus() == 0){
                if (viewDay < yearDay) {
                    clockIn_button.setBackgroundResource(R.drawable.punch_missed);
                    clockIn_button.setEnabled(false);
                    clockIn_button.setText("未打卡");
                } else if (viewDay > yearDay) {
                    clockIn_button.setBackgroundResource(R.drawable.punch_missed);
                    clockIn_button.setEnabled(false);
                    clockIn_button.setText("未到打卡日");
                }
            } else if(clockInLabel.getLabelStatus() == 2)
             */
        }
    }

    private class ClockInAdapter extends RecyclerView.Adapter<ClockInHolder> {
        List<LabelPunch> mClockInLabels;

        public ClockInAdapter(List<LabelPunch> labels) {
            mClockInLabels = labels;
        }

        @NonNull
        @Override
        public ClockInHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clockin_item, parent, false);
            ClockInHolder clockInHolder = new ClockInHolder(view);
            clockInHolder.setIsRecyclable(false);
            return clockInHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ClockInHolder holder, @SuppressLint("RecyclerView") int position) {
            LabelPunch clockInLabel = mClockInLabels.get(position);

            holder.bind(clockInLabel);

            holder.clockIn_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toClockIn(new LabelPunchTitle(clockInLabel.getTitle()));
                    clockInLabel.setLabelStatus(1);
                    int temp = clockInLabel.getNumber() + 1;
                    clockInLabel.setNumber(temp);
                    notifyDataSetChanged();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ifDayAllPunch();
                }
            });

            holder.mConstraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    holder.clockIn_delete.setVisibility(View.VISIBLE);
                    holder.clockIn_button.setVisibility(View.GONE);
                    return true;
                }
            });

            holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.clockIn_delete.setVisibility(View.GONE);
                    holder.clockIn_button.setVisibility(View.VISIBLE);
                }
            });

            holder.clockIn_delete.setOnClickListener(new View.OnClickListener() {
                Button ok, no;
                TextView sayingText;

                @Override
                public void onClick(View v) {
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
                            RemoveLabel(new LabelPunchTitle(clockInLabel.getTitle()));
                            mClockInLabelList.remove(clockInLabel);
                            notifyDataSetChanged();
                            popupWindow.dismiss();
                            updateRVUI();
                        }
                    });

                    popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mClockInLabels.size();
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    public void backgroundAlpha(float v) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = v; //0.0-1.0
        getWindow().setAttributes(lp);
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

    public void requestBg() {
        NetUtil.getInstance().getApi().userInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        if (user != null) {
                            if (user.getData().getCurrent_backdrop() == 1) {
                                mLayout.setBackgroundResource(R.mipmap.background_default);
                            }
                            if (user.getData().getCurrent_backdrop() == 2) {
                                mLayout.setBackgroundResource(R.mipmap.theme_1);
                            }
                            if (user.getData().getCurrent_backdrop() == 3) {
                                mLayout.setBackgroundResource(R.mipmap.theme_2);
                            }
                            if (user.getData().getCurrent_backdrop() == 4) {
                                mLayout.setBackgroundResource(R.mipmap.theme_3);
                            }
                            if (user.getData().getCurrent_backdrop() == 5) {
                                mLayout.setBackgroundResource(R.mipmap.theme_4);
                            }
                            if (user.getData().getCurrent_backdrop() == 6) {
                                mLayout.setBackgroundResource(R.mipmap.theme_5);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

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

    public void RemoveLabel(LabelPunchTitle labelPunchTitle){
        NetUtil.getInstance().getApi().removeLabel(token, labelPunchTitle).enqueue(new Callback<SingleMessage>() {
            @Override
            public void onResponse(Call<SingleMessage> call, Response<SingleMessage> response) {

            }

            @Override
            public void onFailure(Call<SingleMessage> call, Throwable t) {

            }
        });
    }
}
