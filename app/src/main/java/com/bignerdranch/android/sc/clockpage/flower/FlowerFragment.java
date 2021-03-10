package com.bignerdranch.android.sc.clockpage.flower;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;

public class FlowerFragment extends Fragment {
    private TextView mTextView;
    private ImageButton unflower;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.flower,container,false);

        mTextView = view.findViewById(R.id.today);
        mView = view.findViewById(R.id.line);
        unflower = view.findViewById(R.id.unflower);

        return view;
    }

    public void setTextV(String m){
        mTextView.setText(m);
    }
}