package com.bignerdranch.android.sc.clockpage.flower;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.Data;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.clockpage.ClockActivity;
import com.bignerdranch.android.sc.label.LabelPagerActivity;
import com.bignerdranch.android.sc.label.PunchAPI;
import com.bignerdranch.android.sc.login.LoginActivity;
import com.bignerdranch.android.sc.punch.MyPunchActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;


public class FlowerFragment extends Fragment {
    private TextView mTextView;
    private ImageButton unflower;
    private View mView;
    private String text = "";
    private int date;
    private Data mData;


    public void FlowerFragment(String text, int date) {
        this.date = date;
        this.text = text;
    }

//    public void FlowerFragment(ImageButton unflower){
//        this.unflower = unflower;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.flower, container, false);

        mTextView = view.findViewById(R.id.today);
        mView = view.findViewById(R.id.line);
        unflower = view.findViewById(R.id.unflower);
        mTextView.setText(text);

        unflower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    Intent intent = new Intent(getActivity(), MyPunchActivity.class);
                    intent.putExtra("data",date);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    private void ifpunchcomplete() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client3 = retrofit.create(PunchAPI.class);
        Call<Data> call = client3.ifpunchcomplete(token);

        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {

                mData = response.body();
                mData.getData();
                if (mData.getData() > 0) {

                }

            }

            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }

}
