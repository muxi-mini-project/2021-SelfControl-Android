package com.bignerdranch.android.sc.clockpage.view.flower;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.punch.view.ClockInActivity;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.Utils;

public class FlowerFragment extends Fragment {
    private TextView mTextView;
    private ImageView unflower;
    private View mView;
    private String text = "";

    public FlowerFragment(String text) {
        this.text = text;
    }

    public void setSmileFlower(){
        unflower.setBackgroundResource(R.mipmap.done);
    }

    public void setWhiteFlower(){
        if (unflower!=null)
            unflower.setBackgroundResource(R.mipmap.undone);
    }

    private void initWidgets(View view)
    {
        mTextView = view.findViewById(R.id.today);
        mView = view.findViewById(R.id.line);
        unflower = view.findViewById(R.id.unflower);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flower,container,false);
        initWidgets(view);
        mTextView.setText(text);
        unflower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    Intent intent = new Intent(getActivity(), ClockInActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}
