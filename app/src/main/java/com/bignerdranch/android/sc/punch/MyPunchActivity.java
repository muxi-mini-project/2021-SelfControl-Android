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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.label.LabelPagerActivity;
import com.bignerdranch.android.sc.login.LoginActivity;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MyPunchActivity extends StatusBar {
    private ImageButton mrank;
    private ImageButton mBack;
    private ImageButton madd;

    private RecyclerView mRecyclerView;
    private List<LabelPunch> mLabelPunchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clockin_pager);

        mrank = findViewById(R.id.rank_ImageButton);
        mrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPunchActivity.this,RankBackgroundActivity.class);
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
                Intent intent = new Intent(MyPunchActivity.this, RankBackgroundActivity.class);
                startActivity(intent);
            }
        });

        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPunchActivity.this, LabelPagerActivity.class);
                startActivity(intent);
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


                AlertDialog dialog = new AlertDialog.Builder(MyPunchActivity.this, AlertDialog.THEME_HOLO_LIGHT)
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

                            }}).create();

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

}
