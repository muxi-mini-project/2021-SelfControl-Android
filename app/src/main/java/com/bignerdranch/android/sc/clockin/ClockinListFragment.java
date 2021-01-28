package com.bignerdranch.android.sc.clockin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;

import java.util.List;

public class ClockinListFragment extends Fragment {

    private RecyclerView mClockinRecyclerView;
    private ClockinAdapter mAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_clockin_list,container,false);

        mClockinRecyclerView = (RecyclerView)view.findViewById(R.id.clockin_recycler_view);
        mClockinRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        ClockinLab clockinLab = ClockinLab.get(getActivity());
        mClockinRecyclerView.setAdapter(mAdapter);
    }

    private class ClockinHolder extends RecyclerView.ViewHolder{

        private Clockin mClockin;
        private ImageView mImageView;
        private TextView mTextView1;
        private TextView mTextView2;
        private Button mButton;
        private int mDay;

        public ClockinHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.clockin_list,parent,false));

            mImageView = (ImageView) itemView.findViewById(R.id.list1);
            mTextView1 = (TextView) itemView.findViewById(R.id.text1);
            mTextView2 = (TextView) itemView.findViewById(R.id.text2);
            mButton = (Button) itemView.findViewById(R.id.clockin);
        }

        public void bind(Clockin clockin){
            mClockin = clockin;
            mTextView1.getText();
            mTextView2.setText("你已打卡：" + mDay + "天");
        }

    }


    private class ClockinAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<Clockin> mClockins;

        public ClockinAdapter(List<Clockin> clockins){
            mClockins = clockins;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                return new ClockinHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
            Clockin clockin = mClockins.get(position);
                ((ClockinHolder)holder).bind(clockin);
        }

        @Override
        public int getItemCount(){
            return mClockins.size();
        }
    }
}
