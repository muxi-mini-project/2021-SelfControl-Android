package com.bignerdranch.android.sc.clockpage.flower;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bignerdranch.android.sc.user.Bean.Data;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.label.PunchAPI;
import com.bignerdranch.android.sc.punch.LabelPunch;

import java.util.List;

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
    private Bundle mBundle;
    private List<LabelPunch> mList;

    public void FlowerFragment(String text) {
        this.text = text;
    }



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
        mBundle = this.getArguments();
        if(mBundle != null) {date = mBundle.getInt("date");
        ifpunchcomplete();}
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
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        PunchAPI client3 = retrofit.create(PunchAPI.class);
        Call<List<LabelPunch>> call = client3.getDayPunch(token,date);

        call.enqueue(new Callback<List<LabelPunch>>() {
            @Override
            public void onResponse(Call<List<LabelPunch>> call, Response<List<LabelPunch>> response) {
                mList = response.body();
                if( mList != null ){
                    int flag = 1;

                    for( int i = 0 ; i < mList.size() ; i++ ) {
                        int j = mList.get(i).getNumber();
                        if (j == 0) {
                            flag = 0;
                            break;
                        }
                    }
                    if(flag == 1)
                        unflower.setBackgroundResource(R.mipmap.done);
                }
            }

            @Override
            public void onFailure(Call<List<LabelPunch>> call, Throwable t) {

            }

        });
    }

}
