package com.bignerdranch.android.sc.user.Presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.user.Bean.GoldHistory;

import java.util.ArrayList;
import java.util.List;

public class GoldAdapter extends RecyclerView.Adapter<GoldAdapter.ViewHolder_gold> {
    private Context context;
    private List<GoldHistory> list = new ArrayList<>();

    public GoldAdapter(Context context, List<GoldHistory> list) {
        this.context = context;
        this.list = list;
    }

    public ViewHolder_gold onCreateViewHolder(ViewGroup parent, int TypeView) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder_gold(inflater, parent);
    }


    @Override
    public void onBindViewHolder(ViewHolder_gold viewHolder, int position) {
        GoldHistory goldHistory = list.get(position);
        viewHolder.bind(goldHistory);
    }

    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder_gold extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;

        public ViewHolder_gold(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_goldhistory, parent, false));
            init();
        }

        private void init() {
            textView1 = itemView.findViewById(R.id.gold_amount);
            textView2 = itemView.findViewById(R.id.gold_time);
            textView3 = itemView.findViewById(R.id.gold_reason);
        }

        public void bind(GoldHistory goldHistory) {
            textView1.setText(goldHistory.getChange_number());
            textView2.setText(goldHistory.getTime());
            textView3.setText(goldHistory.getReason());
        }
    }

}


