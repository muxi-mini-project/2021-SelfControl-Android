package com.bignerdranch.android.sc.SeeUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.label.PunchAPI;
import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserLabelActivity extends StatusBar {

    private TextView userName;
    private String name;
    private String id;

    private RecyclerView mRecyclerView;
    private UserLabelAdapter adapter;
    private List<LabelPunch> mLabelPunchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_user_label);

        Intent intent = getIntent();
        name = intent.getStringExtra("data1");
        id = intent.getStringExtra("data");

        userName = findViewById(R.id.user_name);
        userName.setText(name);

        mRecyclerView = findViewById(R.id.user_rv);

        initList(id);

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }


    private  void initList(String id){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<List<LabelPunch>> call = client.getUserLabel(id);

        call.enqueue(new Callback<List<LabelPunch>>() {
            @Override
            public void onResponse(Call<List<LabelPunch>> call, Response<List<LabelPunch>> response) {
                mLabelPunchList = response.body();
                if(response.body() != null) {
                    UpUI();
                }
            }

            @Override
            public void onFailure(Call<List<LabelPunch>> call, Throwable t) {

            }
        });
    }

    private void UpUI(){


    }
}
