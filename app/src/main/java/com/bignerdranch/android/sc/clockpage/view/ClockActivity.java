package com.bignerdranch.android.sc.clockpage.view;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.clockpage.clockpresenter.FlowerPresenter;
import com.bignerdranch.android.sc.clockpage.clockpresenter.MainContract;
import com.bignerdranch.android.sc.clockpage.model.RemoteDataSource;
import com.bignerdranch.android.sc.clockpage.view.flower.FlowerFragment;
import com.bignerdranch.android.sc.clockpage.view.flower.FlowerFragmentPagerAdapter;
import com.bignerdranch.android.sc.clockpage.weekcalendar.CalendarAdapter;
import com.bignerdranch.android.sc.clockpage.weekcalendar.CalendarUtils;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.settings.view.SettingPageActivity;
import com.bignerdranch.android.sc.user.view.UserActivity;

import java.time.LocalDate;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.bignerdranch.android.sc.clockpage.weekcalendar.CalendarUtils.daysInWeekArray;
import static com.bignerdranch.android.sc.clockpage.weekcalendar.CalendarUtils.getLocalDate;
import static com.bignerdranch.android.sc.clockpage.weekcalendar.CalendarUtils.monthYearFromDate;

public class ClockActivity extends StatusBar implements CalendarAdapter.OnItemListener, MainContract.View {
    private TextView ticker;

    private ArrayList<FlowerFragment> fragments;
    private ViewPager mViewPager;
    private FlowerFragment mSunFlowerFragment;
    private FlowerFragment mMonFlowerFragment;
    private FlowerFragment mTueFlowerFragment;
    private FlowerFragment mWesFlowerFragment;
    private FlowerFragment mThuFlowerFragment;
    private FlowerFragment mFriFlowerFragment;
    private FlowerFragment mSatFlowerFragment;

    FragmentManager mFragmentManager;
    FragmentPagerAdapter mFragmentPagerAdapter;

    private ConstraintLayout mLayout;
    String token;

    private MainContract.Presenter mPresenter;
    private RecyclerView calendarRecyclerView;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_main_page);

        CalendarUtils.selectedDate = LocalDate.now();

        mPresenter = new FlowerPresenter(this, RemoteDataSource.getINSTANCE());

        initWidgets();
        initView();
        initDatePicker();
        setWeekView();

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initWidgets()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Token",0);
        token = sharedPreferences.getString("Token",null);
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        mLayout = findViewById(R.id.clock_main_page);
        mViewPager = findViewById(R.id.ViewPager);

        dateButton = findViewById(R.id.date_picker_button);
        dateButton.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ticker = findViewById(R.id.tv_scroll);
        ticker.setSelected(true);
        //设置边距5dp
        mViewPager.setPageMargin(dip2px(5));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day)
            {
                month = month + 1;
                CalendarUtils.selectedDate = getLocalDate(year, month, day);
                setWeekView();
            }
        };

        int year = CalendarUtils.selectedDate.getYear();
        int month = CalendarUtils.selectedDate.getMonthValue();
        int day = CalendarUtils.selectedDate.getDayOfMonth();

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog= new DatePickerDialog(this,style,dateSetListener,year,month,day);
//        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());//设置最大日期
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setWeekView()
    {
        dateButton.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        mViewPager.setCurrentItem(getDay(CalendarUtils.selectedDate.getDayOfWeek().getValue()));
        mPresenter.loadFlower(token,CalendarUtils.selectedDate.getDayOfYear());
    }

    private int getDay(int day)
    {
        if(day == 7){
            return 0;
        }
        return day;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void openSetting(View view)
    {
        if (Utils.isFastClick()) {
            Intent intent = new Intent(ClockActivity.this, SettingPageActivity.class);
            startActivity(intent);
        }
    }

    public void openUser(View view)
    {
        if (Utils.isFastClick()) {
            Intent intent = new Intent(ClockActivity.this, UserActivity.class);
            startActivity(intent);
        }
    }
    //dp转px的函数
    private int dip2px(int value) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initView() {
        mSunFlowerFragment = new FlowerFragment();
        mMonFlowerFragment = new FlowerFragment();
        mTueFlowerFragment = new FlowerFragment();
        mWesFlowerFragment = new FlowerFragment();
        mThuFlowerFragment = new FlowerFragment();
        mFriFlowerFragment = new FlowerFragment();
        mSatFlowerFragment = new FlowerFragment();

        fragments = new ArrayList<>();
        fragments.add(mSunFlowerFragment);
        fragments.add(mMonFlowerFragment);
        fragments.add(mTueFlowerFragment);
        fragments.add(mWesFlowerFragment);
        fragments.add(mThuFlowerFragment);
        fragments.add(mFriFlowerFragment);
        fragments.add(mSatFlowerFragment);

        mFragmentManager = getSupportFragmentManager();
        mFragmentPagerAdapter = new FlowerFragmentPagerAdapter(mFragmentManager, fragments);

        mViewPager.setAdapter(mFragmentPagerAdapter);

        mSunFlowerFragment.FlowerFragment("星期日");
        mMonFlowerFragment.FlowerFragment("星期一");
        mTueFlowerFragment.FlowerFragment("星期二");
        mWesFlowerFragment.FlowerFragment("星期三");
        mThuFlowerFragment.FlowerFragment("星期四");
        mFriFlowerFragment.FlowerFragment("星期五");
        mSatFlowerFragment.FlowerFragment("星期六");

        //设置viewPager页面滑动的事件
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //页面状态改变时调用,arg0为页面状态
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            //页面滑动过程中调用
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面滑动后调用
            @Override
            public void onPageSelected(int position) {
                int currentPosition = getDay(CalendarUtils.selectedDate.getDayOfWeek().getValue());

                if (position == currentPosition + 1) {
                    //右滑
                    CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusDays(1);
                    setWeekView();
                }else if (position == currentPosition - 1){
                    //左滑
                    CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusDays(1);
                    setWeekView();
                }
            }
        });
    }

    @Override
    public void showWhiteFlower() {
        FlowerFragment fragment = fragments.get(mViewPager.getCurrentItem());
        fragment.UnFlower();
    }

    @Override
    public void showSmileFlower() {
        FlowerFragment fragment = fragments.get(mViewPager.getCurrentItem());
        fragment.SmileFlower();
    }

    @Override
    public void serPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
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
                                ticker.setBackgroundResource(R.color.purple);
                            }
                            if (user.getData().getCurrent_backdrop() == 2) {
                                mLayout.setBackgroundResource(R.mipmap.theme_1);
                                ticker.setBackgroundResource(R.color.theme2);
                            }
                            if (user.getData().getCurrent_backdrop() == 3) {
                                mLayout.setBackgroundResource(R.mipmap.theme_2);
                                ticker.setBackgroundResource(R.color.theme3);
                            }
                            if (user.getData().getCurrent_backdrop() == 4) {
                                mLayout.setBackgroundResource(R.mipmap.theme_3);
                                ticker.setBackgroundResource(R.mipmap.theme_31);
                            }
                            if (user.getData().getCurrent_backdrop() == 5) {
                                mLayout.setBackgroundResource(R.mipmap.theme_4);
                                ticker.setBackgroundResource(R.mipmap.theme_41);

                            }
                            if (user.getData().getCurrent_backdrop() == 6) {
                                mLayout.setBackgroundResource(R.mipmap.theme_5);
                                ticker.setBackgroundResource(R.mipmap.theme_51);


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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume(){
        super.onResume();
        requestBg();
        setWeekView();
    }

}