package com.bignerdranch.android.sc.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoinQueryActivity extends StatusBar {

    private ImageButton mBack;
    private TextView mCoin;
    private UserActivity.User mUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_query);
        init();

        new Thread() {
            @Override
            public void run() {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://124.71.184.107:2333/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                UserActivity.UserClient client = retrofit.create(UserActivity.UserClient.class);
                Call<UserActivity.User> call = client.mUser();

                call.enqueue(new Callback<UserActivity.User>() {

                    @Override
                    public void onResponse(Call<UserActivity.User> call, Response<UserActivity.User> response) {
                        mUser = response.body();
                    }

                    @Override
                    public void onFailure(Call<UserActivity.User> call, Throwable t) {

                    }
                });
            }
        }.start();

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }
    private void init(){
        mBack = findViewById(R.id.coin_query_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoinQueryActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });

        mCoin = findViewById(R.id.dangqianjinbi);
        mCoin.setText(mUser.getGold());
    }
}
