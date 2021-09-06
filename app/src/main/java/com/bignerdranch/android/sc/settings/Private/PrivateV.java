package com.bignerdranch.android.sc.settings.Private;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;

public class PrivateV extends StatusBar implements PrivateAPI.VP,View.OnClickListener{

    private ImageButton mBack;
    private Button mTrue, mFalse;
    private User mUser;
    private ConstraintLayout mLayout;
    private PrivateP mP = new PrivateP();
    private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdHVkZW50X2lkIjoiMjAyMDIxMzY2NCIsImV4cCI6MTYzMTM1OTIyOSwiaWF0IjoxNjMwNjM5MjI5fQ.FkdwiyYO-nyjODGRuxH91EVA6ub_0gr7ZxI62ordSwA";
//    String token = getSharedPreferences("Token",0).getString("Token",null);

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.choose_open);
        mP.bindView(this);
        initView();
        System.out.println(token);

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
        Toast.makeText(PrivateV.this,"修改成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail() {
        Toast.makeText(PrivateV.this,"出错啦！请检查网络设置！",Toast.LENGTH_SHORT).show();
    }
}
