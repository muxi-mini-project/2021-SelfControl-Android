package com.bignerdranch.android.sc.rank;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity.Rank;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity.RankClient;

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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_rank, container, false);

        new Thread() {
            @Override
            public void run() {
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
                        if (mList.size() == 1) {
                            n1.setText(mList.get(0).getId());
                            o1.setText(mList.get(0).getNumber());
                        }
                        if (mList.size() == 2) {
                            n1.setText(mList.get(0).getId());
                            o1.setText(mList.get(0).getNumber());

                            n2.setText(mList.get(1).getId());
                            o2.setText(mList.get(1).getNumber());
                        }
                        if (mList.size() == 3) {
                            n1.setText(mList.get(0).getId());
                            o1.setText(mList.get(0).getNumber());

                            n2.setText(mList.get(1).getId());
                            o2.setText(mList.get(1).getNumber());

                            n3.setText(mList.get(2).getId());
                            o3.setText(mList.get(2).getNumber());
                        }
                        if (mList.size() == 4) {
                            n1.setText(mList.get(0).getId());
                            o1.setText(mList.get(0).getNumber());

                            n2.setText(mList.get(1).getId());
                            o2.setText(mList.get(1).getNumber());

                            n3.setText(mList.get(2).getId());
                            o3.setText(mList.get(2).getNumber());

                            n4.setText(mList.get(3).getId());
                            o4.setText(mList.get(3).getNumber());
                        }
                        if (mList.size() == 5) {
                            n1.setText(mList.get(0).getId());
                            o1.setText(mList.get(0).getNumber());

                            n2.setText(mList.get(1).getId());
                            o2.setText(mList.get(1).getNumber());

                            n3.setText(mList.get(2).getId());
                            o3.setText(mList.get(2).getNumber());

                            n4.setText(mList.get(3).getId());
                            o4.setText(mList.get(3).getNumber());

                            n5.setText(mList.get(4).getId());
                            o5.setText(mList.get(4).getNumber());
                        }

                        init();
                    }

                    @Override
                    public void onFailure(Call<List<Rank>> call, Throwable t) {

                    }
                });
            }
        }.start();

        return view;
    }

    private void init() {

        n1 = n1.findViewById(R.id.m_first_n);
        n2 = n2.findViewById(R.id.m_first_n);
        n3 = n3.findViewById(R.id.m_first_n);
        n4 = n4.findViewById(R.id.m_first_n);
        n5 = n5.findViewById(R.id.m_first_n);

        o1 = o1.findViewById(R.id.m_first_n);
        o2 = o2.findViewById(R.id.m_first_n);
        o3 = o3.findViewById(R.id.m_first_n);
        o4 = o4.findViewById(R.id.m_first_n);
        o5 = o5.findViewById(R.id.m_first_n);

    }

    public void privateDialog() {
        DialogFragment newFragment = new PrivateDialog();
        newFragment.show(getFragmentManager(), "wrong");
    }
}
