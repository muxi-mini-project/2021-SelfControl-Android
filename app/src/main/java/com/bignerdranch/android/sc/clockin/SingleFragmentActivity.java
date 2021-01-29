package com.bignerdranch.android.sc.clockin;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.label.StudyFragment;

import java.sql.Timestamp;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract ClockinListFragment createFragment();
    private ImageView mImageView;
    private TextView mTextView1;
    private TextView mTextView2;
    private int mTimes = 0;
    private Button mButton;

    private boolean status=false;


    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.clockin_list);

        mImageView = (ImageView)findViewById(R.id.list1);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new StudyFragment());
                fragmentTransaction.commit();
            }
        });

        mTextView1 = (TextView)findViewById(R.id.text1);
        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container,new StudyFragment());
                fragmentTransaction.commit();
            }
        });

        mTextView2 = (TextView)findViewById(R.id.text2);

        mButton = (Button) findViewById(R.id.clockin);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!status){
                    mButton.setText("已完成");
                    status=true;
                    mTimes++;
                }else{
                    mButton.setText("我要打卡");
                    status=false;
                }

                mTimes++;
            }
        });
    }

    public void send1(String s) {
        mTextView1.setText(s);
    }
    
}
