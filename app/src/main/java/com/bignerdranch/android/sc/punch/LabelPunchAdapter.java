package com.bignerdranch.android.sc.punch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import org.w3c.dom.Text;

import java.util.List;

public class LabelPunchAdapter extends RecyclerView.Adapter<LabelPunchAdapter.ViewHolder> {

    private List<LabelPunch> labelPunchList;

    public LabelPunchAdapter(List<LabelPunch> labelPunchList){
        this.labelPunchList = labelPunchList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImage;
        private final TextView mTitle;
        private final TextView mTime;

        public ViewHolder(View itemView){
            super(itemView);
            mImage = itemView.findViewById(R.id.Label);
            mTitle = itemView.findViewById(R.id.title);
            mTime = itemView.findViewById(R.id.time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clockin_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        holder.mImage.setImageResource(labelPunchList.get(position).getImgID());
        holder.mTitle.setText(labelPunchList.get(position).getLabel_Title());
        holder.mTime.setText(labelPunchList.get(position).getLabel_Time());
    }

    @Override
    public int getItemCount() {
        return labelPunchList.size();
    }
}
