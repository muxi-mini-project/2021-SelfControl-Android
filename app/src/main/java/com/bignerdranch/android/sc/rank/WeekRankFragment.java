package com.bignerdranch.android.sc.rank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.rank.RankBackgroundActivity.Rank;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class WeekRankFragment extends Fragment {

    private List<Rank> mList;
    private TextView n1, n2, n3, n4, n5, o1, o2, o3, o4, o5;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.week_rank, container, false);

        n1 = view.findViewById(R.id.w_first_n);
        n2 = view.findViewById(R.id.w_second_n);
        n3 = view.findViewById(R.id.w_third_n);
        n4 = view.findViewById(R.id.w_fourth_n);
        n5 = view.findViewById(R.id.w_fifth_n);

        o1 = view.findViewById(R.id.w_first_o);
        o2 = view.findViewById(R.id.w_second_o);
        o3 = view.findViewById(R.id.w_third_o);
        o4 = view.findViewById(R.id.w_fourth_o);
        o5 = view.findViewById(R.id.w_fifth_o);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        WeekRankClient client = retrofit.create(WeekRankClient.class);
        Call<List<Rank>> call = client.getWeek();

        call.enqueue(new Callback<List<Rank>>() {

            @Override
            public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                mList = response.body();

                if (mList == null) {
                    Toast.makeText(getActivity(), "当前暂无排行榜信息！", Toast.LENGTH_SHORT).show();
                }
                if (mList != null) {
                    if (mList.size() == 1) {
                        n1.setText(mList.get(0).getName());
                        o1.setText(String.valueOf(mList.get(0).getNumber()));
                    }
                    if (mList.size() == 2) {
                        n1.setText(mList.get(0).getName());
                        o1.setText(String.valueOf(mList.get(0).getNumber()));

                        n2.setText(mList.get(1).getName());
                        o2.setText(String.valueOf(mList.get(1).getNumber()));
                    }
                    if (mList.size() == 3) {
                        n1.setText(mList.get(0).getName());
                        o1.setText(String.valueOf(mList.get(0).getNumber()));

                        n2.setText(mList.get(1).getName());
                        o2.setText(String.valueOf(mList.get(1).getNumber()));

                        n3.setText(mList.get(2).getName());
                        o3.setText(String.valueOf(mList.get(2).getNumber()));
                    }
                    if (mList.size() == 4) {
                        n1.setText(mList.get(0).getName());
                        o1.setText(String.valueOf(mList.get(0).getNumber()));

                        n2.setText(mList.get(1).getName());
                        o2.setText(String.valueOf(mList.get(1).getNumber()));

                        n3.setText(mList.get(2).getName());
                        o3.setText(String.valueOf(mList.get(2).getNumber()));

                        n4.setText(mList.get(3).getName());
                        o4.setText(String.valueOf(mList.get(3).getNumber()));
                    }
                    if (mList.size() == 5) {
                        n1.setText(mList.get(0).getName());
                        o1.setText(String.valueOf(mList.get(0).getNumber()));

                        n2.setText(mList.get(1).getName());
                        o2.setText(String.valueOf(mList.get(1).getNumber()));

                        n3.setText(mList.get(2).getName());
                        o3.setText(String.valueOf(mList.get(2).getNumber()));

                        n4.setText(mList.get(3).getName());
                        o4.setText(String.valueOf(mList.get(3).getNumber()));

                        n5.setText(mList.get(4).getName());
                        o5.setText(String.valueOf(mList.get(4).getNumber()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Rank>> call, Throwable t) {

            }
        });

        return view;
    }

    public interface WeekRankClient {
        @GET("api/v1/lists/week/")
        Call<List<Rank>> getWeek();
    }


}
