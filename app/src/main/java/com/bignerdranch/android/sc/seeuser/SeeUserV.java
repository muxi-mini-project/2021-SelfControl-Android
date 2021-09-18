package com.bignerdranch.android.sc.seeuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;

import java.util.List;

import static com.bignerdranch.android.sc.StatusBar.makeStatusBarTransparent;

public class SeeUserV extends AppCompatActivity implements SeeUserAPI.VP {

    private TextView userName;
    private String name;
    private String id;

    private RecyclerView mRecyclerView;
    private UserLabelAdapter adapter;
    private SeeUserP mP = new SeeUserP();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_user_label);

        Intent intent = getIntent();
        name = intent.getStringExtra("data1");
        id = intent.getStringExtra("data");

        userName = findViewById(R.id.user_name);
        userName.setText(name);
        mP.bindView(this);

        getLabel(id);
        mRecyclerView = findViewById(R.id.user_rv);

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @Override
    public void getLabel(String id) {
        mP.getLabel(id);
    }

    @Override
    public void Fail() {
        Toast.makeText(this,"出错啦！请稍后再试！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void haveList(List mList) {
        if(mList!=null){
            adapter = new UserLabelAdapter(mList);
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void listNull() {
        Toast.makeText(SeeUserV.this,"该用户还没有设置标签！",Toast.LENGTH_SHORT).show();
    }
}
