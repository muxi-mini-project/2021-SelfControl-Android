package com.bignerdranch.android.sc.rank;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import com.bignerdranch.android.sc.user.UserActivity;

import java.util.List;

import okhttp3.OkHttpClient;

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

        n1 = view.findViewById(R.id.m_first_n);
        n2 = view.findViewById(R.id.m_first_n);
        n3 = view.findViewById(R.id.m_first_n);
        n4 = view.findViewById(R.id.m_first_n);
        n5 = view.findViewById(R.id.m_first_n);

        o1 = view.findViewById(R.id.m_first_n);
        o2 = view.findViewById(R.id.m_first_n);
        o3 = view.findViewById(R.id.m_first_n);
        o4 = view.findViewById(R.id.m_first_n);
        o5 = view.findViewById(R.id.m_first_n);

        mExchange = view.findViewById(R.id.exchange);
        mExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });



        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        MonthRankClient client = retrofit.create(MonthRankClient.class);
        Call<List<Rank>> call = client.getMonth();

        call.enqueue(new Callback<List<Rank>>() {

            @Override
            public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                mList = response.body();

                if (mList == null) {
                    Toast.makeText(getActivity(), "当前暂无打卡信息哟", Toast.LENGTH_SHORT).show();
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


    public void onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(R.id.rank_editText);
        builder.setView(R.id.rank_textView);
        builder.setView(R.id.rank_yes).setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        });
        builder.setView(R.id.rank_no);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.rank_dialog, null));
        // Add action buttons

        builder.show();
    }

    public void showDialog() {
        Integer number1, number2;
        final EditText editText = new EditText(getActivity());
        editText.setHint("上升排名：");
        final TextView textView = new TextView(getActivity());
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(getActivity());
        inputDialog.setTitle("兑换排名");
        inputDialog.setView(editText);
        inputDialog.setView(textView);
        if (editText.getText().toString() != null) {
            number1 = Integer.parseInt(editText.getText().toString());
            number2 = number1 * 2 + 50;
            textView.setText("所需金币：" + number2);
        }
        inputDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (editText.getText() != null) {

                }
            }
        });
        inputDialog.show();
    }

    public void privateDialog() {
        DialogFragment newFragment = new PrivateDialog();
        newFragment.show(getFragmentManager(), "wrong");
    }

    public interface MonthRankClient {
        @GET("api/v1/lists/month/")
        Call<List<Rank>> getMonth();
    }

}
