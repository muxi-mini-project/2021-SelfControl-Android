package com.bignerdranch.android.sc.user.view;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.bean.GoldHistory;
import com.bignerdranch.android.sc.user.bean.Rank;
import com.bignerdranch.android.sc.user.bean.Report;
import com.bignerdranch.android.sc.user.bean.Week;
import com.bignerdranch.android.sc.user.presenter.GoldAdapter;
import com.bignerdranch.android.sc.user.presenter.UserPresenter;

import java.util.List;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class CoinQueryActivity extends StatusBar implements com.bignerdranch.android.sc.user.view.UserViewHandler {

    private ImageButton mBack;
    private TextView mCoin;
    private User.DataDTO mUser;
    private List<GoldHistory.DataDTO> mList;
    private ConstraintLayout mLayout,mLayout1;
    private RecyclerView recyclerView;
    //qyh修改添加的变量
    private UserPresenter userPresenter = new UserPresenter(CoinQueryActivity.this);
    private GoldAdapter adapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_query);
        init();
        userPresenter.SendUser(token);
        userPresenter.SendGoldHistory(token);
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    private void init() {
        mBack = findViewById(R.id.coin_query_back);
        mCoin = findViewById(R.id.dangqianjinbi);
        mLayout = findViewById(R.id.coin_query_layout);
        mLayout1 = findViewById(R.id.coin_query_layout12);
        //request();
        recyclerView = findViewById(R.id.coin_query_recyclerView);

        //userPresenter.GetMessageGoldHistory("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzc5MCIsImV4cCI6MTYyOTE5MzMwOSwiaWF0IjoxNjI4NDczMzA5fQ.9pX34Mio1K2p4_2pB_nXMzPj3ShDf_6LzBk_SD4si3I");
        //userPresenter.GetMessageUser("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzc5MCIsImV4cCI6MTYyOTE5MzMwOSwiaWF0IjoxNjI4NDczMzA5fQ.9pX34Mio1K2p4_2pB_nXMzPj3ShDf_6LzBk_SD4si3I");

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(CoinQueryActivity.this,UserActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }




    @Override
    public void showTheWeekPicture(List<Week.DataDTO> list) {

    }

    @Override
    public void showGoldHistory(List<GoldHistory.DataDTO> goldList) {
        this.mList = goldList;
        adapter = new GoldAdapter(this,mList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        Log.d("Demo2","成功");
    }

    @Override
    public void showChangedName() {

    }

    @Override
    public void getUser(User.DataDTO u, Bitmap bitmap) {
        this.mUser = u;
        Log.d("CoinActivity","here");
        if(mUser!=null){
            mCoin.setText(String.valueOf(mUser.getGold()));
            int background = mUser.getCurrent_backdrop();
            if (background == 1){
                mLayout.setBackgroundResource(R.color.purple);
                mLayout1.setBackgroundResource(R.color.purple);

            } else if(background ==2){
                mLayout.setBackgroundResource(R.color.theme2);
                mLayout1.setBackgroundResource(R.color.theme2);

            }else if(background ==3){
                mLayout.setBackgroundResource(R.color.theme3);
                mLayout1.setBackgroundResource(R.color.theme3);

            }else if(background ==4){
                mLayout.setBackgroundResource(R.mipmap.theme_31);
                mLayout1.setBackgroundResource(R.mipmap.theme_31);

            }else if(background ==5){
                mLayout.setBackgroundResource(R.mipmap.theme_41);
                mLayout1.setBackgroundResource(R.mipmap.theme_41);

            }else if(background ==6){
                mLayout.setBackgroundResource(R.mipmap.theme_51);
                mLayout1.setBackgroundResource(R.mipmap.theme_51);

            }
        }else{
            Toast.makeText(this,"用户信息返回失败",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void showRank(Rank.DataDTO rank) {

    }

    @Override
    public void showMonthReport(List<Report.DataDTO> report) {

    }


}


