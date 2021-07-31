package com.bignerdranch.android.sc.user.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.GetBackdropAPI;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.model.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class RankQueryActivity extends StatusBar {
    private ImageButton mBack;
    private TextView mName, mMonthFormer, mMonthAfter, mWeekFormer, mWeekAfter;
    private User mUser1,mUser2;
    private Rank mRank;
    private ConstraintLayout mLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_query);
        init();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void init() {
        mLayout = findViewById(R.id.rank_query_layout);
        request();

        mBack = findViewById(R.id.rank_query_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        mName = findViewById(R.id.rank_query_name);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.mUser(token);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mUser1 = response.body();
                if (mUser1 != null)
                    mName.setText(String.valueOf(mUser1.getName()));

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Coin", "failure");
            }
        });

        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder.build();

        myRankAPI client1 = retrofit.create(myRankAPI.class);
        Call<Rank> call1 = client1.getMyRank(token);

        call1.enqueue(new Callback<Rank>() {

            @Override
            public void onResponse(Call<Rank> call, Response<Rank> response) {
                mRank = response.body();
                if (mRank != null) {
                    mMonthFormer.setText(String.valueOf(mRank.getMonth_former()));
                    mMonthAfter.setText(String.valueOf(mRank.getMonth_after()));
                    mWeekFormer.setText(String.valueOf(mRank.getWeek_former()));
                    mWeekAfter.setText(String.valueOf(mRank.getWeek_after()));


                }
            }

            @Override
            public void onFailure(Call<Rank> call, Throwable t) {

            }
        });
        mMonthFormer = findViewById(R.id.ypm_start);


        mMonthAfter = findViewById(R.id.ypm_hl);

        mWeekFormer = findViewById(R.id.zpm_start);


        mWeekAfter = findViewById(R.id.zpm_hl);

    }

    public class Rank {
        private int month_after;

        private int month_former;

        private String student_id;

        private int week_after;

        private int week_former;

        private Rank(int month_after, int month_former, int week_after, int week_former) {

            this.month_after = month_after;
            this.month_former = month_former;
            this.week_former = week_former;
            this.week_after = week_after;
        }

        public void setMonth_after(int month_after) {
            this.month_after = month_after;
        }

        public int getMonth_after() {
            return this.month_after;
        }

        public void setMonth_former(int month_former) {
            this.month_former = month_former;
        }

        public int getMonth_former() {
            return this.month_former;
        }

        public void setStudent_id(String student_id) {
            this.student_id = student_id;
        }

        public String getStudent_id() {
            return this.student_id;
        }

        public void setWeek_after(int week_after) {
            this.week_after = week_after;
        }

        public int getWeek_after() {
            return this.week_after;
        }

        public void setWeek_former(int week_former) {
            this.week_former = week_former;
        }

        public int getWeek_former() {
            return this.week_former;
        }

    }

    public interface myRankAPI {
        @GET("list/history")
        Call<Rank> getMyRank(@Header("token") String token);
    }
    private void request() {
        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder1.build();
        GetBackdropAPI client1 = retrofit1.create(GetBackdropAPI.class);
        Call<User> call1 = client1.getCurrentBackdrop(token);

        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mUser2 = response.body();
                if (mUser2 != null) {
                    if (mUser2.getCurrent_backdrop() == 6) {
                        mLayout.setBackgroundResource(R.color.purple);
                    }
                    if (mUser2.getCurrent_backdrop() == 1) {
                        mLayout.setBackgroundResource(R.color.theme2);
                    }
                    if (mUser2.getCurrent_backdrop() == 2) {
                        mLayout.setBackgroundResource(R.color.theme3);
                    }
                    if (mUser2.getCurrent_backdrop() == 3) {
                        mLayout.setBackgroundResource(R.mipmap.theme_31);
                    }
                    if (mUser2.getCurrent_backdrop() == 4) {
                        mLayout.setBackgroundResource(R.mipmap.theme_41);
                    }
                    if (mUser2.getCurrent_backdrop() == 5) {
                        mLayout.setBackgroundResource(R.mipmap.theme_51);
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}

