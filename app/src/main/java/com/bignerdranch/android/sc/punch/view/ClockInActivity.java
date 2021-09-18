package com.bignerdranch.android.sc.punch.view;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.bignerdranch.android.sc.clockpage.weekcalendar.CalendarUtils;
import com.bignerdranch.android.sc.label.LabelPagerActivity;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.punch.LabelPunch;
import com.bignerdranch.android.sc.punch.LabelPunchTitle;
import com.bignerdranch.android.sc.punch.presenter.ClockInPresenter;
import com.bignerdranch.android.sc.rank.newrank.view.RankActivity;
import com.bignerdranch.android.sc.user.model.GetBackdropAPI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClockInActivity extends AppCompatActivity implements ClockInView {
    List<LabelPunch> mClockInLabelList = new ArrayList<>();
    Calendar mCalendar = Calendar.getInstance();
    int yearDay = 0;    //指的是今天
    int viewDay = 0;    //指的是从主页面点进来查看的一天
    RecyclerView mRecyclerView;
    ClockInAdapter mClockInAdapter;
    ClockInPresenter mClockInPresenter;
    String token;
    ImageView back, addLabel;
    ImageButton rank;
    ConstraintLayout mLayout;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clockin_pager);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        viewDay = CalendarUtils.selectedDate.getDayOfYear();

        initView();
        Listener();
    }

    /**
     * 绑定与监听
     */
    public void initView() {
        yearDay = mCalendar.get(Calendar.DAY_OF_YEAR);

        SharedPreferences sharedPreferences = getSharedPreferences("Token", 0);
        token = sharedPreferences.getString("Token", null);

        back = findViewById(R.id.back);
        addLabel = findViewById(R.id.add);

        mClockInPresenter = new ClockInPresenter(this);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mLayout = findViewById(R.id.clockin_pager);
        requestBg();
        getClockInLabel();
        updateRVUI();
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
                Intent intent = new Intent(ClockInActivity.this, LabelPagerActivity.class);
                startActivity(intent);
                finish();
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
        mClockInLabelList = new ArrayList<>();
        for (LabelPunch clockInLabel : clockInLabels) {
            String url = "http://39.99.53.8:2333/api/v1/punch/oneday/" + String.valueOf(clockInLabel.getId()) + "/" + String.valueOf(viewDay);
            CheckLabelStatus(url, clockInLabel);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mClockInLabelList.add(clockInLabel);
        }
        updateRVUI();
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

    /**
     * 网络请求: 删除、打卡、获取标签、检查打卡
     */
    public void removeLabel(LabelPunchTitle clockInLabelTitle) {
        mClockInPresenter.removeLabel(token, clockInLabelTitle);
    }

    public void toClockIn(LabelPunchTitle clockInLabelTitle) {
        mClockInPresenter.toClockIn(token, clockInLabelTitle);
        getClockInLabel();
    }

    public void getClockInLabel() {
        mClockInPresenter.getLabels(token);
    }

    public void CheckLabelStatus(String url, LabelPunch clockInLabel) {
        mClockInPresenter.CheckLabelStatus(token, url, clockInLabel);
    }

    /**
     * 列表相关
     */
    public void updateRVUI() {
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
            clockIn_times.setText("您已打卡：" + String.valueOf(clockInLabel.getNumber()) + "次");
            clockIn_image.setImageResource(clockInLabel.getImgID(clockInLabel.getTitle()));
            if (clockInLabel.getLabelStatus()) {
                clockIn_button.setBackgroundResource(R.drawable.punch_done);
                clockIn_button.setEnabled(false);
                clockIn_button.setText("已打卡");
            } else {
                if(viewDay != yearDay){
                    clockIn_button.setBackgroundResource(R.drawable.punch_done);
                    clockIn_button.setEnabled(false);
                    clockIn_button.setText("未打卡");
                }
            }

        }
    }

    private class ClockInAdapter extends RecyclerView.Adapter<ClockInHolder> {
        List<LabelPunch> mClockInLabels;
        int lastPosition = -1;

        public ClockInAdapter(List<LabelPunch> labels) {
            mClockInLabels = labels;
        }

        @NonNull
        @Override
        public ClockInHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clockin_item, parent, false);
            return new ClockInHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ClockInHolder holder, @SuppressLint("RecyclerView") int position) {
            LabelPunch clockInLabel = mClockInLabels.get(position);

            holder.bind(clockInLabel);

            holder.clockIn_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toClockIn(new LabelPunchTitle(clockInLabel.getTitle()));
                    notifyDataSetChanged();
                }
            });

            holder.mConstraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    holder.clockIn_delete.setVisibility(View.VISIBLE);
                    holder.clockIn_button.setVisibility(View.GONE);
                    lastPosition = position;
                    return true;
                }
            });

            holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastPosition != position || mClockInLabels.size() == 1) {
                        updateRVUI();
                    }
                }
            });

            holder.clockIn_delete.setOnClickListener(new View.OnClickListener() {
                Button ok, no;
                TextView sayingText;

                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.clockin_popupwindow, null, false);
                    final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    //参数为1.View 2.宽度 3.高度
                    popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                    popupWindow.setFocusable(true);

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
                            removeLabel(new LabelPunchTitle(clockInLabel.getTitle()));
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
        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.99.53.8:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder1.build();
        GetBackdropAPI client1 = retrofit1.create(GetBackdropAPI.class);
        Call<User> call1 = client1.getCurrentBackdrop(token);

        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User.DataDTO mUser = new User.DataDTO();
                mUser = response.body().getData();
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
