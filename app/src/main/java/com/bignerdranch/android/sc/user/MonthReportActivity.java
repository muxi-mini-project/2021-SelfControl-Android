package com.bignerdranch.android.sc.user;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

<<<<<<< HEAD

=======
>>>>>>> 5d73960e30223c996514c00cc349b30578898273
public class MonthReportActivity extends StatusBar {
    private ImageButton mBack;
    private TextView dk1_cs, dk2_cs, dk3_cs, dk4_cs, dk5_cs, dk1_y,
            dk2_y, dk3_y, dk4_y, dk5_y, dk1_lb, dk2_lb, dk3_lb, dk4_lb, dk5_lb;
    private ImageView dk1, dk2, dk3, dk4, dk5;
    private List<myPunch> mList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.month_report);
        new Thread() {
            @Override
            public void run() {
                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl("http://39.102.42.156:2333/api/v1/")
                        .addConverterFactory(GsonConverterFactory.create());

                Retrofit retrofit = builder.build();

                myPunchAPI client = retrofit.create(myPunchAPI.class);
                Call<List<myPunch>> call = client.getMyPunch();

                call.enqueue(new Callback<List<myPunch>>() {

                    @Override
                    public void onResponse(Call<List<myPunch>> call, Response<List<myPunch>> response) {
                        mList = response.body();
                        init();
                    }

                    @Override
                    public void onFailure(Call<List<myPunch>> call, Throwable t) {

                    }
                });
            }
        }.start();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void init() {
        mBack = findViewById(R.id.month_report_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dk1_cs = findViewById(R.id.dk1_cs);
        dk2_cs = findViewById(R.id.dk2_cs);
        dk3_cs = findViewById(R.id.dk3_cs);
        dk4_cs = findViewById(R.id.dk4_cs);
        dk5_cs = findViewById(R.id.dk5_cs);
        dk1_y = findViewById(R.id.dk1_y);
        dk2_y = findViewById(R.id.dk2_y);
        dk3_y = findViewById(R.id.dk3_y);
        dk4_y = findViewById(R.id.dk4_y);
        dk5_y = findViewById(R.id.dk5_y);
        dk1_lb = findViewById(R.id.dk1_lb);
        dk2_lb = findViewById(R.id.dk2_lb);
        dk3_lb = findViewById(R.id.dk3_lb);
        dk4_lb = findViewById(R.id.dk4_lb);
        dk5_lb = findViewById(R.id.dk5_lb);
        dk1 = findViewById(R.id.dk1_iv);
        dk2 = findViewById(R.id.dk2_iv);
        dk3 = findViewById(R.id.dk3_iv);
        dk4 = findViewById(R.id.dk4_iv);
        dk5 = findViewById(R.id.dk5_iv);


        if(mList.size() == 1){
            dk1_cs.setText(mList.get(0).getNumber());
            dk1_y.setText("次");
            dk1_lb.setText(mList.get(0).getTitle());
            dk1.setBackgroundResource(R.drawable.shape_yuanjiao_huise);
        }
        if(mList.size() == 2){

            dk1_cs.setText(mList.get(0).getNumber());
            dk1_y.setText("次");
            dk1_lb.setText(mList.get(0).getTitle());
            dk1.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk2_cs.setText(mList.get(1).getNumber());
            dk2_y.setText("次");
            dk2_lb.setText(mList.get(1).getTitle());
            dk2.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

        }
        if(mList.size() == 3){

            dk1_cs.setText(mList.get(0).getNumber());
            dk1_y.setText("次");
            dk1_lb.setText(mList.get(0).getTitle());
            dk1.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk2_cs.setText(mList.get(1).getNumber());
            dk2_y.setText("次");
            dk2_lb.setText(mList.get(1).getTitle());
            dk2.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk3_cs.setText(mList.get(2).getNumber());
            dk3_y.setText("次");
            dk3_lb.setText(mList.get(2).getTitle());
            dk3.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

        }
        if(mList.size() == 4){

            dk1_cs.setText(mList.get(0).getNumber());
            dk1_y.setText("次");
            dk1_lb.setText(mList.get(0).getTitle());
            dk1.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk2_cs.setText(mList.get(1).getNumber());
            dk2_y.setText("次");
            dk2_lb.setText(mList.get(1).getTitle());
            dk2.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk3_cs.setText(mList.get(2).getNumber());
            dk3_y.setText("次");
            dk3_lb.setText(mList.get(2).getTitle());
            dk3.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk4_cs.setText(mList.get(3).getNumber());
            dk4_y.setText("次");
            dk4_lb.setText(mList.get(3).getTitle());
            dk4.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

        }
        if(mList.size() == 5){

            dk1_cs.setText(mList.get(0).getNumber());
            dk1_y.setText("次");
            dk1_lb.setText(mList.get(0).getTitle());
            dk1.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk2_cs.setText(mList.get(1).getNumber());
            dk2_y.setText("次");
            dk2_lb.setText(mList.get(1).getTitle());
            dk2.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk3_cs.setText(mList.get(2).getNumber());
            dk3_y.setText("次");
            dk3_lb.setText(mList.get(2).getTitle());
            dk3.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk4_cs.setText(mList.get(3).getNumber());
            dk4_y.setText("次");
            dk4_lb.setText(mList.get(3).getTitle());
            dk4.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

            dk5_cs.setText(mList.get(4).getNumber());
            dk5_y.setText("次");
            dk5_lb.setText(mList.get(4).getTitle());
            dk5.setBackgroundResource(R.drawable.shape_yuanjiao_huise);

        }

    }

    public class myPunch {
        private int number;
        private String title;

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

    }

    public interface myPunchAPI {
        @GET("user/history")
        @Headers("")
        Call<List<myPunch>> getMyPunch();
    }
}
