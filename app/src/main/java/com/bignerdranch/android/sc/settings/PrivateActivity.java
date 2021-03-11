package com.bignerdranch.android.sc.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.UserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class PrivateActivity extends StatusBar {

    private ImageButton mBack;
    private Button mTrue, mFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_open);
        init();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void init(){
        mBack = (ImageButton)findViewById(R.id.private_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mTrue = (Button)findViewById(R.id.ture_button);
        mTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    new Thread() {
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
                                mCoin = findViewById(R.id.dangqianjinbi);
                                mCoin.setText(mUser.getGold());
                            }

                            @Override
                            public void onFailure(Call<User> call, Throwable t) {

                            }
                        });
                    }
                }.start();*/
            }
        });
        mFalse = (Button)findViewById(R.id.false_button);
        mFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void network(){

    }
    public interface PrivateAPI {

        @POST("user")
        @FormUrlEncoded
        Call<User> getCall(@Field("private") int param1);

    }
}
