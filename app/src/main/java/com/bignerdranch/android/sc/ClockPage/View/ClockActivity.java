package com.bignerdranch.android.sc.ClockPage.View;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bignerdranch.android.sc.ClockPage.weekcalendar.CalendarAdapter;
import com.bignerdranch.android.sc.ClockPage.weekcalendar.CalendarUtils;
import com.bignerdranch.android.sc.GetBackdropAPI;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.ClockPage.flower.FlowerFragmentPagerAdapter;
import com.bignerdranch.android.sc.ClockPage.flower.FlowerFragment;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.settings.SettingPageActivity;
import com.bignerdranch.android.sc.user.UserActivity;

import java.time.LocalDate;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.ClockPage.weekcalendar.CalendarUtils.daysInWeekArray;
import static com.bignerdranch.android.sc.ClockPage.weekcalendar.CalendarUtils.getLocalDate;
import static com.bignerdranch.android.sc.ClockPage.weekcalendar.CalendarUtils.monthYearFromDate;
import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class ClockActivity extends StatusBar implements CalendarAdapter.OnItemListener {
    private TextView ticker;

    private ArrayList<Fragment> fragments;
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
    private User mUser;

    private RecyclerView calendarRecyclerView;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_main_page);

        CalendarUtils.selectedDate = LocalDate.now();

        initWidgets();
        initView();
        initDatePicker();
        setWeekView();

        //done = findViewById(R.id.unflower);
        //done.setBackgroundResource(R.mipmap.done);
        //ifpunchcomplete(done);

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initWidgets()
    {
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
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
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

        request();

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



    private void request() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClientBuilder.addInterceptor(logging);


        Retrofit.Builder builder1 = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build());

        Retrofit retrofit1 = builder1.build();
        GetBackdropAPI client1 = retrofit1.create(GetBackdropAPI.class);
        Call<User> call1 = client1.getCurrentBackdrop(token);

        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mUser = response.body();
                if (mUser != null) {
                    if (mUser.getCurrent_backdrop() == 6) {
                        mLayout.setBackgroundResource(R.mipmap.background_default);
                        ticker.setBackgroundResource(R.color.purple);
                    }
                    if (mUser.getCurrent_backdrop() == 1) {
                        mLayout.setBackgroundResource(R.mipmap.theme_1);
                        ticker.setBackgroundResource(R.color.theme2);
                    }
                    if (mUser.getCurrent_backdrop() == 2) {
                        mLayout.setBackgroundResource(R.mipmap.theme_2);
                        ticker.setBackgroundResource(R.color.theme3);
                    }
                    if (mUser.getCurrent_backdrop() == 3) {
                        mLayout.setBackgroundResource(R.mipmap.theme_3);
                        ticker.setBackgroundResource(R.mipmap.theme_31);
                    }
                    if (mUser.getCurrent_backdrop() == 4) {
                        mLayout.setBackgroundResource(R.mipmap.theme_4);
                        ticker.setBackgroundResource(R.mipmap.theme_41);
                    }
                    if (mUser.getCurrent_backdrop() == 5) {
                        mLayout.setBackgroundResource(R.mipmap.theme_5);
                        ticker.setBackgroundResource(R.mipmap.theme_51);

                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    //    private void ifpunchcomplete(ImageButton button){
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("http://39.102.42.156:2333/")
//                .addConverterFactory(GsonConverterFactory.create());
//
//        Retrofit retrofit = builder.build();
//        PunchAPI client3 = retrofit.create(PunchAPI.class);
//        Call<Data> call = client3.ifpunchcomplete(token);
//
//        call.enqueue(new Callback<Data>() {
//            @Override
//            public void onResponse(Call<Data> call, Response<Data> response) {
//
//                mData = response.body();
//                mData.getData();
//                if (mData.getData() > 0 ){
//                    mMonFlowerFragment.FlowerFragment(button);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Data> call, Throwable t) {
//
//            }
//        });
//    }
}