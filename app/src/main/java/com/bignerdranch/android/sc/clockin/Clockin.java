package com.bignerdranch.android.sc.clockin;

import android.content.res.Resources;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

public class Clockin{

    private UUID mId;
    private ImageView mImageView;
    private TextView mTextView1;
    private TextView mTextView2;
    private int mTimes;

    public Clockin(){
        mId = UUID.randomUUID();
    }

    public UUID getId(){
        return mId;
    }

    public ImageView getImageView(){
        return mImageView;
    }

    public void setImageView(ImageView imageView){
        mImageView = imageView;
    }

    public TextView getmTextView1() {
        return mTextView1;
    }

    public void setmTextView1(TextView textView1) {
        mTextView1 = textView1;
    }

    public TextView getmTextView2() {
        return mTextView2;
    }

    public void setmTextView2(TextView textView2) {
        mTextView2 = textView2;
    }

    public int getmTimes() {
        return mTimes;
    }

    public void setmTimes(int times) {
        mTimes = times;
    }
}
