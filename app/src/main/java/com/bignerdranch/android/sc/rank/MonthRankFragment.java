package com.bignerdranch.android.sc.rank;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity.Rank;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity.RankClient;
import com.bignerdranch.android.sc.user.UserActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class MonthRankFragment extends Fragment {

    private List<Rank> mList;
    private TextView n1, n2, n3, n4, n5, o1, o2, o3, o4, o5;
    private ImageView mExchange;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_rank, container, false);

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://39.102.42.156:2333/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                RankClient client = retrofit.create(RankClient.class);
                Call<List<Rank>> call = client.list("month");

                call.enqueue(new Callback<List<Rank>>() {

                    @Override
                    public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                        mList = response.body();

                        init();
                    }

                    @Override
                    public void onFailure(Call<List<Rank>> call, Throwable t) {

                    }
                });


        return view;
    }

    private void init() {

        n1 = n1.findViewById(R.id.m_first_n);
        n1.setText(mList.get(0).getId());

        n2 = n2.findViewById(R.id.m_first_n);
        n2.setText(mList.get(1).getId());

        n3 = n3.findViewById(R.id.m_first_n);
        n3.setText(mList.get(2).getId());


        n4 = n4.findViewById(R.id.m_first_n);
        n4.setText(mList.get(3).getId());

        n5 = n5.findViewById(R.id.m_first_n);
        n5.setText(mList.get(4).getId());


        o1 = o1.findViewById(R.id.m_first_n);
        o1.setText(mList.get(0).getNumber());


        o2 = o2.findViewById(R.id.m_first_n);
        o2.setText(mList.get(1).getNumber());


        o3 = o3.findViewById(R.id.m_first_n);
        o3.setText(mList.get(2).getNumber());

        o4 = o4.findViewById(R.id.m_first_n);
        o4.setText(mList.get(3).getNumber());


        o5 = o5.findViewById(R.id.m_first_n);
        o5.setText(mList.get(4).getNumber());

        mExchange = mExchange.findViewById(R.id.exchange);
        mExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public void showDialog(){
        final EditText editText = new EditText(getActivity());
        editText.setHint("上升排名：");
        final TextView textView = new TextView(getActivity());
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(getActivity());
        inputDialog.setTitle("兑换排名").setView(editText).setView(textView);
        inputDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText() != null) {
                    int number;
                    number = Integer.parseInt(editText.getText().toString());
                    textView.setText("所需金币：" + number);

                }
        }
       });
    }

    public void privateDialog() {
        DialogFragment newFragment = new PrivateDialog();
        newFragment.show(getFragmentManager(), "wrong");
    }

    }
