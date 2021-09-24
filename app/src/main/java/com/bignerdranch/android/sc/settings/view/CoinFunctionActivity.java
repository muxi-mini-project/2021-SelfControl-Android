package com.bignerdranch.android.sc.settings.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.net.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bignerdranch.android.sc.login.LoginActivity.token;


public class CoinFunctionActivity extends StatusBar {

    private ImageButton mBack;
    private User.DataDTO mUser;
    private ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_function);

        mLayout = findViewById(R.id.coin_function_layout);
        requestBg();


        mBack = findViewById(R.id.coin_back);
        mBack.setOnClickListener(v -> finish());
        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    public void requestBg() {
        NetUtil.getInstance().getApi().userInfo(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        if (user != null) {
                            if (user.getData().getCurrent_backdrop() == 1) {
                                mLayout.setBackgroundResource(R.mipmap.background_default);
                            }
                            if (user.getData().getCurrent_backdrop() == 2) {
                                mLayout.setBackgroundResource(R.mipmap.theme_1);
                            }
                            if (user.getData().getCurrent_backdrop() == 3) {
                                mLayout.setBackgroundResource(R.mipmap.theme_2);
                            }
                            if (user.getData().getCurrent_backdrop() == 4) {
                                mLayout.setBackgroundResource(R.mipmap.theme_3);
                            }
                            if (user.getData().getCurrent_backdrop() == 5) {
                                mLayout.setBackgroundResource(R.mipmap.theme_4);
                            }
                            if (user.getData().getCurrent_backdrop() == 6) {
                                mLayout.setBackgroundResource(R.mipmap.theme_5);
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onResume() {

        super.onResume();
        requestBg();
    }
}
