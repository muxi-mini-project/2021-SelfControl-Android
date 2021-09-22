package com.bignerdranch.android.sc.settings.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.settings.api.PrivateAPI;
import com.bignerdranch.android.sc.settings.presenter.PrivatePresenter;
import com.bignerdranch.android.sc.user.model.GetBackdropAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class PrivateActivity extends StatusBar implements PrivateAPI.VP,View.OnClickListener{

    private ImageButton mBack;
    private Button mTrue, mFalse;
    private User mUser;
    private ConstraintLayout mLayout;
    private PrivatePresenter mP = new PrivatePresenter();


    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.choose_open);
        mP.bindView(this);
        initView();
        requestBg();
        System.out.println(token);

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    public void initView(){
        mLayout = findViewById(R.id.choose_layout);
        mBack = findViewById(R.id.private_back);
        mBack.setOnClickListener(this);
        mTrue = findViewById(R.id.true_button);
        mTrue.setOnClickListener(this);
        mFalse = findViewById(R.id.false_button);
        mFalse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.private_back:
                finish();
                break;
            case R.id.true_button:
                trueRequest(token);
                break;
            case R.id.false_button:
                falseRequest(token);
                break;

        }
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

    @Override
    public void trueRequest(String token) {
        mP.trueRequest(token);
    }

    @Override
    public void falseRequest(String token) {
        mP.falseRequest(token);
    }

    @Override
    public void success() {
        Toast.makeText(PrivateActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail() {
        Toast.makeText(PrivateActivity.this,"出错啦！请检查网络设置！",Toast.LENGTH_SHORT).show();
    }
}
