package com.bignerdranch.android.sc.clockpage.flower;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.clockpage.ClockActivity;
import com.bignerdranch.android.sc.label.LabelPagerActivity;
import com.bignerdranch.android.sc.login.LoginActivity;
import com.bignerdranch.android.sc.punch.MyPunchActivity;


public class FlowerFragment extends Fragment {
    private TextView mTextView;
    private ImageButton unflower;
    private View mView;
    private String text="";


    public void FlowerFragment(String text){
        this.text=text;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.flower,container,false);

        mTextView = view.findViewById(R.id.today);
        mView = view.findViewById(R.id.line);
        unflower = view.findViewById(R.id.unflower);
        mTextView.setText(text);

        unflower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()){
                    Intent intent = new Intent(getActivity(), MyPunchActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

}
