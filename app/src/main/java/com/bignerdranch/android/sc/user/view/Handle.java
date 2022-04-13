package com.bignerdranch.android.sc.user.view;

import android.net.Uri;
import android.util.Log;

import com.bignerdranch.android.sc.net.NetUtil;
import com.bignerdranch.android.sc.net.RetrofitApi;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Handle {
    public static void sendPicture(String token, File file){
        RetrofitApi mApi = NetUtil.getInstance().getApi();
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file",file.getName(),requestFile);
        mApi.sendPicture(token,part).enqueue(new Callback<UserPic>() {
            @Override
            public void onResponse(Call<UserPic> call, Response<UserPic> response) {
                assert response.body() != null;
                Log.d("Test",response.body().toString());
            }

            @Override
            public void onFailure(Call<UserPic> call, Throwable t) {
                Log.i("Error",call.request().toString());
            }
        });
    }

    public static String makeUriToPath(Uri uri){
        String path = uri.getPath();

        return  path;
    }

}
