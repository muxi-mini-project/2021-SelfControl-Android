package com.bignerdranch.android.sc.clockpage.view.flower;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.punch.view.ClockInActivity;
import com.bignerdranch.android.sc.user.bean.Data;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.Utils;

public class FlowerFragment extends Fragment {
    private TextView mTextView;
    private ImageButton unflower;
    private View mView;
    private String text = "";
    private int date;
    private Data mData;
    private Bundle mBundle;

    public void FlowerFragment(String text) {
        this.text = text;
    }

    public void SmileFlower(){
        unflower.setBackgroundResource(R.mipmap.done);
    }

    public void UnFlower(){
        unflower.setBackgroundResource(R.mipmap.undone);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flower, container, false);
        initwidgets(view);
        mTextView.setText(text);
        mBundle = this.getArguments();
        if(mBundle != null) {date = mBundle.getInt("date");}
        unflower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    Intent intent = new Intent(getActivity(), ClockInActivity.class);
                    intent.putExtra("data",date);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private void initwidgets(View view)
    {
        mTextView = view.findViewById(R.id.today);
        mView = view.findViewById(R.id.line);
        unflower = view.findViewById(R.id.unflower);
    }
}
