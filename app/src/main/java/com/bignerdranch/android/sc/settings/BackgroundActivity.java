package com.bignerdranch.android.sc.settings;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.UserActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public class BackgroundActivity extends StatusBar {

    private ImageButton mBack;
    private ImageView mTheme1,mTheme2,mTheme3,mTheme4,mTheme5,mTheme6;
    private ImageView mChoose1,mChoose2,mChoose3,mChoose4,mChoose5,mChoose6;
    private int f1 = 0,f2 = 0,f3 = 0,f4 = 0,f5 = 0,f6 = 0;
    private User mUser;
    private int mCoin;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.backgroud_theme);

        init();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        new Thread() {
            @Override
            public void run() {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://124.71.184.107:2333/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                UserActivity.UserClient client = retrofit.create(UserActivity.UserClient.class);
                Call<User> call = client.mUser();

                call.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        mUser = response.body();
                        mCoin = mUser.getGold();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        }.start();
    }

    private void init(){

        mBack = (ImageButton)findViewById(R.id.background_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mTheme1 = (ImageView)findViewById(R.id.theme1);
        mTheme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 1;f2 = 0;f3 = 0;f4 = 0;f5 = 0;f6 = 0;
                updateView();
            }
        });
        mTheme2 = (ImageView)findViewById(R.id.theme2);
        mTheme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 0;f2 = 1;f3 = 0;f4 = 0;f5 = 0;f6 = 0;
                updateView();
            }
        });
        mTheme3 = (ImageView)findViewById(R.id.theme3);
        mTheme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 0;f2 = 0;f3 = 1;f4 = 0;f5 = 0;f6 = 0;
                updateView();
            }
        });
        mTheme4 = (ImageView)findViewById(R.id.theme4);
        mTheme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 0;f2 = 0;f3 = 0;f4 = 1;f5 = 0;f6 = 0;
                updateView();
            }
        });
        mTheme5 = (ImageView)findViewById(R.id.theme5);
        mTheme5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 0;f2 = 0;f3 = 0;f4 = 0;f5 = 1;f6 = 0;
                updateView();
            }
        });
        mTheme6 = (ImageView)findViewById(R.id.theme6);
        mTheme6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 0;f2 = 0;f3 = 0;f4 = 0;f5 = 0;f6 = 1;
                updateView();
            }
        });

        mChoose1 = findViewById(R.id.choose1);
        mChoose2 = findViewById(R.id.choose2);
        mChoose3 = findViewById(R.id.choose3);
        mChoose4 = findViewById(R.id.choose4);
        mChoose5 = findViewById(R.id.choose5);
        mChoose6 = findViewById(R.id.choose6);

    }
    private void updateView(){
        if(f1 == 1){
            mChoose1.setBackgroundResource(R.mipmap.choose);
        }else {
            
        }
        if(f2 == 1){
            mChoose2.setBackgroundResource(R.mipmap.choose);
        }
        if(f3 == 1){
            mChoose3.setBackgroundResource(R.mipmap.choose);
        }
        if(f4 == 1){
            mChoose4.setBackgroundResource(R.mipmap.choose);
        }
        if(f5 == 1){
            mChoose5.setBackgroundResource(R.mipmap.choose);
        }
        if(f6 == 1){
            mChoose6.setBackgroundResource(R.mipmap.choose);
        }

    }
}
