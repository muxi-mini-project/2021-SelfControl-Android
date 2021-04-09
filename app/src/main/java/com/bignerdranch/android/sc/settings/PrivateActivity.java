package com.bignerdranch.android.sc.settings;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.GetBackdropAPI;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

import retrofit2.http.Header;

import retrofit2.http.PUT;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class PrivateActivity extends StatusBar {

    private ImageButton mBack;
    private Button mTrue, mFalse;
    private User mUser;
    private ConstraintLayout mLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_open);
        init();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void init() {

        mLayout = findViewById(R.id.choose_layout);
        request();

        mBack = (ImageButton) findViewById(R.id.private_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTrue = (Button) findViewById(R.id.ture_button);
        mTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://39.102.42.156:2333/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                PrivateAPI client = retrofit.create(PrivateAPI.class);
                Call<User> call = client.getCall(new User(1), token);

                call.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(PrivateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(PrivateActivity.this, "出错啦！请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        mFalse = (Button) findViewById(R.id.false_button);
        mFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://39.102.42.156:2333/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                PrivateAPI client = retrofit.create(PrivateAPI.class);
                Call<User> call = client.getCall(new User(0), token);

                call.enqueue(new Callback<User>() {

                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Toast.makeText(PrivateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(PrivateActivity.this, "出错啦！请稍后再试", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });
    }

    public interface PrivateAPI {

        @PUT("user/")
        Call<User> getCall(@Body User mUser, @Header("token") String token);

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
