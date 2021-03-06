package com.bignerdranch.android.sc.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        mstudent_id=(EditText)findViewById(R.id.username);
        mpassword=(EditText)findViewById(R.id.password);

        mloginbutton=(Button)findViewById(R.id.login_B);
        mloginbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String id = mstudent_id.getText().toString();
                String password = mpassword.getText().toString();

                request(id,password);
            }
        });

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void request(String id,String password){
        Retrofit retrofit = new Retrofit
                .Builder().baseUrl("http://124.71.184.107:2333/api/v1/").addConverterFactory(GsonConverterFactory.create()).build();
        LoginAPI request = retrofit.create(LoginAPI.class);

        Call<User> call = request.getCall(id,password);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Intent intent=new Intent(LoginActivity.this, LabelPagerActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                Intent intent=new Intent(LoginActivity.this, LabelPagerActivity.class);
                startActivity(intent);
            }
        });

    }

}
