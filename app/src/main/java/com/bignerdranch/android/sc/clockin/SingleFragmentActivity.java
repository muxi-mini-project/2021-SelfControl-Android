package com.bignerdranch.android.sc.clockin;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.label.HealthFragment;
import com.bignerdranch.android.sc.label.SportFragment;
import com.bignerdranch.android.sc.label.StudyFragment;

import java.sql.Timestamp;
import java.util.List;

import static com.bignerdranch.android.sc.StatusBar.makeStatusBarTransparent;

public abstract class SingleFragmentActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    protected abstract ClockinListFragment createFragment();

    private ImageView mImageView;
    private TextView mTextView1;
    private TextView mTextView2;
    private int mTimes = 0;
    private ImageButton mImageButton;

    private boolean isDeleteShow;//删除组件是否显示
    private ViewGroup mPointChild;//手指按下位置的item组件
    private int mDeleteWidth;//删除组件的宽度
    private LinearLayout.LayoutParams mItemLayoutParams;//手指按下时所在的item的布局参数

    //定义手势检测器实例
    GestureDetector detector;

    private boolean status = false;
    private  int POS = 0;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.clockin_pager);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

        //new一个手势检测器
        detector = new GestureDetector(this, this);

        mImageButton = (ImageButton) findViewById(R.id.clockin);

        mImageView = (ImageView) findViewById(R.id.list1);

        //设置状态栏透明
        makeStatusBarTransparent(this);
        //状态栏文字自适应
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    //用GestureDetector处理在该activity上发生的所有触碰事件
    public boolean onTouchEvent(MotionEvent me) {
        return detector.onTouchEvent(me);
    }

    public boolean onDown(MotionEvent arg0) {
        return false;
    }


    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float minMove = 120;//定义最小滑动距离  
        float minVelocity = 0;//定义最小滑动速度  
        float beginX = e1.getX();
        float endX = e2.getX();


        if (beginX - endX > minMove && Math.abs(velocityX) > minVelocity) {//左滑  
            mImageButton.setBackgroundResource(R.mipmap.delete2);
            mImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    POS = 1;
                    mImageButton.setBackgroundResource(R.color.white);
                }
            });
        }else{
            mImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!status) {
                        mImageButton.setBackgroundResource(R.mipmap.finish);
                        mImageButton.setEnabled(false);
                        status = true;
                        mTimes++;
                    } else {
                        mImageButton.setBackgroundResource(R.mipmap.clock_in);
                        mImageButton.setEnabled(true);
                        status = false;
                        showTypeDialog();
                    }
                }
            });
        }

        return false;
    }

    private void showTypeDialog() {
        View inflate1 = View.inflate(SingleFragmentActivity.this, R.layout.clockin_finished, null);
        final PopupWindow popupWindow = new PopupWindow(inflate1, 900, 600);//参数为1.View 2.宽度 3.高度
        popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(inflate1, Gravity.CENTER, 0, -100);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //弹窗消失恢复原来的透明度
                backgroundAlpha(1f);
            }
        });

    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    private void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    public void onShowPress(MotionEvent arg0) {
        // TODO Auto-generated method stub
    }

    public boolean onSingleTapUp(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public void onLongPress(MotionEvent arg0) {
        // TODO Auto-generated method stub
    }

    public boolean onScroll(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
        return false;
    }

}
