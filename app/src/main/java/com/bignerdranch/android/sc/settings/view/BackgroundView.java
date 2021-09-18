package com.bignerdranch.android.sc.settings.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.settings.API.BackgroundAPI;
import com.bignerdranch.android.sc.settings.presenter.BackgroundP;
import com.bignerdranch.android.sc.user.model.GetBackdropAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class BackgroundView extends StatusBar implements BackgroundAPI.VP, View.OnClickListener {

    private ImageButton mBack;
    private ImageView mTheme1, mTheme2, mTheme3, mTheme4, mTheme5, mTheme6;
    private ImageView mChoose1, mChoose2, mChoose3, mChoose4, mChoose5, mChoose6;
    private BackgroundP mP = new BackgroundP();
    private ConstraintLayout mLayout;



    @Override
    protected void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.backgroud_theme);
        mP.bindView(this);

        initView();
        requestBg();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void initView() {
        mLayout = findViewById(R.id.background_layout);

        mChoose1 = findViewById(R.id.choose1);
        mChoose2 = findViewById(R.id.choose2);
        mChoose3 = findViewById(R.id.choose3);
        mChoose4 = findViewById(R.id.choose6);
        mChoose5 = findViewById(R.id.choose5);
        mChoose6 = findViewById(R.id.choose4);

        mBack = findViewById(R.id.background_back);
        mBack.setOnClickListener(this);


        mTheme1 = findViewById(R.id.theme1);
        mTheme2 = findViewById(R.id.theme2);
        mTheme3 = findViewById(R.id.theme3);
        mTheme4 = findViewById(R.id.theme4);
        mTheme5 = findViewById(R.id.theme5);
        mTheme6 = findViewById(R.id.theme6);
        mTheme1.setOnClickListener(this);
        mTheme2.setOnClickListener(this);
        mTheme3.setOnClickListener(this);
        mTheme4.setOnClickListener(this);
        mTheme5.setOnClickListener(this);
        mTheme6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.theme1:
                successChange(1);
                break;
            case R.id.theme2:
                haveRequest(2,token);
                break;
            case R.id.theme3:
                haveRequest(3,token);
                break;
            case R.id.theme4:
                haveRequest(4,token);
                break;
            case R.id.theme5:
                haveRequest(5,token);
                break;
            case R.id.theme6:
                haveRequest(6,token);
                break;
            case R.id.background_back:
                finish();
                break;

        }
    }

    @Override
    public void haveRequest(int click, String token) {
        mP.haveRequest(click,token);
    }

    @Override
    public void successChange(int num) {
        switch (num) {
            case 2: {
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(1f);
                mLayout.setBackgroundResource(R.color.theme2);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(0f);
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(0f);
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(0f);
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(0f);
            };break;
            case 3:{
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(1f);
                mLayout.setBackgroundResource(R.color.theme3);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(0f);
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(0f);
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(0f);
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(0f);
            };break;
            case 4:{
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(1f);
                mLayout.setBackgroundResource(R.mipmap.theme_31);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(0f);
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(0f);
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(0f);
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(0f);
            };break;
            case 5:{
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(1f);
                mLayout.setBackgroundResource(R.mipmap.theme_41);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(0f);
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(0f);
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(0f);
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(0f);
            };break;
            case 6:{
                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(1f);
                mLayout.setBackgroundResource(R.mipmap.theme_51);

                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(0f);
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(0f);
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(0f);
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(0f);
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(0f);
            };break;
            case 1:{
                mChoose1.setBackgroundResource(R.mipmap.choose);
                mChoose1.setAlpha(1f);
                mLayout.setBackgroundResource(R.color.purple);

                mChoose6.setBackgroundResource(R.mipmap.choose);
                mChoose6.setAlpha(0f);
                mChoose3.setBackgroundResource(R.mipmap.choose);
                mChoose3.setAlpha(0f);
                mChoose4.setBackgroundResource(R.mipmap.choose);
                mChoose4.setAlpha(0f);
                mChoose5.setBackgroundResource(R.mipmap.choose);
                mChoose5.setAlpha(0f);
                mChoose2.setBackgroundResource(R.mipmap.choose);
                mChoose2.setAlpha(0f);
            };break;

        }
    }

    //显示对话框
    @Override
    public void buyDialog(int click) {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(BackgroundView.this);
        normalDialog.setTitle("兑换背景");
        normalDialog.setMessage("确定用金币兑换背景吗?");
        normalDialog.setPositiveButton("确定", (dialog, which) -> buyRequest(click,token));
        normalDialog.setNegativeButton("关闭", (dialog, which) -> {

        });
        // 显示
        normalDialog.show();
    }

    @Override
    public void buyRequest(int click,String token) {
        mP.buyRequest(click,token);
    }

    @Override
    public void noCoin() {
        Toast.makeText(this,"金币不足！快去打卡赚金币吧！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error() {
        Toast.makeText(this,"出现未知错误！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

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
                successChange(mUser.getCurrent_backdrop());
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
