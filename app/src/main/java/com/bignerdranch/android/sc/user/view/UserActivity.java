package com.bignerdranch.android.sc.user.view;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.login.LoginActivity;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.bean.GoldHistory;
import com.bignerdranch.android.sc.user.bean.Rank;
import com.bignerdranch.android.sc.user.bean.Report;
import com.bignerdranch.android.sc.user.bean.Week;
import com.bignerdranch.android.sc.user.presenter.UserImageChange;
import com.bignerdranch.android.sc.user.presenter.UserPresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class UserActivity extends StatusBar implements View.OnClickListener, com.bignerdranch.android.sc.user.view.UserViewHandler {

    private ConstraintLayout mJinbi, mYuebao, mPaihangbang;
    private ImageButton mJinbiButton, mYuebaoButton, mPaihangbangButton,mBack;
    private ImageView iv_photo;
    private Bitmap head;
    private TextView mName;
    private User.DataDTO mUser;
    private UserImageChange u = new UserImageChange(UserActivity.this);
    public static SharedPreferences sharedPreferences;
    private final String fileName = "IMG_selfControl.jpg";
    private String path;
    private static final int TAKE_PHOTO = 0X66;
    private static final int PICK_PHOTO = 0X88;
    private final UserPresenter userPresenter = new UserPresenter(UserActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        init();

        makeStatusBarTransparent(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void init() {

        iv_photo = findViewById(R.id.head);
        iv_photo.setOnClickListener(this);

        mBack = findViewById(R.id.user_back);
        mBack.setOnClickListener(this);

        mJinbi = findViewById(R.id.user_jinbi);
        mJinbi.setOnClickListener(this);
        mJinbiButton = findViewById(R.id.user_jinbi_button);
        mJinbiButton.setOnClickListener(this);

        mYuebao = findViewById(R.id.user_yuebao);
        mYuebao.setOnClickListener(this);
        mYuebaoButton = findViewById(R.id.user_yuebao_button);
        mYuebaoButton.setOnClickListener(this);

        mPaihangbang = findViewById(R.id.user_paihangbang);
        mPaihangbang.setOnClickListener(this);
        mPaihangbangButton = findViewById(R.id.user_paihangbang_button);
        mPaihangbangButton.setOnClickListener(this);

        mName = findViewById(R.id.name);
        mName.setOnClickListener(this);

        /*实现加载*/
        sharedPreferences = this.getSharedPreferences("sharedPreferences",MODE_PRIVATE);
        String string = sharedPreferences.getString("getFilePath",null);
        if(string!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(string);
            iv_photo.setImageBitmap(bitmap);
        }
        userPresenter.SendUser(token);
    }
    private void showInputDialog() {
        /*@setView 装入一个EditView
         */
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_changename, null, false);
        final PopupWindow popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //参数为1.View 2.宽度 3.高度
        popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
        popupWindow.setFocusable(true);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });


        TextView textView = (TextView)v.findViewById(R.id.changename_text);
        EditText editView = (EditText)v.findViewById(R.id.changename_edit);
        Button button1 = (Button)v.findViewById(R.id.changeName_button1);
        Button button2 = (Button)v.findViewById(R.id.changeName_button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundAlpha(1.0f);
                popupWindow.dismiss();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editView.getText().toString().length() == 0 ) {
                    Toast.makeText(UserActivity.this,"名称不可以为空哦！",Toast.LENGTH_SHORT).show();
                }else{
                    mName.setText(editView.getText().toString());
                    String name = editView.getText().toString();
                    mUser.setName(name);
                    userPresenter.SendChangeName(token,mUser);
                    backgroundAlpha(1.0f);
                    popupWindow.dismiss();
                }

            }

        });

        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

    }

    public void backgroundAlpha(float v) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = v; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                if (u.getFile() == null || !u.getFile().exists()) {
                    iv_photo.setImageBitmap(null);
                } else {
                    Bitmap bitmap = u.getScaledBitmap(u.getFile().getPath(), UserActivity.this);
                    iv_photo.setImageBitmap(bitmap);
                    savePhotos(u.getFile().getPath());
                    userPresenter.changeAvatar(u.getFile().getPath(),token);
                    //Handle.sendPicture(token,u.getFile());
                }

            }else if(requestCode==PICK_PHOTO){
                Uri uri = data.getData();
                UserActivity.this.revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                Log.d("TRy", uri.toString());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    path = u.handleImageOnKitKat(uri);
                } else {
                    path = u.handleImageBeforeKitKat(uri);
                }

                Bitmap bitmap = BitmapFactory.decodeFile(path);
                Log.d("相册",path);
                iv_photo.setImageBitmap(bitmap);
                savePhotos(path);
                //Handle.sendPicture(token,u.getFile());
                userPresenter.changeAvatar(path,token);
            } else {
                Log.d("Demo", "结果无");
            }
        }
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.head:
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    if (!checkPermission()) {
                        requestPermissions(PICK_PHOTO);
                    }
                    if (!checkPermission()) {
                        requestPermissions(TAKE_PHOTO);
                    }
                }
                u.showDialog();
                Log.d("Demo", "点击");
                break;
            case R.id.user_back:
                finish();
                break;
            case R.id.user_jinbi:
            case R.id.user_jinbi_button:
                if (Utils.isFastClick()){
                    Intent intent1 = new Intent(UserActivity.this, CoinQueryActivity.class);
                    startActivity(intent1);}
                break;
            case R.id.user_yuebao:
            case R.id.user_yuebao_button:
                if (Utils.isFastClick()){
                    Intent intent2 = new Intent(UserActivity.this, MonthReportActivity.class);
                    startActivity(intent2);
                }
                break;
            case R.id.user_paihangbang:
            case R.id.user_paihangbang_button:
                if (Utils.isFastClick()){
                    Intent intent3 = new Intent(UserActivity.this, RankQueryActivity.class);
                    startActivity(intent3);
                }
                break;
            case R.id.name:
                showInputDialog();
                break;

            default:break;
        }

    }
    /*动态权限请求*/
    public boolean checkPermission() {
        boolean haveCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean haveWritePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return haveCameraPermission && haveWritePermission;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermissions(int request) {
        switch (request) {
            case TAKE_PHOTO:
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, TAKE_PHOTO);
                break;
            case PICK_PHOTO:
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_PHOTO);
                break;
        }
    }

    /*实现推出后再加载*/
    public void savePhotos(String filePath) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("getFilePath",filePath);
        editor.commit();
    }

    @Override
    public void showTheWeekPicture(List<Week.DataDTO> list) {

    }

    @Override
    public void showGoldHistory(List<GoldHistory.DataDTO> goldList) {

    }

    @Override
    public void showChangedName() {
        Toast.makeText(this,"修改成功！",Toast.LENGTH_SHORT);
    }

    @Override
    public void getUser(User.DataDTO u,Bitmap bitmap) {
        this.mUser = u;
        if(mUser!=null){
            mName.setText(mUser.getName());
            RequestOptions options = new RequestOptions().placeholder(new BitmapDrawable(bitmap));
            Glide.with(this)
                    .asBitmap()
                    .apply(options)
                    .load(Uri.parse(u.getUser_picture()))
                    .into(iv_photo);
        } else{
            Intent i = new Intent(UserActivity.this,LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void showRank(Rank.DataDTO rank) {

    }

    @Override
    public void showMonthReport(List<Report.DataDTO> report) {

    }


}

