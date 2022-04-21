package com.bignerdranch.android.sc.rank.newrank.presenter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.rank.newrank.bean.Message;
import com.bignerdranch.android.sc.rank.newrank.bean.RankItem;
import com.bignerdranch.android.sc.seeuser.SeeUserActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        private final SimpleDraweeView mUser;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Uri uri = Uri.parse(mList.get(position).getUser_picture());
        shake = AnimationUtils.loadAnimation(mActivity, R.anim.shake);
        holder.mRate.setText("打卡次数: " + mList.get(position).getNumber() + " 次");
        holder.mName.setText("" + mList.get(position).getName());
        holder.mUser.setImageURI(uri);
        holder.mUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeUserRequest(mList.get(position).getStudent_id(), mList.get(position).getName(), mList.get(position).getUser_picture());
            }
        });
        holder.mThumb.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 v.startAnimation(shake);
                                             }
                                         }
        );
        switch (mList.get(position).getRanking()) {
            case 1:
                holder.mRank.setBackgroundResource(R.mipmap.rank1);
                break;
            case 2:
                holder.mRank.setBackgroundResource(R.mipmap.rank2);
                break;
            case 3:
                holder.mRank.setBackgroundResource(R.mipmap.rank3);
                break;
            default:
                holder.mRank.setText(String.valueOf(mList.get(position).getRanking()));
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

    public void seeUserRequest(String id, String name, String uri) {
        NetUtil.getInstance().getApi().askPrivacy(id).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                mPrivacy = response.body();
                if (mPrivacy != null) {
                    if (mPrivacy.getData() == 2) {
                        showPrivateDialog();
                    } else if (mPrivacy.getData() == 1) {
                        Intent intent = new Intent(getActivity(), SeeUserActivity.class);
                        intent.putExtra("data", id);
                        intent.putExtra("data1", name);
                        intent.putExtra("uri",uri);
                        mActivity.startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    private void showPrivateDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(getActivity(), R.layout.dialog_private, null));
        dialog.show();
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_private);

        Button yes = window.findViewById(R.id.know);
        yes.setOnClickListener(v -> dialog.dismiss());
    }
}
