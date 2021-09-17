package com.bignerdranch.android.sc.settings.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.model.GetBackdropAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;


public class CoinFunctionActivity extends StatusBar {

    private ImageButton mBack;
    private User.DataDTO mUser;
    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_function);

        mLayout = findViewById(R.id.coin_function_layout);
        requestBg();


        mBack = findViewById(R.id.coin_back);
        mBack.setOnClickListener(v -> finish());
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
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
                mUser = response.body().getData();
                if (mUser != null) {
                    if (mUser.getCurrent_backdrop() == 6) {
                        mLayout.setBackgroundResource(R.color.purple);
                    }
                    if (mUser.getCurrent_backdrop() == 1) {
                        mLayout.setBackgroundResource(R.color.theme2);
                    }
                    if (mUser.getCurrent_backdrop() == 2) {
                        mLayout.setBackgroundResource(R.color.theme3);
                    }
                    if (mUser.getCurrent_backdrop() == 3) {
                        mLayout.setBackgroundResource(R.mipmap.theme_31);
                    }
                    if (mUser.getCurrent_backdrop() == 4) {
                        mLayout.setBackgroundResource(R.mipmap.theme_41);
                    }
                    if (mUser.getCurrent_backdrop() == 5) {
                        mLayout.setBackgroundResource(R.mipmap.theme_51);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    @Override
    public void onResume() {

        super.onResume();
        requestBg();
    }
}
