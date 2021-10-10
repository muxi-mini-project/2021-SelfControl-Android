package com.bignerdranch.android.sc.clockpage.weekcalendar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private final ArrayList<LocalDate> days;
    public final View parentView;
    public final TextView dayOfMonth;
    public final View cicle;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(ArrayList<LocalDate> days, @NonNull View itemView, CalendarAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        this.days = days;
        parentView = itemView.findViewById(R.id.parentView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        cicle = itemView.findViewById(R.id.circle);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        onItemListener.onItemClick(getAdapterPosition(),days.get(getAdapterPosition()));
    }
}
