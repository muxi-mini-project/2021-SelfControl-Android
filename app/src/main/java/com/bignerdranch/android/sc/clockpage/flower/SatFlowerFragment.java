package com.bignerdranch.android.sc.clockpage.flower;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;

public class SatFlowerFragment extends Fragment {
    private TextView mTextView;
    private ImageButton unflower;
    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.flower_sat,container,false);

        mTextView = (TextView)view.findViewById(R.id.today_date);
        mView = (View)view.findViewById(R.id.line);;
        unflower = (ImageButton)view.findViewById(R.id.unflower);

        return view;
    }
}
