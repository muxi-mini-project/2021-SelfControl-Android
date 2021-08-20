package com.bignerdranch.android.sc.rank.newRank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    private List<RankItem> mList;
    public RankAdapter(List<RankItem> mList){
        this.mList = mList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mRank;
        private final ImageView mUser;
        private final TextView mName;
        private final TextView mRate;
        private final ImageView mThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRank = itemView.findViewById(R.id.m_rank);
            mName = itemView.findViewById(R.id.m_name);
            mRate = itemView.findViewById(R.id.m_rate);
            mUser = itemView.findViewById(R.id.m_user);
            mThumb = itemView.findViewById(R.id.thumb);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mRate.setText("打卡天数: "+ mList.get(position).getNumber() + " 天");
        holder.mName.setText("用户昵称: " + mList.get(position).getName());
        switch (position){
            case 0: holder.mRank.setBackgroundResource(R.mipmap.rank1);break;
            case 1: holder.mRank.setBackgroundResource(R.mipmap.rank2);break;
            case 2: holder.mRank.setBackgroundResource(R.mipmap.rank3);break;
            default: holder.mRank.setText(String.valueOf(position));break;
        }
        holder.mUser.setOnClickListener(v -> {
            seeUserRequest();
        });
        holder.mThumb.setOnClickListener(v -> );
    }


    @Override
    public int getItemCount() {
        return mList.size();
        //return 0;
    }
    public void seeUserRequest(String id){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        seeUserAPI mApi = retrofit.create(seeUserAPI.class);
        Call<List<LabelPunch>> call = mApi.getUserLabel(id);
        call.enqueue(new Callback<List<LabelPunch>>() {
            @Override
            public void onResponse(Call<List<LabelPunch>> call, Response<List<LabelPunch>> response) {

            }

            @Override
            public void onFailure(Call<List<LabelPunch>> call, Throwable t) {

            }
        });
    }
    public interface seeUserAPI{
        @GET("/punch/punch/{id}")
        Call<List<LabelPunch>> getUserLabel(@Path("id") String id);

    }
}
