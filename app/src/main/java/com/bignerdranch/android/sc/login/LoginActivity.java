package com.bignerdranch.android.sc.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.label.LabelPagerView;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginAPI.VP {

    private final LoginPresenter mPresenter = new LoginPresenter();
    private EditText mStudent_id;
    private EditText mPassword;
    private Button mLoginButton;
    private LoginPresenter mLoginPresenter = new LoginPresenter();
    public static String token;

    public static int key = 2;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.login_page);

        mPresenter.bindView(this);
        initView();
        initWidgets();
    }

    public void initView() {
        mStudent_id = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mLoginButton = findViewById(R.id.login_B);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    String id = mStudent_id.getText().toString();
                    String password = mPassword.getText().toString();
                    mPresenter.login(id, password);
                }
            }
        });
    }

    public void initWidgets(){
        SharedPreferences sharedPreferences = getSharedPreferences("Token",0);
        token = sharedPreferences.getString("Token",null);
        IsToken(token);
    }

    public void IsToken(String token){
        if(token != null) {
            Intent intent = new Intent(LoginActivity.this, com.bignerdranch.android.sc.clockpage.view.ClockActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    public void onSuccess(Response<LoginResponse> response){
        Intent intent=new Intent(LoginActivity.this, LabelPagerView.class);
        startActivity(intent);
        token = response.body().getData();
        Log.d("tag", "token "+response.body().getData());

        SharedPreferences sharedPreferences = getSharedPreferences("Token",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token",token);
        editor.commit();
        editor.apply();
        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail() {
        Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error(Throwable throwable) {
        Toast.makeText(LoginActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
        throwable.printStackTrace();
        Log.e("tag",throwable.getMessage());
    }

}