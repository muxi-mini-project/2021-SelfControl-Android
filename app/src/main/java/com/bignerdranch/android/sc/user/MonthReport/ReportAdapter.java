package com.bignerdranch.android.sc.user.MonthReport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    private List<Report> ReportList;

    public ReportAdapter(List<Report> ReportList){
        this.ReportList = ReportList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView mTitle;
        private final TextView mTime;

        public ViewHolder(View itemView){
            super(itemView);
            mTime = itemView.findViewById(R.id.punch_time);
            mTitle = itemView.findViewById(R.id.punch_title);

        }

    }

    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.month_report_item,parent,false);
        return new ReportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder , final int position){
        holder.mTitle.setText(ReportList.get(position).getTitle());
        holder.mTime.setText(String.valueOf(ReportList.get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return ReportList.size();
    }
}
