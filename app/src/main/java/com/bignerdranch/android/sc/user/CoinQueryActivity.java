package com.bignerdranch.android.sc.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.baseUrl;
import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class CoinQueryActivity extends StatusBar {

    private ImageButton mBack;
    private TextView mCoin, czsj1, czsj2, czsj3, bhsl1, bhsl2, bhsl3, czyy1, czyy2, czyy3;
    private User mUser;
    private List<GoldHistory> mList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_query);
        init();


        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    private void init() {
        mBack = findViewById(R.id.coin_query_back);
        mCoin = findViewById(R.id.dangqianjinbi);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(CoinQueryActivity.this,UserActivity.class);
                //startActivity(intent);
                finish();

            }
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.mUser(token);
//        Log.e("token", token);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mUser = response.body();
//                Log.d("Coin",mUser.toString());
                if (mUser != null)
                    mCoin.setText(String.valueOf(mUser.getGold()));
                else {
                    Log.e("user", "null");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Coin", "failure");
            }
        });


        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder.build();

        GoldHistoryAPI client1 = retrofit.create(GoldHistoryAPI.class);
        Call<List<GoldHistory>> call1 = client1.getGoldHistory(token);

        call1.enqueue(new Callback<List<GoldHistory>>() {

            @Override
            public void onResponse
                    (Call<List<GoldHistory>> call, Response<List<GoldHistory>> response) {
                mList = response.body();

                czsj1 = findViewById(R.id.czsj1);
                czsj1.setText(mList.get(0).getTime());

                czsj2 = findViewById(R.id.czsj2);
                czsj2.setText(mList.get(1).getTime());

                czsj3 = findViewById(R.id.czsj3);
                czsj3.setText(mList.get(2).getTime());

                bhsl1 = findViewById(R.id.bhsl1);
                bhsl1.setText(mList.get(0).getResidual_number());

                bhsl2 = findViewById(R.id.bhsl2);
                bhsl2.setText(mList.get(1).getResidual_number());

                bhsl3 = findViewById(R.id.bhsl3);
                bhsl3.setText(mList.get(2).getResidual_number());

                czyy1 = findViewById(R.id.czyy1);
                czyy1.setText(mList.get(0).getReason());

                czyy2 = findViewById(R.id.czyy2);
                czyy2.setText(mList.get(1).getReason());

                czyy3 = findViewById(R.id.czyy3);
                czyy3.setText(mList.get(2).getReason());

            }

            @Override
            public void onFailure(Call<List<GoldHistory>> call, Throwable t) {

            }
        });
    }

    public class GoldHistory {

        private int change_number;
        private String reason;
        private int residual_number;
        private String student_id;
        private String time;

        public void setChange_number(int change_number) {
            this.change_number = change_number;
        }

        public int getChange_number() {
            return change_number;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getReason() {
            return reason;
        }

        public void setResidual_number(int residual_number) {
            this.residual_number = residual_number;
        }

        public int getResidual_number() {
            return residual_number;
        }

        public void setStudent_id(String student_id) {
            this.student_id = student_id;
        }

        public String getStudent_id() {
            return student_id;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTime() {
            return time;
        }

    }

}


