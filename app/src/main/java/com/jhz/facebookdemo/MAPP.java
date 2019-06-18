package com.jhz.facebookdemo;

import android.app.Application;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.facebook.FacebookSdk;

public class MAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        int permission = ActivityCompat.checkSelfPermission(this,"android.permission.READ_EXTERNAL_STORAGE");
        if(permission != PackageManager.PERMISSION_GRANTED){
        }
    }
}
