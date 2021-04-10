package com.bignerdranch.android.sc.clockpage;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.ViewFlipper;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bignerdranch.android.sc.Data;
import com.bignerdranch.android.sc.GetBackdropAPI;
import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;

import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.clockpage.flower.FlowerFragmentPagerAdapter;
import com.bignerdranch.android.sc.clockpage.flower.FlowerFragment;
import com.bignerdranch.android.sc.clockpage.flower.NoScrollViewPager;
import com.bignerdranch.android.sc.clockpage.weekcalendar.DateAdapter;
import com.bignerdranch.android.sc.clockpage.weekcalendar.SpecialCalendar;
import com.bignerdranch.android.sc.label.PunchAPI;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.settings.SettingPageActivity;
import com.bignerdranch.android.sc.user.UserActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class ClockActivity extends StatusBar implements GestureDetector.OnGestureListener {
    private static String TAG = "ClockActivity";
    private ViewFlipper flipper1 = null;
    private GridView gridView = null;
    private GestureDetector gestureDetector = null;
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private int week_c = 0;
    private int week_num = 0;
    private String currentDate = "";
    private DateAdapter dateAdapter;
    private int daysOfMonth = 0;
    private int dayOfWeek = 0;
    private int weeksOfMonth = 0;
    private SpecialCalendar sc = null;
    private boolean isLeapyear = false;
    private int selectPostion = 0;
    private String dayNumbers[] = new String[7];
    private TextView tvDate;
    private int currentYear;
    private int currentMonth;
    private int currentWeek;
    private int currentDay;
    private int currentNum;

    private TextView ticker;
    private ImageButton settings;
    private ImageButton users;
    private ImageButton done;

    private ArrayList<Fragment> fragments;
    private NoScrollViewPager mViewPager;
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
    private Data mData;

    private int date;

    public ClockActivity() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date);

        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);

        currentYear = year_c;
        currentMonth = month_c;
        currentDay = day_c;

        sc = new SpecialCalendar();
        getCalendar(year_c, month_c);
        week_num = getWeeksOfMonth();
        currentNum = week_num;
        if (dayOfWeek == 7) {
            week_c = currentDay / 7 + 1;
        } else {
            if (currentDay <= (7 - dayOfWeek)) {
                week_c = 1;
            } else {
                if ((currentDay - (7 - dayOfWeek)) % 7 == 0) {
                    week_c = (currentDay - (7 - dayOfWeek)) / 7 + 1;
                } else {
                    week_c = (currentDay - (7 - dayOfWeek)) / 7 + 2;
                }
            }
        }
        currentWeek = week_c;
        getCurrent();

    }


    public int getWeeksOfMonth(int year, int month) {
        int preMonthRelax = 0;
        int dayFirst = getWhichDayOfWeek(year, month);
        int days = sc.getDaysOfMonth(sc.isLeapYear(year), month);
        if (dayFirst != 7) {
            preMonthRelax = dayFirst;
        }
        if ((days + preMonthRelax) % 7 == 0) {
            weeksOfMonth = (days + preMonthRelax) / 7;
        } else {
            weeksOfMonth = (days + preMonthRelax) / 7 + 1;
        }
        return weeksOfMonth;

    }


    public int getWhichDayOfWeek(int year, int month) {
        return sc.getWeekdayOfMonth(year, month);

    }


    public int getLastDayOfWeek(int year, int month) {
        return sc.getWeekDayOfLastMonth(year, month,
                sc.getDaysOfMonth(isLeapyear, month));
    }

    public void getCalendar(int year, int month) {
        isLeapyear = sc.isLeapYear(year);
        daysOfMonth = sc.getDaysOfMonth(isLeapyear, month);
        dayOfWeek = sc.getWeekdayOfMonth(year, month);
    }

    public int getWeeksOfMonth() {
        // getCalendar(year, month);
        int preMonthRelax = 0;
        if (dayOfWeek != 7) {
            preMonthRelax = dayOfWeek;
        }
        if ((daysOfMonth + preMonthRelax) % 7 == 0) {
            weeksOfMonth = (daysOfMonth + preMonthRelax) / 7;
        } else {
            weeksOfMonth = (daysOfMonth + preMonthRelax) / 7 + 1;
        }
        return weeksOfMonth;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_main_page);

        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDate.setText(year_c + "年" + month_c + "月" + day_c + "日");
        gestureDetector = new GestureDetector(this);
        flipper1 = (ViewFlipper) findViewById(R.id.flipper1);

        dateAdapter = new DateAdapter(this, currentYear, currentMonth, currentWeek, currentWeek == 1 ? true : false);
        addGridView();
        dayNumbers = dateAdapter.getDayNumbers();
        gridView.setAdapter(dateAdapter);
        selectPostion = dateAdapter.getTodayPosition();
        gridView.setSelection(selectPostion);
        flipper1.addView(gridView, 0);


        ticker = findViewById(R.id.tv_scroll);
        ticker.setSelected(true);

        settings = (ImageButton) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    Intent intent = new Intent(ClockActivity.this, SettingPageActivity.class);
                    startActivity(intent);
                }

            }
        });

        //done = findViewById(R.id.unflower);
        //done.setBackgroundResource(R.mipmap.done);
        //ifpunchcomplete(done);

        initView();

        users = findViewById(R.id.users);
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isFastClick()) {
                    Intent intent = new Intent(ClockActivity.this, UserActivity.class);
                    startActivity(intent);
                }

            }
        });


        mViewPager.setCurrentItem(dateAdapter.getTodayPosition());
        //设置边距5dp
        mViewPager.setPageMargin(dip2px(5));


        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    //dp转px的函数
    private int dip2px(int value) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    private void initView() {

        request();
        mLayout = findViewById(R.id.clock_main_page);


        mViewPager = findViewById(R.id.ViewPager);

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


        mSunFlowerFragment.FlowerFragment("星期日",date);
        mMonFlowerFragment.FlowerFragment("星期一",date);
        mTueFlowerFragment.FlowerFragment("星期二",date);
        mWesFlowerFragment.FlowerFragment("星期三",date);
        mThuFlowerFragment.FlowerFragment("星期四",date);
        mFriFlowerFragment.FlowerFragment("星期五",date);
        mSatFlowerFragment.FlowerFragment("星期六",date);

        /*View view_line = LayoutInflater.from(ClockActivity.this).inflate(R.layout.item_calendar, null).findViewById(R.id.view_line);
        LinearLayout ll = LayoutInflater.from(ClockActivity.this).inflate(R.layout.item_calendar, null).findViewById(R.id.ll_data);


        设置viewPager页面滑动的事件
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int preItem,position;

            //页面状态改变时调用,arg0为页面状态
            @Override
            public void onPageScrollStateChanged(int arg0) {
                preItem = mViewPager.getCurrentItem();
            }

            //页面滑动过程中调用
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }


            //页面滑动后调用
            @Override
            public void onPageSelected(int arg0) {
                if(mViewPager.getCurrentItem() < preItem){//从左向右

                    return;
                }else {
                    ll.setSelected(false);

                    view_line.setVisibility(View.INVISIBLE);
                }

            }
        });*/
    }

    private void addGridView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gridView = new GridView(this);
        gridView.setNumColumns(7);
        gridView.setGravity(Gravity.CENTER_VERTICAL);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setVerticalSpacing(1);
        gridView.setHorizontalSpacing(1);
        gridView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return ClockActivity.this.gestureDetector.onTouchEvent(event);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "day:" + dayNumbers[position]);
                selectPostion = position;
                dateAdapter.setSeclection(position);
                dateAdapter.notifyDataSetChanged();
                tvDate.setText(dateAdapter.getCurrentYear(selectPostion) + "年" + dateAdapter.getCurrentMonth(selectPostion) + "月" + dayNumbers[position] + "日");

                //int month =

                int today = dateAdapter.getSelectedPosition(dateAdapter.getCurrentYear(selectPostion), dateAdapter.getCurrentMonth(selectPostion), Integer.parseInt(dayNumbers[position]));
                mViewPager.setCurrentItem(today);

                Bundle bundle = new Bundle();
                bundle.putInt("date",date);
            }
        });
        gridView.setLayoutParams(params);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // jumpWeek = 0;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    public void getCurrent() {
        if (currentWeek > currentNum) {
            if (currentMonth + 1 <= 12) {
                currentMonth++;
            } else {
                currentMonth = 1;
                currentYear++;
            }
            currentWeek = 1;
            currentNum = getWeeksOfMonth(currentYear, currentMonth);
        } else if (currentWeek == currentNum) {
            if (getLastDayOfWeek(currentYear, currentMonth) == 6) {
            } else {
                if (currentMonth + 1 <= 12) {
                    currentMonth++;
                } else {
                    currentMonth = 1;
                    currentYear++;
                }
                currentWeek = 1;
                currentNum = getWeeksOfMonth(currentYear, currentMonth);
            }

        } else if (currentWeek < 1) {
            if (currentMonth - 1 >= 1) {
                currentMonth--;
            } else {
                currentMonth = 12;
                currentYear--;
            }
            currentNum = getWeeksOfMonth(currentYear, currentMonth);
            currentWeek = currentNum - 1;
        }
    }

    @Override

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {//手势滑动效果
        int gvFlag = 0;
        /*getX表示得到他在整个页面的x或者y坐标
         */
        if (e1.getX() - e2.getX() > 80) {//向左滑动
            addGridView();
            currentWeek++;
            getCurrent();
            dateAdapter = new DateAdapter(this, currentYear, currentMonth, currentWeek, currentWeek == 1 ? true : false);
            dayNumbers = dateAdapter.getDayNumbers();
            gridView.setAdapter(dateAdapter);
            tvDate.setText(dateAdapter.getCurrentYear(selectPostion) + "年" + dateAdapter.getCurrentMonth(selectPostion) + "月" + dayNumbers[selectPostion] + "日");
            gvFlag++;
            flipper1.addView(gridView, gvFlag);
            dateAdapter.setSeclection(selectPostion);
            this.flipper1.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
            this.flipper1.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));
            this.flipper1.showNext();
            flipper1.removeViewAt(0);
            return true;


        } else if (e1.getX() - e2.getX() < -80) {//向右滑动
            addGridView();
            currentWeek--;
            getCurrent();
            dateAdapter = new DateAdapter(this, currentYear, currentMonth,
                    currentWeek, currentWeek == 1 ? true : false);
            dayNumbers = dateAdapter.getDayNumbers();
            gridView.setAdapter(dateAdapter);
            tvDate.setText(dateAdapter.getCurrentYear(selectPostion) + "年" + dateAdapter.getCurrentMonth(selectPostion) + "月" + dayNumbers[selectPostion] + "日");
            gvFlag++;
            flipper1.addView(gridView, gvFlag);
            dateAdapter.setSeclection(selectPostion);
            this.flipper1.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
            this.flipper1.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
            this.flipper1.showPrevious();
            flipper1.removeViewAt(0);
            return true;
        }
        return false;
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