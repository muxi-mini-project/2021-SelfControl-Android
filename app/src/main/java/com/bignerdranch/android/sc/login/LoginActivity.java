package com.bignerdranch.android.sc.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.clockpage.view.ClockActivity;
import com.bignerdranch.android.sc.label.LabelPagerActivity;
import com.bignerdranch.android.sc.net.NetUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends StatusBar {

    private EditText mStudent_Id;
    private EditText mPassword;
    private Button mLoginButton;
    public static String token;

    public static int key = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        initWidgets();

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void initWidgets() {
        SharedPreferences sharedPreferences = getSharedPreferences("Token",0);
        token = sharedPreferences.getString("Token",null);
        IsToken(token);

        mStudent_Id =findViewById(R.id.username);
        mPassword =findViewById(R.id.password);
        mLoginButton =findViewById(R.id.login_B);
    }

    public void loginAction(View view) {
        if (Utils.isFastClick()){
            key = 0;
            String id = mStudent_Id.getText().toString();
            String password = mPassword.getText().toString();
            request(id,password);
        }
    }

    public void IsToken(String token){
        if (token != null){
            Intent intent = new Intent(LoginActivity.this, ClockActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void request(String id,String password){

        NetUtil.getInstance().getApi().getCall(new User.DataDTO(id,password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Intent intent=new Intent(LoginActivity.this, LabelPagerActivity.class);
                    startActivity(intent);
                    token = response.body().getData();
                    Log.d("tag", "token "+response.body().getData());

                    SharedPreferences sharedPreferences = getSharedPreferences("Token",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Token",token);
                    editor.commit();
                    editor.apply();
                }else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                Toast.makeText(LoginActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
                Log.e("tag",throwable.getMessage());
            }

        });

    }

}
