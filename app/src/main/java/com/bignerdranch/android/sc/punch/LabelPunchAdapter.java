package com.bignerdranch.android.sc.punch;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.label.MyFragmentPagerAdapter;
import com.bignerdranch.android.sc.settings.BackgroundActivity;

import org.w3c.dom.Text;

import java.util.List;

public class LabelPunchAdapter extends RecyclerView.Adapter<LabelPunchAdapter.ViewHolder> {

    private List<LabelPunch> labelPunchList;
    private OnItemClickListener onItemClickListener;

    private Context mContext;

    public LabelPunchAdapter(List<LabelPunch> labelPunchList){
        this.labelPunchList = labelPunchList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImage;
        private final TextView mTitle;
        private final TextView mTime;
        private final Button mPunch;
        private final Button mDelete;

        public ViewHolder(View itemView){
            super(itemView);
            mImage = itemView.findViewById(R.id.Label);
            mTitle = itemView.findViewById(R.id.title);
            mTime = itemView.findViewById(R.id.time);
            mPunch = itemView.findViewById(R.id.punch);
            mDelete = itemView.findViewById(R.id.delete);

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

        if(onItemClickListener!=null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    holder.mPunch.setVisibility(View.INVISIBLE);
//                    holder.mDelete.setVisibility(View.VISIBLE);
//
//                    holder.mDelete.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                        }
//                    });

                    onItemClickListener.onItemLongClick(holder.itemView, position);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return labelPunchList.size();
    }

    public interface OnItemClickListener{
        void onItemLongClick(View view , int positon);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
