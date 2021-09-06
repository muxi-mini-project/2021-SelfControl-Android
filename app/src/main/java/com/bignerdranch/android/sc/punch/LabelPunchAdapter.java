package com.bignerdranch.android.sc.punch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.user.bean.Data;
import com.bignerdranch.android.sc.user.bean.Message;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.label.Punch;
import com.bignerdranch.android.sc.label.PunchAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class LabelPunchAdapter extends RecyclerView.Adapter<LabelPunchAdapter.ViewHolder> {

    private List<LabelPunch> labelPunchList;
    private OnItemClickListener onItemClickListener;

    private Context context;

    private Data mData;

    public LabelPunchAdapter(List<LabelPunch> labelPunchList){
        this.labelPunchList = labelPunchList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImage;
        private final TextView mTitle;
        private final TextView mTime;
        private final Button mPunch;

        public ViewHolder(View itemView){
            super(itemView);
            mImage = itemView.findViewById(R.id.Label);
            mTitle = itemView.findViewById(R.id.title);
            mTime = itemView.findViewById(R.id.time);
            mPunch = itemView.findViewById(R.id.punch);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clockin_item,parent,false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {

        holder.mImage.setImageResource(labelPunchList.get(position).getImgID(labelPunchList.get(position).getTitle()));
        holder.mTitle.setText(labelPunchList.get(position).getTitle());
        holder.mTime.setText("我的打卡数：" + labelPunchList.get(position).getNumber());
        holder.mPunch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                holder.mPunch.setEnabled(false);
                holder.mPunch.setText("已打卡");
                holder.mPunch.setBackgroundResource(R.drawable.punch_done);
                holder.mTime.setText("我的打卡数：1");

                punch(labelPunchList.get(position).getTitle());

                ifpunchcomplete();
            }
        });

        if (labelPunchList.get(position).getNumber() != 0){
            holder.mPunch.setEnabled(false);
            holder.mPunch.setText("已打卡");
            holder.mPunch.setBackgroundResource(R.drawable.punch_done);
        }

        if(onItemClickListener!=null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    holder.mPunch.setVisibility(View.INVISIBLE);
//                    holder.mDelete.setVisibility(View.VISIBLE);
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

    private void punch(String title){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<Message> call = client.punch(token,new Punch(title));

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    private void getPunch(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client = retrofit.create(PunchAPI.class);
        Call<List<LabelPunch>> call = client.getPunch(token);

        call.enqueue(new Callback<List<LabelPunch>>() {
            @Override
            public void onResponse(Call<List<LabelPunch>> call, Response<List<LabelPunch>> response) {

            }

            @Override
            public void onFailure(Call<List<LabelPunch>> call, Throwable t) {

            }
        });
    }


    private void ifpunchcomplete(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client3 = retrofit.create(PunchAPI.class);
        Call<Data> call = client3.ifpunchcomplete(token);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                mData = response.body();
                mData.getData();
                int money;
                if (mData.getData() > 0 ) {
                    if (mData.getData() <= 5) {
                        money = mData.getData() * 10;
                    } else {
                        money = 2 * (mData.getData() - 5) + 50;
                    }
                    Toast.makeText(context, "您已经获得" + money + "金币", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }


}
