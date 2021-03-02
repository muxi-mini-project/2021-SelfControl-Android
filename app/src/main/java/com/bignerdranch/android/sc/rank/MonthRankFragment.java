package com.bignerdranch.android.sc.rank;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity.RankClient;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity.RankList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MonthRankFragment extends Fragment {

    private List<RankList> mList;
    private TextView n1,n2,n3,n4,n5,o1,o2,o3,o4,o5;
    private int num1,num2,num3,num4,num5;
    private String name1,name2,name3,name4,name5;

    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_rank, container, false);

        new Thread() {
            @Override
            public void run() {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://124.71.184.107/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                RankClient client = retrofit.create(RankClient.class);
                Call<List<RankList>> call = client.list("month");

                call.enqueue(new Callback<List<RankList>>() {

                    @Override
                    public void onResponse(Call<List<RankList>> call, Response<List<RankList>> response) {
                        mList = response.body();
                    }

                    @Override
                    public void onFailure(Call<List<RankList>> call, Throwable t) {
                        
                    }
                });
            }
        }.start();

        //num1 = mList.

        init();



        return view;
    }
    private void init(){

        n1 = n1.findViewById(R.id.m_first_n);
        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n1.setText();
            }
        });

        n2 = n2.findViewById(R.id.m_first_n);
        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n2.setText();
            }
        });

        n3 = n3.findViewById(R.id.m_first_n);
        n3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n3.setText();
            }
        });

        n4 = n4.findViewById(R.id.m_first_n);
        n4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n4.setText();
            }
        });

        n5 = n5.findViewById(R.id.m_first_n);
        n5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n5.setText();
            }
        });

        o1 = o1.findViewById(R.id.m_first_n);
        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o1.setText();
            }
        });

        o2 = o2.findViewById(R.id.m_first_n);
        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o2.setText();
            }
        });

        o3 = o3.findViewById(R.id.m_first_n);
        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o3.setText();
            }
        });

        o4 = o4.findViewById(R.id.m_first_n);
        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o4.setText();
            }
        });

        o5 = o5.findViewById(R.id.m_first_n);
        o5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                o5.setText();
            }
        });


    }

}
