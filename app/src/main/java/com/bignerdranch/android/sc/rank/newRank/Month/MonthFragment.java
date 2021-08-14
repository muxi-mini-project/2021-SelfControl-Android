package com.bignerdranch.android.sc.rank.newRank.Month;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.rank.newRank.RankAdapter;

public class MonthFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ImageView mExchange;
    private RankAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_rank, container, false);
        mRecyclerView = view.findViewById(R.id.month_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
        mExchange = view.findViewById(R.id.exchange);
        return view;
    }

}
