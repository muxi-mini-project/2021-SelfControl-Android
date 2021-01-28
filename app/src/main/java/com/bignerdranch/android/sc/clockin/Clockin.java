package com.bignerdranch.android.sc.clockin;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Clockin extends AppCompatActivity {
    private ImageView mImageView;
    private TextView mTextView;
    private int mDay;
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

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int day) {
        mDay = day;
    }

    public Button getmButton() {
        return mButton;
    }

    public void setmButton(Button button) {
        mButton = button;
    }
}
