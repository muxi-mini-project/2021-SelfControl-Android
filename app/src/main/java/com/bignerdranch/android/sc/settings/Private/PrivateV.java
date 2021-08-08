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

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.choose_open);

        initView();

    }
    public void initView(){
        mLayout = findViewById(R.id.choose_layout);
        mBack = findViewById(R.id.private_back);
        mTrue = findViewById(R.id.true_button);
        mFalse = findViewById(R.id.false_button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.private_back:
                finish();
                break;
            case R.id.true_button:
                trueRequest();
                break;
            case R.id.false_button:
                falseRequest();
                break;

        }
    }

    @Override
    public void trueRequest() {
        mP.trueRequest();
    }

    @Override
    public void falseRequest() {
        mP.falseRequest();
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
