package com.jhz.facebookdemo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ShareActivity extends AppCompatActivity {
    private ShareButton sharePictrueButton ,shareVideoButton;
    private CallbackManager callbackManager;
    private String TAG="ShareActivity";
    private Button addVideoButton,addPictureButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        addFile();//为分享视频，创建文件夹，并手动将视频文件放入 内部存储设备/Android/data/com.jhz.facebookdemo/files/DCIM文件下
        sharePictrueButton = (ShareButton)findViewById(R.id.fb_share_picture_button);
        shareVideoButton = findViewById(R.id.fb_share_video_button);
        addPictureButton = findViewById(R.id.addPicture);
        addVideoButton = findViewById(R.id.addFiles);

        addVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareVideo();
            }
        });
        addPictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePhoto();
            }
        });


        callbackManager = CallbackManager.Factory.create();
        shareVideoButton.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.i(TAG,"shareVideoButton onSuccess");
            }

            @Override
            public void onCancel() {
                Log.i(TAG,"shareVideoButton onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i(TAG,"shareVideoButton onError"+error);
            }
        });
        sharePictrueButton.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Log.i(TAG,"sharePictrueButton onSuccess");
            }

            @Override
            public void onCancel() {
                Log.i(TAG,"sharePictrueButton onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i(TAG,"sharePictrueButton onError"+error);
            }
        });
    }

    private void sharePhoto(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(bitmap)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();
        sharePictrueButton.setShareContent(content);
    }

    private void  addFile(){
        /*
         * 9.创建并返回外部存储文件目录，需要sd卡的的写入数据权限；
         * 输出结果是：getExternalFilesDir():/mnt/sdcard/Android/data/com.example.filedemo/files/Music
         */
        File dir4 = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        Log.i("MainActivity", "getExternalFilesDir():" + dir4.toString());
    }

    private void shareVideo(){
        File dir4 = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        Uri videoFileUri = Uri.fromFile(new File(dir4.toString()+"/123.mp4"));
        ShareVideo svideo = new ShareVideo.Builder()
                .setLocalUrl(videoFileUri)
                .build();
        ShareVideoContent content = new ShareVideoContent.Builder()
                .setVideo(svideo)
                .build();
        shareVideoButton.setShareContent(content);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
