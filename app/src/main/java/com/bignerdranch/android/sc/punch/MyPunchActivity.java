package com.bignerdranch.android.sc.punch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.GetBackdropAPI;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.label.LabelPagerActivity;
import com.bignerdranch.android.sc.label.Punch;
import com.bignerdranch.android.sc.label.PunchAPI;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class MyPunchActivity extends StatusBar {
    private ImageButton mrank;
    private ImageButton mBack;
    private ImageButton madd;

    private RecyclerView mRecyclerView;
    private List<LabelPunch> mLabelPunchList = new ArrayList<>();
    private List<Punch> mPunches;
    private User mUser;

    private ConstraintLayout mLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clockin_pager);

        mLayout = findViewById(R.id.clockin_pager);
        request();


        mrank = findViewById(R.id.rank_ImageButton);
        mrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPunchActivity.this, RankBackgroundActivity.class);
                startActivity(intent);
            }
        });
        mBack = findViewById(R.id.back);
        madd = findViewById(R.id.add);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    Intent intent = new Intent(MyPunchActivity.this, RankBackgroundActivity.class);
                    startActivity(intent);
                }

            }
        });

        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    Intent intent = new Intent(MyPunchActivity.this, LabelPagerActivity.class);
                    startActivity(intent);
                }

            }
        });

        initList();

        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyPunchActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        LabelPunchAdapter adapter = new LabelPunchAdapter(mLabelPunchList);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new LabelPunchAdapter.OnItemClickListener() {

            @Override
            public void onItemLongClick(final View view, final int pos) {
//                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(MyPunchActivity.this);
//                normalDialog.setTitle("删除打卡");
//                normalDialog.setMessage("确定删除该打卡内容吗?");
//                normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        mLabelPunchList.remove(pos);
//                        adapter.notifyItemRemoved(pos);
//                    }
//                });
//                normalDialog.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                normalDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                    @Override
//                    public void onShow(DialogInterface dialog) {                    //
//                        Button positiveButton = ((AlertDialog) dialog)
//                                .getButton(AlertDialog.BUTTON_POSITIVE);
//                        positiveButton.setBackgroundColor(getResources().getColor(R.color.purple));
//                        positiveButton.setTextColor(Color.WHITE);
//                    }
//                });
//                // 显示
//                normalDialog.show();


                AlertDialog dialog = new AlertDialog.Builder(MyPunchActivity.this)
                        .setTitle("删除打卡")
                        .setMessage("确定删除该打卡内容吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mLabelPunchList.remove(pos);
                                adapter.notifyItemRemoved(pos);
                            }
                        })
                        .setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
//                        Button positiveButton = ((AlertDialog) dialog)
//                                .getButton(AlertDialog.BUTTON_POSITIVE);
//                        positiveButton.setBackgroundColor(getResources().getColor(R.color.purple));
//                        positiveButton.setTextColor(Color.WHITE);
//
                    }
                });
                dialog.show();
            }
        });

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

        private void initList(){
        for (int i = 0;i<5;i++){
            LabelPunch paobu = new LabelPunch(R.mipmap.paobu,"跑步","已打卡3次");
            mLabelPunchList.add(paobu);
            LabelPunch beidanci = new LabelPunch(R.mipmap.beidanci,"背单词","已打卡1次");
            mLabelPunchList.add(beidanci);
            LabelPunch yangwoqizuo = new LabelPunch(R.mipmap.yangwuoqizuo,"仰卧起坐","已打卡2次");
            mLabelPunchList.add(yangwoqizuo);
        }

    }
    public void getMyPunch() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<List<LabelPunch>> call = client.getMyPunch(token);

        call.enqueue(new Callback<List<LabelPunch>>() {

            @Override
            public void onResponse(Call<List<LabelPunch>> call, Response<List<LabelPunch>> response) {

            }

            @Override
            public void onFailure(Call<List<LabelPunch>> call, Throwable t) {

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
