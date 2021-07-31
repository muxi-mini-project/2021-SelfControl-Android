package com.bignerdranch.android.sc.user.View;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.Utils;
import com.bignerdranch.android.sc.login.LoginActivity;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.Presenter.UserImageChange;
import com.bignerdranch.android.sc.user.model.UserAPI_send;
import com.bignerdranch.android.sc.user.model.UserClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class UserActivity extends StatusBar implements View.OnClickListener  {

    private ConstraintLayout mJinbi, mYuebao, mPaihangbang;
    private ImageButton mJinbiButton, mYuebaoButton, mPaihangbangButton,mBack;
    private ImageView iv_photo;
    private Bitmap head;
    private TextView mName;
    private User mUser;
    private UserImageChange u = new UserImageChange(UserActivity.this);
    private SharedPreferences sharedPreferences;
    private final String fileName = "IMG_selfControl.jpg";
    private String path;
    private static final int TAKE_PHOTO = 0X66;
    private static final int PICK_PHOTO = 0X88;

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

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:2333/api/v1/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.mUser(token);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                mUser = response.body();
                if (response.isSuccessful() == true){
                    if (mUser != null)
                        mName.setText(String.valueOf(mUser.getName()));
                }else {
                    Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Coin", "failure");
            }
        });



    }
    private void showInputDialog() {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(UserActivity.this);
        AlertDialog.Builder inputDialog = new AlertDialog.Builder(UserActivity.this);
        inputDialog.setTitle("更换昵称").setView(editText);
        inputDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(editText.getText()!= null) {
                    String name;
                    mName.setText(editText.getText().toString());
                    name = editText.getText().toString();

                    Retrofit.Builder builder = new Retrofit.Builder()
                            .baseUrl("http://39.102.42.156:2333/api/v1/")
                            .addConverterFactory(GsonConverterFactory.create());

                    Retrofit retrofit = builder.build();

                    UserAPI_send client = retrofit.create(UserAPI_send.class);
                    Call<User> call = client.changName(token, new User(name));

                    call.enqueue(new Callback<User>() {

                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(UserActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(UserActivity.this,"修改失败，请稍后再试",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).show();
    }

/*
    private void showListDialog() {
        final String[] items = {"从相册选取", "取消"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(UserActivity.this);
        listDialog.setTitle("更换头像");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent1, 1);
                        dialog.dismiss();
                        break;
                   /* case 2:
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile
                                (new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                        startActivityForResult(intent2, 2);
                        dialog.dismiss();
                        break;
                    case 1:
                        dialog.dismiss();
                        break;
                }
            }
        });
        listDialog.show();
    }
*/

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
                    Log.d("Demo","文件名是"+u.getFile().toString());
                    savePhotos(u.getFile());
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
                iv_photo.setImageBitmap(bitmap);
                savePhotos(u.getFile());

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
    public void savePhotos(File file) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("getFilePath",file.getPath());
        editor.commit();
    }



   /* private void askPermissions() {//动态申请权限！
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS,//联系人的权限
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};//读写SD卡权限
            //验证是否许可权限
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                    this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                }
            }
        }
    }*/

    /* private void showTypeDialog(){
         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         final AlertDialog dialog = builder.create();
         View view = View.inflate(this,R.layout.dialog_select_photo,null);
         tv_select_gallery = findViewById(R.id.tv_select_gallery);
         tv_select_gallery.setOnClickListener(new DialogInterface.setOnClickListener() {
             @Override
             public void onClick(DialogInterface dialog,int which) {
                 Intent intent1 = new Intent(Intent.ACTION_PICK,null);
                 intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
                 startActivityForResult(intent1,1);
                 dialog.dismiss();
             }
         });
         tv_select_camera = findViewById(R.id.tv_select_camera);
         tv_select_camera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                 intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile
                         (new File(Environment.getExternalStorageDirectory(),"head.jpg")));
                 startActivityForResult(intent2,2);
                 dialog.dismiss();
             }
         });
         dialog.setView(view);
         dialog.show();
     }*/


}

