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
import com.bignerdranch.android.sc.ClockPage.View.ClockActivity;
import com.bignerdranch.android.sc.label.LabelPagerActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends StatusBar {

    private EditText mstudent_id;
    private EditText mpassword;
    private Button mloginbutton;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        SharedPreferences sharedPreferences = getSharedPreferences("Token",0);
        token = sharedPreferences.getString("Token",null);
        IsToken(token);

        mstudent_id=findViewById(R.id.username);
        mpassword=findViewById(R.id.password);

        mloginbutton=findViewById(R.id.login_B);
        mloginbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()){
                    String id = mstudent_id.getText().toString();
                    String password = mpassword.getText().toString();

                    request(id,password);
                }

            }
        });

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void request(String id,String password){
        Retrofit retrofit = new Retrofit
                .Builder().baseUrl("http://39.99.53.8:2333/api/v1/").addConverterFactory(GsonConverterFactory.create()).build();
        LoginAPI request = retrofit.create(LoginAPI.class);

        Call<LoginResponse> call = request.getCall(new User(id,password));

        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()==true){
                    Intent intent=new Intent(LoginActivity.this, LabelPagerActivity.class);
                    startActivity(intent);
                    token = response.body().getToken();
                    Log.d("tag", "token "+response.body().getToken());

                    SharedPreferences sharedPreferences = getSharedPreferences("Token",0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Token",token);
                    editor.commit();

                }else {
                    Toast.makeText(LoginActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                Intent intent=new Intent(LoginActivity.this, LabelPagerActivity.class);
                startActivity(intent);
                Toast.makeText(LoginActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
                Log.e("tag",throwable.getMessage());
            }

        });

    }

    public void IsToken(String token){
        if (token != null){
            Intent intent = new Intent(LoginActivity.this, ClockActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
