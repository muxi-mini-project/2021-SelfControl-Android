package com.bignerdranch.android.sc.clockin;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ClockinActivity extends AppCompatActivity {
    private ImageView mImageView;
    private TextView mTextView;
    private int mTimes;
    private Button mButton;

    public ImageView getImageView(){
        return mImageView;
    }

    public void setImageView(ImageView imageView){
        mImageView = imageView;
    }

    public TextView getmTextView() {
        return mTextView;
    }

    public void setmTextView(TextView textView) {
        mTextView = textView;
    }

    public int getmTimes() {
        return mTimes;
    }

    public void setmTimes(int day) {
        mTimes = day;
    }

    public Button getmButton() {
        return mButton;
    }

    public void setmButton(Button button) {
        mButton = button;
    }
}
