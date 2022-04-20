package com.bignerdranch.android.sc.user.view;

import android.net.Uri;
import android.util.Log;


import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.util.Auth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class SendToRemote {
    private String url;
    private final static String access_key = "0bNiwJGpdwmvvuVAzLDjM6gnxj9MiwmSagVpIW81";
    private final static String secret_key = "zHA9w8PoSfL6D4dvWNwU2GF4XHUn9MalynbANE3_";
    private final static String bucket_name = "self-control";
    private final static String domain_name = "http://ossself-control.muxixyz.com";
    //private final static String token = "bNiwJGpdwmvvuVAzLDjM6gnxj9MiwmSagVpIW81:pF9n7f8DsXFpWJziEkf71yJqw08=:eyJzY29wZSI6InNlbGYtY29udHJvbCIsImRlYWRsaW5lIjo1OTQ1NDIyMDMwfQ==";
    private final static String TAG = " 哦，这该死的服务端";

    public void upLoad1(String path, String id) {
        String token = getToken();
        Log.d("获取的token",token);
        Configuration configuration = new Configuration.Builder()
                .connectTimeout(10)
                //.zone(zone)
                //.dns(buildDefaultDns())//指定dns服务器
                .responseTimeout(60)
                .build();

        UploadManager uploadManager = new UploadManager(configuration);
        final long time = System.currentTimeMillis();
        final String keyName = id + time;

        uploadManager.put(path, keyName, token,
                (key, respInfo, jsonData) -> {
                    if (respInfo.isOK()) {
                        try {
                            Log.e("zw", jsonData.toString() + respInfo);
                            String fileKey = jsonData.getString("key");
                            String fileHash = jsonData.getString("hash");
                            Log.d(TAG, "File Key: " + fileKey);
                            Log.d(TAG, "File Hash: " + fileHash);
                            Log.d(TAG, "X-Reqid: " + respInfo.reqId);
                            Log.d(TAG, "X-Via: " + respInfo.xvia);
                            Log.d(TAG, "--------------------------------" + "\n上传成功");
                        } catch (JSONException e) {
                            Log.d(TAG, jsonData.toString());
                            Log.d(TAG, "--------------------------------" + "\n上传失败");
                        }
                    } else {
                        Log.d(TAG, respInfo.toString());
                        if (jsonData != null) {
                            Log.d(TAG, jsonData.toString());
                        }
                        Log.d(TAG, "--------------------------------" + "\n上传失败");
                    }
                }, null);

        url = domain_name + '/' + keyName;
    }

    private String getToken(){
        Auth auth = Auth.create(access_key, secret_key);
        String upToken = auth.uploadToken(bucket_name);
        return upToken;
    }

    public String getUrl(){
        Log.d("what happend",url);
        return url;

    }




}
