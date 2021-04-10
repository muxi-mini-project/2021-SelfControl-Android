package com.bignerdranch.android.sc.SeeUser;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.List;

public class UserLabelAdapter extends RecyclerView.Adapter<UserLabelAdapter.ViewHolder> {

    private List<LabelPunch> mLabelPunchList;

    public UserLabelAdapter(List<LabelPunch> labelPunchList){
        this.mLabelPunchList = labelPunchList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImage;
        private final TextView mTitle;
        private final TextView mTime;

        public ViewHolder(View itemView){
            super(itemView);
            mImage = itemView.findViewById(R.id.other_label);
            mTitle = itemView.findViewById(R.id.other_title);
            mTime = itemView.findViewById(R.id.other_time);

        }
    }

    @Override
    public UserLabelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.see_user_label_item,parent,false);
        return new UserLabelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserLabelAdapter.ViewHolder holder, int position) {
        holder.mImage.setImageResource(mLabelPunchList.get(position).getImgID(mLabelPunchList.get(position).getTitle()));
        holder.mTitle.setText(mLabelPunchList.get(position).getTitle());
        holder.mTime.setText("TA已打卡：" + mLabelPunchList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return mLabelPunchList.size();
    }


}
