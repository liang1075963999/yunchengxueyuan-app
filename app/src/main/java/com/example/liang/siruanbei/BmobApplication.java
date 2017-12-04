package com.example.liang.siruanbei;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

import cn.bmob.v3.Bmob;

public class BmobApplication extends Application {

    public static String APPID = "d1c50ea41373a8d4c3c9c263b4a24121";
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        //提供以下两种方式进行初始化操作：
//		//第一：设置BmobConfig，允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)
//		BmobConfig config =new BmobConfig.Builder(this)
//		//设置appkey
//		.setApplicationId(APPID)
//		//请求超时时间（单位为秒）：默认15s
//		.setConnectTimeout(30)
//		//文件分片上传时每片的大小（单位字节），默认512*1024
//		.setUploadBlockSize(1024*1024)
//		//文件的过期时间(单位为秒)：默认1800s
//		.setFileExpiration(5500)
//		.build();
//		Bmob.initialize(config);
        //第二：默认初始化
        Stetho.initializeWithDefaults(this);
        Bmob.initialize(this, APPID);
        context = getApplicationContext();
    }
    public static Context getContext() {
        return context;
    }

}