package com.bignerdranch.android.sc.clockin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class ClockinFragment extends Fragment {
    private static final String ARG_CRIME_ID = "clockin_id";

    private ClockinActivity mClockin;
    private ImageView mImageView;
    private TextView mTextView;
    private int mDay;
    private Button mButton;

    public static  ClockinFragment newInstance(UUID clockinId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID,clockinId);

        ClockinFragment fragment = new ClockinFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
