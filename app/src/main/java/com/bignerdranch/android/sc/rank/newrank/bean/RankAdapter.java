package com.bignerdranch.android.sc.rank.newrank.bean;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.seeuser.SeeUserV;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    private List<RankItem.RankDataBean> mList;
    private Message mPrivacy;
    private Activity mActivity;
    Animation shake ;
    public RankAdapter(List<RankItem.RankDataBean> mList, Activity mActivity) {
        this.mList = mList;
        this.mActivity = mActivity;
    }

    public Context getActivity() {
        return mActivity;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rank_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        Uri url = Uri.parse(mList.get(position).getUser_picture());
        shake = AnimationUtils.loadAnimation(mActivity, R.anim.shake);
        holder.mRate.setText("打卡天数: " + mList.get(position).getNumber() + " 天");
        holder.mName.setText("用户昵称: " + mList.get(position).getName());
        holder.mUser.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                seeUserRequest(mList.get(position).getStudent_id(), mList.get(position).getName());
                                            }
                                        });
                holder.mThumb.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {
                                                         v.startAnimation(shake);
                                                     }
                                                 }
                );
        //    holder.mUser.setImageURI(url);
        switch (position) {
            case 0:
                holder.mRank.setBackgroundResource(R.mipmap.rank1);
                break;
            case 1:
                holder.mRank.setBackgroundResource(R.mipmap.rank2);
                break;
            case 2:
                holder.mRank.setBackgroundResource(R.mipmap.rank3);
                break;
            default:
                holder.mRank.setText(String.valueOf(position));
                break;
        }

    }


    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
        //return 0;
    }

    public void seeUserRequest(String id, String name) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.99.53.8:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        seeUserAPI mApi = retrofit.create(seeUserAPI.class);
        Call<Message> call = mApi.askPrivacy(id);

        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                mPrivacy = response.body();
                if (mPrivacy != null) {
                    if (mPrivacy.getData() == 2) {
                        showPrivateDialog();
                    } else if (mPrivacy.getData() == 1) {
                        Intent intent = new Intent(getActivity(), SeeUserV.class);
                        intent.putExtra("data", id);
                        intent.putExtra("data1", name);
                        mActivity.startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    public interface seeUserAPI {
        @GET("user/privacy/{id}")
        Call<Message> askPrivacy(@Path("id") String id);

    }

    private void showPrivateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(getActivity(), R.layout.dialog_private, null));
        dialog.show();
    }
}
