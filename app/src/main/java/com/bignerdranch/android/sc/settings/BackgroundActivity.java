package com.bignerdranch.android.sc.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.UserClient;

import java.util.List;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class BackgroundActivity extends StatusBar {

    private ImageButton mBack;
    private ImageView mTheme1, mTheme2, mTheme3, mTheme4, mTheme5, mTheme6;
    private ImageView mChoose1, mChoose2, mChoose3, mChoose4, mChoose5, mChoose6;
    private int f1 = 1, f2 = 0, f3 = 0, f4 = 0, f5 = 0, f6 = 0;
    private int b_1,b_2,b_3,b_4,b_5,b_6;

    private myBackground mMyBackground;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.backgroud_theme);

        init();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }


    private void init() {

        mBack = findViewById(R.id.background_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mChoose1 = findViewById(R.id.choose1);
        mChoose2 = findViewById(R.id.choose2);
        mChoose3 = findViewById(R.id.choose3);
        mChoose4 = findViewById(R.id.choose4);
        mChoose5 = findViewById(R.id.choose5);
        mChoose6 = findViewById(R.id.choose6);

        request1();
        updateView();

        mTheme1 = findViewById(R.id.theme1);
        mTheme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                f1 = 1;
                f2 = 0;
                f3 = 0;
                f4 = 0;
                f5 = 0;
                f6 = 0;
                updateView();

            }
        });
        mTheme2 = findViewById(R.id.theme2);
        mTheme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request1();
                if(b_2 == 0){
                    showNormalDialog(3);
                    request1();
                    if(b_2 != 0 ){
                        f1 = 0;
                        f2 = 1;
                        f3 = 0;
                        f4 = 0;
                        f5 = 0;
                        f6 = 0;
                    }
                }else{

                    f1 = 0;
                    f2 = 1;
                    f3 = 0;
                    f4 = 0;
                    f5 = 0;
                    f6 = 0;
                }
                updateView();

            }
        });
        mTheme3 = findViewById(R.id.theme3);
        mTheme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request1();
                if(b_3 == 0){
                    showNormalDialog(3);
                    request1();
                    if(b_3 != 0 ){
                        f1 = 0;
                        f2 = 0;
                        f3 = 1;
                        f4 = 0;
                        f5 = 0;
                        f6 = 0;
                    }
                }else{

                    f1 = 0;
                    f2 = 0;
                    f3 = 1;
                    f4 = 0;
                    f5 = 0;
                    f6 = 0;
                }
                updateView();
            }
        });
        mTheme4 = findViewById(R.id.theme4);
        mTheme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {request1();
                if(b_4 == 0){
                    showNormalDialog(4);
                    request1();
                    if(b_4 != 0 ){
                        f1 = 0;
                        f2 = 0;
                        f3 = 0;
                        f4 = 1;
                        f5 = 0;
                        f6 = 0;
                    }
                }else{
                    f1 = 0;
                    f2 = 0;
                    f3 = 0;
                    f4 = 1;
                    f5 = 0;
                    f6 = 0;
                }
                updateView();
            }
        });
        mTheme5 = findViewById(R.id.theme5);
        mTheme5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(b_5 == 0){
                    showNormalDialog(5);
                    request1();
                    if(b_5 != 0 ){
                        f1 = 0;
                        f2 = 0;
                        f3 = 0;
                        f4 = 0;
                        f5 = 1;
                        f6 = 0;
                    }
                }else{
                    f1 = 0;
                    f2 = 0;
                    f3 = 0;
                    f4 = 0;
                    f5 = 1;
                    f6 = 0;
                }
                updateView();
            }
        });
        mTheme6 = findViewById(R.id.theme6);
        mTheme6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b_6 == 0){
                    showNormalDialog(6);
                    request1();
                    if(b_6 != 0 ){
                        f1 = 0;
                        f2 = 0;
                        f3 = 0;
                        f4 = 0;
                        f5 = 1;
                        f6 = 0;
                    }
                }else{
                    f1 = 0;
                    f2 = 0;
                    f3 = 0;
                    f4 = 0;
                    f5 = 1;
                    f6 = 0;
                }

                updateView();
            }
        });



    }

    private void updateView() {
        if (f1 == 1) {
            mChoose1.setBackgroundResource(R.mipmap.choose);
            mChoose1.setAlpha(1f);
        } else if (f1 == 0) {
            mChoose1.setAlpha(0f);
        }
        if (f2 == 1) {
            mChoose2.setBackgroundResource(R.mipmap.choose);
            mChoose2.setAlpha(1f);
        } else if (f2 == 0) {
            mChoose2.setAlpha(0f);
        }
        if (f3 == 1) {
            mChoose3.setBackgroundResource(R.mipmap.choose);
            mChoose3.setAlpha(1f);
        } else if (f3 == 0) {
            mChoose3.setAlpha(0f);
        }
        if (f4 == 1) {
            mChoose4.setBackgroundResource(R.mipmap.choose);
            mChoose4.setAlpha(1f);
        } else if (f4 == 0) {
            mChoose4.setAlpha(0f);
        }
        if (f5 == 1) {
            mChoose5.setBackgroundResource(R.mipmap.choose);
            mChoose5.setAlpha(1f);
        } else if (f5 == 0) {
            mChoose5.setAlpha(0f);
        }
        if (f6 == 1) {
            mChoose6.setBackgroundResource(R.mipmap.choose);
            mChoose6.setAlpha(1f);
        } else if (f6 == 0) {
            mChoose6.setAlpha(0f);
        }

    }

    public interface BackgroundAPI {
        @PUT("backdrop")
        Call<Background> buyBackground(@Body Background mBackground, @Header("token") String token);
    }

    public interface myBackgroundAPI {

        @GET("backdrops")
        Call<myBackground> getMyBackground(@Header("token") String token);
    }

    public class myBackground {

        private int b_1;
        private int b_2;
        private int b_3;
        private int b_4;
        private int b_5;
        private int b_6;

        public myBackground(int b_1, int b_2, int b_3, int b_4, int b_5, int b_6) {
            this.b_1 = b_1;
            this.b_2 = b_2;
            this.b_3 = b_3;
            this.b_4 = b_4;
            this.b_5 = b_5;
            this.b_6 = b_6;
        }

        public void setB_1(int b_1) {
            this.b_1 = b_1;
        }

        public int getB_1() {
            return b_1;
        }

        public void setB_2(int b_2) {
            this.b_2 = b_2;
        }

        public int getB_2() {
            return b_2;
        }

        public void setB_3(int b_3) {
            this.b_3 = b_3;
        }

        public int getB_3() {
            return b_3;
        }

        public void setB_4(int b_4) {
            this.b_4 = b_4;
        }

        public int getB_4() {
            return b_4;
        }

        public void setB_5(int b_5) {
            this.b_5 = b_5;
        }

        public int getB_5() {
            return b_5;
        }

        public void setB_6(int b_6) {
            this.b_6 = b_6;
        }

        public int getB_6() {
            return b_6;
        }

    }

    public class Background {

        private int backdrop_id;
        private String picture_url;
        private int price;

        public Background(int backdrop_id){
            this.backdrop_id = backdrop_id;
        }

        public void setBackdrop_id(int backdrop_id) {
            this.backdrop_id = backdrop_id;
        }

        public int getBackdrop_id() {
            return backdrop_id;
        }

        public void setPicture_url(String picture_url) {
            this.picture_url = picture_url;
        }

        public String getPicture_url() {
            return picture_url;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getPrice() {
            return price;
        }
    }


    private void showNormalDialog(int id) {

        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(BackgroundActivity.this);
        normalDialog.setTitle("兑换背景");
        normalDialog.setMessage("确定用金币兑换背景吗?");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                request2(id);

            }
        });
        normalDialog.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // 显示
        normalDialog.show();
    }

    public void request1() {//获取拥有的背景

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build());

        Retrofit retrofit = builder.build();
        myBackgroundAPI client = retrofit.create(myBackgroundAPI.class);
        Call<myBackground> call = client.getMyBackground(token);

        call.enqueue(new Callback<myBackground>() {

            @Override
            public void onResponse(Call<myBackground> call, Response<myBackground> response) {
                b_1 = response.body().getB_1();
                b_2 = response.body().getB_2();
                b_3 = response.body().getB_3();
                b_4 = response.body().getB_4();
                b_5 = response.body().getB_5();
                b_6 = response.body().getB_6();

            }

            @Override
            public void onFailure(Call<myBackground> call, Throwable t) {

            }
        });

    }

    private void request2( int id) {

        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder1.build();
        BackgroundAPI client1 = retrofit1.create(BackgroundAPI.class);
        Call<Background> call1 = client1.buyBackground(new Background(id),token);

        call1.enqueue(new Callback<Background>() {

            @Override
            public void onResponse(Call<Background> call, Response<Background> response) {
                if(response.code() == 200){
                    Toast.makeText(BackgroundActivity.this,"兑换成功",Toast.LENGTH_SHORT).show();
                }
                else if(response.code() == 203){
                    Toast.makeText(BackgroundActivity.this,"金币不足！快去打卡赚金币吧~",Toast.LENGTH_SHORT).show();
                }
                else if(response.code() == 400){
                    Toast.makeText(BackgroundActivity.this,"出错啦！请稍后再试~",Toast.LENGTH_SHORT).show();
                }else if(response.code() == 401){
                    Toast.makeText(BackgroundActivity.this,"身份认证出错啦！",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(BackgroundActivity.this,"出错啦！稍后再试~",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Background> call, Throwable t) {
                Toast.makeText(BackgroundActivity.this,"出错啦！请检查网络连接~",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
