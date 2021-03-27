package com.bignerdranch.android.sc.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.UserActivity;
import com.bignerdranch.android.sc.user.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class PrivateActivity extends StatusBar {

    private ImageButton mBack;
    private Button mTrue, mFalse;
    private User mUser;
    private int mCoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_open);
        init();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void init() {
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
}
