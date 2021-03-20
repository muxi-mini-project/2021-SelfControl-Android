package com.bignerdranch.android.sc.rank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity.Rank;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeekRankFragment extends Fragment {

    private List<Rank> mList;
    private TextView n1,n2,n3,n4,n5,o1,o2,o3,o4,o5;

    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_rank, container, false);

        new Thread() {
            @Override
            public void run() {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("https://124.71.184.107:2333/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                RankBackgroundActivity.RankClient client = retrofit.create(RankBackgroundActivity.RankClient.class);
                Call<List<Rank>> call = client.list("week");

                call.enqueue(new Callback<List<Rank>>() {

                    @Override
                    public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                        mList = response.body();
                    }

                    @Override
                    public void onFailure(Call<List<Rank>> call, Throwable t) {

                    }
                });
            }
        }.start();


        init();
        return view;
    }
    private void init(){

        n1 = n1.findViewById(R.id.w_first_n);
        n1.setText(mList.get(0).getId());

        n2 = n2.findViewById(R.id.w_second_n);
        n2.setText(mList.get(1).getId());


        n3 = n3.findViewById(R.id.w_third_n);
        n3.setText(mList.get(2).getId());


        n4 = n4.findViewById(R.id.w_fourth_n);
        n4.setText(mList.get(3).getId());


        n5 = n5.findViewById(R.id.w_fifth_n);
        n5.setText(mList.get(4).getId());


        o1 = o1.findViewById(R.id.w_first_o);
        o1.setText(mList.get(0).getNumber());


        o2 = o2.findViewById(R.id.w_second_o);
        o2.setText(mList.get(1).getNumber());


        o3 = o3.findViewById(R.id.w_third_o);
        o3.setText(mList.get(2).getNumber());


        o4 = o4.findViewById(R.id.w_fourth_o);
        o4.setText(mList.get(3).getNumber());


        o5 = o5.findViewById(R.id.w_fifth_o);
        o5.setText(mList.get(4).getNumber());
    }
}
