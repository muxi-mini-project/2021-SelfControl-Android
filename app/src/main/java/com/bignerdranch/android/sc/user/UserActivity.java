package com.bignerdranch.android.sc.user;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bignerdranch.android.sc.R;
import com.bignerdranch.android.sc.StatusBar;
import com.bignerdranch.android.sc.login.User;
import com.bignerdranch.android.sc.user.CoinQueryActivity;
import com.bignerdranch.android.sc.user.MonthReportActivity;
import com.bignerdranch.android.sc.user.RankQueryActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;

import static com.bignerdranch.android.sc.login.LoginActivity.baseUrl;
import static com.bignerdranch.android.sc.login.LoginActivity.token;

public class UserActivity extends StatusBar {

    private ConstraintLayout mJinbi, mYuebao, mPaihangbang;
    private ImageButton mJinbiButton, mYuebaoButton, mPaihangbangButton,mBack;
    private ImageView iv_photo;
    private Bitmap head;
    // private TextView tv_select_gallery,tv_select_camera;
    private static String path = "/sdcard/myHead";
    private TextView mName;
    private User mUser;

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
        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    askPermissions();
                showListDialog();
            }
        });
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);
            iv_photo.setImageDrawable(drawable);
        }
        mBack = findViewById(R.id.user_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mJinbi = findViewById(R.id.user_jinbi);
        mJinbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(UserActivity.this, CoinQueryActivity.class);
                startActivity(intent1);
            }
        });
        mJinbiButton = findViewById(R.id.user_jinbi_button);
        mJinbiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(UserActivity.this, CoinQueryActivity.class);
                startActivity(intent4);
            }
        });

        mYuebao = findViewById(R.id.user_yuebao);
        mYuebao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(UserActivity.this, MonthReportActivity.class);
                startActivity(intent2);
            }
        });
        mYuebaoButton = findViewById(R.id.user_jinbi_button);
        mYuebaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(UserActivity.this, MonthReportActivity.class);
                startActivity(intent4);
            }
        });

        mPaihangbang = findViewById(R.id.user_paihangbang);
        mPaihangbang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(UserActivity.this, RankQueryActivity.class);
                startActivity(intent1);
            }
        });
        mPaihangbangButton = findViewById(R.id.user_jinbi_button);
        mPaihangbangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent4 = new Intent(UserActivity.this, RankQueryActivity.class);
                startActivity(intent4);
            }
        });

        mName = findViewById(R.id.name);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        UserClient client = retrofit.create(UserClient.class);
        Call<User> call = client.mUser(token);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mUser = response.body();
                if (mUser != null)
                    mName.setText(String.valueOf(mUser.getName()));

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Coin", "failure");
            }
        });

        mName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();

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
                            .baseUrl(baseUrl)
                            .addConverterFactory(GsonConverterFactory.create());

                    Retrofit retrofit = builder.build();

                    ChangeNameAPI client = retrofit.create(ChangeNameAPI.class);
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


    private void showListDialog() {
        final String[] items = {"从相册选取", "取消"};
        AlertDialog.Builder listDialog = new AlertDialog.Builder(UserActivity.this);
        listDialog.setTitle("更换头像");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 1:
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile
                                (new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                        startActivityForResult(intent2, 2);
                        dialog.dismiss();
                        break;
                    case 2:
                        dialog.dismiss();
                }
            }
        });
        listDialog.show();
    }

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
                    break;
                }
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        setPicToView(head);
                        iv_photo.setImageBitmap(head);
                    }
                }
                break;
            default:
                break;
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

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
// 关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public interface ChangeNameAPI{
        @PUT("user/")
        Call<User> changName(@Header("token")String token, @Body User mUser);

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
}

