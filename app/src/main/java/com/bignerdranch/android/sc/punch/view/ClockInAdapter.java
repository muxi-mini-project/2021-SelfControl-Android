package com.bignerdranch.android.sc.punch.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.punch.bean.LabelPunch;

import java.util.List;

class ClockInAdapter extends RecyclerView.Adapter<ClockInAdapter.ClockInHolder> {
    List<LabelPunch> mClockInLabels;
    private int viewDay;
    private int yearDay;
    private int buttonBG;

    AdapterCallBack.popupCallBack popupCallBack;
    AdapterCallBack.clockInCallBack clockInCallBack;

    public ClockInAdapter(int viewDay, int yearDay) {
        this.viewDay = viewDay;
        this.yearDay = yearDay;
    }

    public void setClockInLabels(List<LabelPunch> labels, int buttonBG){
        mClockInLabels = labels;
        this.buttonBG = buttonBG;
    }

    public void setPopupWindowCallBack(AdapterCallBack.popupCallBack popupCallBack){this.popupCallBack = popupCallBack;}

    public void setClockInCallBack(AdapterCallBack.clockInCallBack clockInCallBack){this.clockInCallBack = clockInCallBack;}

    @NonNull
    @Override
    public ClockInAdapter.ClockInHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clockin_item, parent, false);
        ClockInHolder clockInHolder = new ClockInHolder(view);
        clockInHolder.setIsRecyclable(false);
        return clockInHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ClockInAdapter.ClockInHolder holder, @SuppressLint("RecyclerView") int position) {
        LabelPunch clockInLabel = mClockInLabels.get(position);

        holder.bind(clockInLabel);

        holder.clockIn_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clockInCallBack.clockInCallBack(clockInLabel);
            }
        });

        holder.mConstraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holder.clockIn_delete.setVisibility(View.VISIBLE);
                holder.clockIn_button.setVisibility(View.GONE);
                return true;
            }
        });

        holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.clockIn_delete.setVisibility(View.GONE);
                holder.clockIn_button.setVisibility(View.VISIBLE);
            }
        });

        holder.clockIn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupCallBack.popupCallBack(clockInLabel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mClockInLabels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ClockInHolder extends RecyclerView.ViewHolder {
        ConstraintLayout mConstraintLayout;
        TextView clockIn_title, clockIn_times;
        Button clockIn_button, clockIn_delete;
        ImageView clockIn_image;

        public ClockInHolder(@NonNull View itemView) {
            super(itemView);
            clockIn_title = itemView.findViewById(R.id.title);
            clockIn_times = itemView.findViewById(R.id.time);
            clockIn_button = itemView.findViewById(R.id.punch);
            clockIn_image = itemView.findViewById(R.id.Label);
            clockIn_delete = itemView.findViewById(R.id.delete);
            mConstraintLayout = itemView.findViewById(R.id.linearLayout15);
            setClockIn_button();
        }

        // 设置打卡button为主题色
        public void setClockIn_button(){
            switch (buttonBG){
                case 1: clockIn_button.setBackgroundResource(R.mipmap.background_default); break;
                case 2: clockIn_button.setBackgroundResource(R.mipmap.theme_1); break;
                case 3: clockIn_button.setBackgroundResource(R.mipmap.theme_2); break;
                case 4: clockIn_button.setBackgroundResource(R.mipmap.theme_3); break;
                case 5: clockIn_button.setBackgroundResource(R.mipmap.theme_4); break;
                case 6: clockIn_button.setBackgroundResource(R.mipmap.theme_5); break;
            }
        }

        public void bind(LabelPunch clockInLabel) {
            clockIn_title.setText(clockInLabel.getTitle());
            clockIn_times.setText("累计打卡：" + String.valueOf(clockInLabel.getNumber()) + "次");
            clockIn_image.setImageResource(clockInLabel.getImgID(clockInLabel.getTitle()));

            switch (clockInLabel.getLabelStatus()) {
                case 2:
                    clockIn_title.setText(clockInLabel.getTitle() + "(已删除)");
                case 1:
                    clockIn_button.setBackgroundResource(R.drawable.punch_done);
                    clockIn_button.setTextColor(Color.parseColor("#FDD682"));
                    clockIn_button.setEnabled(false);
                    clockIn_button.setText("已打卡");
                    break;
                case 3:
                    if (viewDay < yearDay) {
                        clockIn_button.setBackgroundResource(R.drawable.punch_missed);
                        clockIn_button.setEnabled(false);
                        clockIn_button.setText("未打卡");
                    } else if (viewDay > yearDay) {
                        clockIn_button.setBackgroundResource(R.drawable.punch_missed);
                        clockIn_button.setEnabled(false);
                        clockIn_button.setText("未到打卡日");
                    }
                    break;
                default:
                    break;
            }
            /*
            if (clockInLabel.getLabelStatus() == 1) {
                clockIn_button.setBackgroundResource(R.drawable.punch_done);
                clockIn_button.setTextColor(Color.parseColor("#FDD682"));
                clockIn_button.setEnabled(false);
                clockIn_button.setText("已打卡");
            } else if(clockInLabel.getLabelStatus() == 0){
                if (viewDay < yearDay) {
                    clockIn_button.setBackgroundResource(R.drawable.punch_missed);
                    clockIn_button.setEnabled(false);
                    clockIn_button.setText("未打卡");
                } else if (viewDay > yearDay) {
                    clockIn_button.setBackgroundResource(R.drawable.punch_missed);
                    clockIn_button.setEnabled(false);
                    clockIn_button.setText("未到打卡日");
                }
            } else if(clockInLabel.getLabelStatus() == 2)
             */
        }
    }
}
