package com.bignerdranch.android.sc.user.View;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.user.Bean.GoldHistory;
import com.bignerdranch.android.sc.user.Bean.Rank;
import com.bignerdranch.android.sc.user.Bean.Report;
import com.bignerdranch.android.sc.user.Bean.Week;
import com.bignerdranch.android.sc.user.Presenter.UserPresenter;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;

import java.util.List;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class RankQueryActivity extends StatusBar implements UserViewHandler{
    private ImageButton mBack;
    private TextView mName, mMonthFormer, mMonthAfter, mWeekFormer, mWeekAfter;
    private ImageView imageView;
    private User.DataDTO mUser;
    private Rank.DataDTO mRank;
    private ConstraintLayout mLayout;
    private UserPresenter userPresenter = new UserPresenter(RankQueryActivity.this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_query);
        init();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void init() {
        mLayout = findViewById(R.id.rank_query_layout);
        //request();
        imageView = findViewById(R.id.touxiang);

        mBack = findViewById(R.id.rank_query_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mName = findViewById(R.id.rank_query_name);
        //导入本地图片
        SharedPreferences sharedPreferences = this.getSharedPreferences("sharedPreferences",MODE_PRIVATE);
        String string = sharedPreferences.getString("getFilePath",null);
        if(string!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(string);
            imageView.setImageBitmap(bitmap);
        }
        userPresenter.SendRank(token);
        userPresenter.SendUser(token);
        //userPresenter.GetMessageUser("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzc5MCIsImV4cCI6MTYyOTE5MzMwOSwiaWF0IjoxNjI4NDczMzA5fQ.9pX34Mio1K2p4_2pB_nXMzPj3ShDf_6LzBk_SD4si3I");
        //userPresenter.GetMessageRank("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzc5MCIsImV4cCI6MTYyOTE5MzMwOSwiaWF0IjoxNjI4NDczMzA5fQ.9pX34Mio1K2p4_2pB_nXMzPj3ShDf_6LzBk_SD4si3I");



        //代替上面的部分
        //userPresenter.GetMessageRank("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzc5MCIsImV4cCI6MTYyOTE5MzMwOSwiaWF0IjoxNjI4NDczMzA5fQ.9pX34Mio1K2p4_2pB_nXMzPj3ShDf_6LzBk_SD4si3I");

        mMonthFormer = findViewById(R.id.ypm_start);


        mMonthAfter = findViewById(R.id.ypm_hl);

        mWeekFormer = findViewById(R.id.zpm_start);


        mWeekAfter = findViewById(R.id.zpm_hl);

    }


    @Override
    public void showTheWeekPicture(List<Week.DataDTO> list) {

    }

    @Override
    public void showGoldHistory(List<GoldHistory.DataDTO> goldList) {

    }

    @Override
    public void showChangedName() {

    }

    @Override
    public void getUser(User.DataDTO u) {
        this.mUser = u;
        if(mUser!=null){
            mName.setText(mUser.getName());
            int background = mUser.getCurrent_backdrop();
            if (background == 1){
                mLayout.setBackgroundResource(R.color.purple);
            } else if(background ==2){
                mLayout.setBackgroundResource(R.color.theme2);
            }else if(background ==3){
                mLayout.setBackgroundResource(R.color.theme3);
            }else if(background ==4){
                mLayout.setBackgroundResource(R.mipmap.theme_31);
            }else if(background ==5){
                mLayout.setBackgroundResource(R.mipmap.theme_41);
            }else if(background ==6){
                mLayout.setBackgroundResource(R.mipmap.theme_51);
            }
        }
    }


    @Override
    public void showRank(Rank.DataDTO rank) {
        this.mRank = rank;
        if (mRank != null) {
            mMonthFormer.setText(String.valueOf(mRank.getMonth_former()));
            mMonthAfter.setText(String.valueOf(mRank.getMonth_after()));
            mWeekFormer.setText(String.valueOf(mRank.getWeek_former()));
            mWeekAfter.setText(String.valueOf(mRank.getWeek_after()));
        }
    }

    @Override
    public void showMonthReport(List<Report.DataDTO> report) {

    }


}

