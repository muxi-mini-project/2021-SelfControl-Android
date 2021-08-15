package com.bignerdranch.android.sc.rank.newRank.Month;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.rank.newRank.RankAdapter;
import com.bignerdranch.android.sc.rank.newRank.RankItem;

import java.util.List;

public class MonthFragment extends Fragment implements MonthAPI.VP {

    private RecyclerView mRecyclerView;
    private ImageView mExchange;
    private List<RankItem> mList;
    private MonthP mP = new MonthP();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_rank, container, false);
        mP.bindView(this);
        mRecyclerView = view.findViewById(R.id.month_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mRecyclerView.setAdapter(new RankAdapter(mList));
        mExchange = view.findViewById(R.id.exchange);
        mExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    @Override
    public List requestList() {

        return null;
    }

    @Override
    public void changeRank() {

    }

    @Override
    public void haveList() {

    }

    @Override
    public void ListFail() {

    }

    @Override
    public void changeSuccess() {
        Toast.makeText(getContext(),"兑换成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeFail() {
        Toast.makeText(getContext(),"金币不足！快去打卡赚金币吧！",Toast.LENGTH_SHORT).show();
    }
}
