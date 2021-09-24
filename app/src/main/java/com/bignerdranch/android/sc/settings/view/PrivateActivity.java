package com.bignerdranch.android.sc.settings.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.settings.contract.PrivateContract;
import com.bignerdranch.android.sc.settings.presenter.PrivatePresenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class PrivateActivity extends StatusBar implements PrivateContract.VP,View.OnClickListener{

    private ImageButton mBack;
    private Button mTrue, mFalse;
    private User mUser;
    private ConstraintLayout mLayout;
    private PrivatePresenter mP = new PrivatePresenter();


    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.choose_open);
        mP.bindView(this);
        initView();
        requestBg();
        System.out.println(token);

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    public void initView(){
        mLayout = findViewById(R.id.choose_layout);
        mBack = findViewById(R.id.private_back);
        mBack.setOnClickListener(this);
        mTrue = findViewById(R.id.true_button);
        mTrue.setOnClickListener(this);
        mFalse = findViewById(R.id.false_button);
        mFalse.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.private_back:
                finish();
                break;
            case R.id.true_button:
                trueRequest(token);
                break;
            case R.id.false_button:
                falseRequest(token);
                break;

        }
    }

    @Override
    public void trueRequest(String token) {
        mP.trueRequest(token);
    }

    @Override
    public void falseRequest(String token) {
        mP.falseRequest(token);
    }

    @Override
    public void success() {
        Toast.makeText(PrivateActivity.this,"修改成功！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail() {
        Toast.makeText(PrivateActivity.this,"出错啦！请检查网络设置！",Toast.LENGTH_SHORT).show();
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
}
