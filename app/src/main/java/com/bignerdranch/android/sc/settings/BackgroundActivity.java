
package com.bignerdranch.android.sc.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.UserClient;

import java.util.List;

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
    private int f1 = 0, f2 = 0, f3 = 0, f4 = 0, f5 = 0, f6 = 0;

    private User mUser;
    private int mCoin;
    private List<Background> mBackgroundList;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.backgroud_theme);

        init();
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }



    private void init() {

        mBack = (ImageButton) findViewById(R.id.background_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                request();

                f1 = 0;
                f2 = 1;
                f3 = 0;
                f4 = 0;
                f5 = 0;
                f6 = 0;
                updateView();
            }
        });
        mTheme3 = findViewById(R.id.theme3);
        mTheme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 0;
                f2 = 0;
                f3 = 1;
                f4 = 0;
                f5 = 0;
                f6 = 0;
                updateView();
            }
        });
        mTheme4 = (ImageView) findViewById(R.id.theme4);
        mTheme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 0;
                f2 = 0;
                f3 = 0;
                f4 = 1;
                f5 = 0;
                f6 = 0;
                updateView();
            }
        });
        mTheme5 = (ImageView) findViewById(R.id.theme5);
        mTheme5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 0;
                f2 = 0;
                f3 = 0;
                f4 = 0;
                f5 = 1;
                f6 = 0;
                updateView();
            }
        });
        mTheme6 = (ImageView) findViewById(R.id.theme6);
        mTheme6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f1 = 0;
                f2 = 0;
                f3 = 0;
                f4 = 0;
                f5 = 0;
                f6 = 1;
                updateView();
            }
        });

        mChoose1 = findViewById(R.id.choose1);
        mChoose2 = findViewById(R.id.choose2);
        mChoose3 = findViewById(R.id.choose3);
        mChoose4 = findViewById(R.id.choose4);
        mChoose5 = findViewById(R.id.choose5);
        mChoose6 = findViewById(R.id.choose6);

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
        @PUT
        Call<Background> buyBackground(@Body String id, @Header("token") String token);

        @GET
        Call<List<Background>> myBackground(@Header("token") String token);
    }

    public class Background {

        private int backdrop_id;
        private String picture_url;
        private int price;

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


    private void showNormalDialog() {

        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(BackgroundActivity.this);
        normalDialog.setTitle("兑换背景");
        normalDialog.setMessage("确定用金币兑换背景吗?");
        normalDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

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
    private void request(){


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        BackgroundAPI client = retrofit.create(BackgroundAPI.class);
        Call<List<Background>> call = client.myBackground(token);

        call.enqueue(new Callback<List<Background>>() {

            @Override
            public void onResponse(Call<List<Background>> call, Response<List<Background>> response) {
                mBackgroundList = response.body();
            }

            @Override
            public void onFailure(Call<List<Background>> call, Throwable t) {

            }

        });


        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit1 = builder.build();
        UserClient client1 = retrofit.create(UserClient.class);
        Call<User> call1 = client1.mUser(token);

        call1.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mUser = response.body();
                mCoin = mUser.getGold();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }
}
