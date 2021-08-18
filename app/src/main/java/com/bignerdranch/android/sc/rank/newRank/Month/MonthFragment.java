package com.bignerdranch.android.sc.rank.newRank.Month;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.rank.newRank.RankAdapter;
import com.bignerdranch.android.sc.rank.newRank.RankItem;

import java.util.ArrayList;
import java.util.List;

import top.defaults.view.PickerView;

public class MonthFragment extends Fragment implements MonthAPI.VP {

    private RecyclerView mRecyclerView;
    private ImageView mExchange;
    private List<RankItem> mList;
    private MonthP mP = new MonthP();
    SharedPreferences mSharedPreferences = getActivity().getSharedPreferences("Token",0);
    String token = mSharedPreferences.getString("Token","null");

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_rank, container, false);


        mP.bindView(this);
        mRecyclerView = view.findViewById(R.id.month_item);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        mRecyclerView.setAdapter(new RankAdapter(mList));
        mExchange = view.findViewById(R.id.exchange);
        mExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWheelDialog();
            }
        });
        return view;
    }

    public void showWheelDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(getActivity(),R.layout.rank_rolldialog,null));
        dialog.show();
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.rank_rolldialog);
        TextView textView = window.findViewById(R.id.rank_num);
        PickerView pickerView = window.findViewById(R.id.pickerView);
        Button yes = window.findViewById(R.id.yes);
        Button no = window.findViewById(R.id.no);

        List items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(i);
        }

        PickerView.Adapter adapter = new PickerView.Adapter() {

            @Override
            public int getItemCount() {
                return items.size();
            }

            @Override
            public PickerView.PickerItem getItem(int index) {
                return null;
            }

            @Override
            public String getText(int index) {
                return (String) items.get(index);
            }
        };
        pickerView.setAdapter(adapter);
        pickerView.setOnSelectedItemChangedListener(new PickerView.OnSelectedItemChangedListener() {

            public void onSelectedItemChanged(PickerView pickerView, int previousPosition, int selectedItemPosition) {
                textView.setText(String.valueOf(selectedItemPosition*2+50));
                yes.setOnClickListener(v -> changeRank(selectedItemPosition,token));
            }
        });

        no.setOnClickListener(v -> dialog.dismiss());
    }

    @Override
    public List requestList() {

        return null;
    }

    @Override
    public void changeRank(int rank, String token) {

    }

    @Override
    public void haveList() {

    }

    @Override
    public void ListFail() {

    }

    @Override
    public void changeSuccess() {
        Toast.makeText(getContext(),"兑换成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeFail() {
        Toast.makeText(getContext(),"金币不足！快去打卡赚金币吧！",Toast.LENGTH_SHORT).show();
    }
}
