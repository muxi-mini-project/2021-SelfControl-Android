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
import com.bignerdranch.android.sc.label.HealthFragment;
import com.bignerdranch.android.sc.label.SportFragment;
import com.bignerdranch.android.sc.label.StudyFragment;

import java.sql.Timestamp;

import static com.bignerdranch.android.sc.StatusBar.makeStatusBarTransparent;

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
        setContentView(R.layout.clockin_fragment_container);

        mButton = (Button) findViewById(R.id.clockin);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!status){
                    mButton.setText("已完成");
                    mButton.setEnabled(false);
                    status=true;
                    mTimes++;
                }else{
                    mButton.setText("我要打卡");
                    mButton.setEnabled(true);
                    status=false;
                }

                mTimes++;
            }
        });

        mImageView = (ImageView)findViewById(R.id.list1);

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}
