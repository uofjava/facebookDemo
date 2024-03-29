/**
 * Copyright (c) 2017-present, Facebook, Inc. All rights reserved.
 * <p>
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 * copy, modify, and distribute this software in source code or binary form for use
 * in connection with the web services and APIs provided by Facebook.
 * <p>
 * As with any software that integrates with the Facebook platform, your use of
 * this software is subject to the Facebook Developer Principles and Policies
 * [http://developers.facebook.com/policy/]. This copyright notice shall be
 * included in all copies or substantial portions of the software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.jhz.facebookdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class MainActivity extends Activity {

    private static final int RESULT_PROFILE_ACTIVITY = 1;
    private static final int RESULT_POSTS_ACTIVITY = 2;
    private static final int RESULT_PERMISSIONS_ACTIVITY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{"android.permission.READ_EXTERNAL_STORAGE","android.permission.READ_EXTERNAL_STORAGE"},1);

        // If MainActivity is reached without the user being logged in, redirect to the Login
        // Activity
        if (AccessToken.getCurrentAccessToken() == null) {
            Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
            startActivity(loginIntent);
        }

        // Make a button which leads to profile information of the user
        Button gotoProfileButton = findViewById(R.id.btn_profile);
        gotoProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() == null) {
                    Intent profileIntent = new Intent(MainActivity.this, FacebookLoginActivity
                            .class);
                    startActivityForResult(profileIntent, RESULT_PROFILE_ACTIVITY);
                } else {
//                    Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
//                    startActivity(profileIntent);
                }
            }
        });

        // Make a button which leads to posts made by the user
        Button gotoPostsFeedButton = findViewById(R.id.btn_posts);
        gotoPostsFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() == null) {
                    Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
                    startActivityForResult(loginIntent, RESULT_POSTS_ACTIVITY);
                } else {
//                    Intent postsFeedIntent = new Intent(MainActivity.this, PostFeedActivity.class);
//                    startActivity(postsFeedIntent);
                }
            }
        });

        // Make a button which leads to request or revoke permissions
        Button gotoPermissionsButton = findViewById(R.id.btn_permissions);
        gotoPermissionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() == null) {
                    Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
                    startActivityForResult(loginIntent, RESULT_PERMISSIONS_ACTIVITY);
                } else {
//                    Intent permissionsIntent = new Intent(MainActivity.this, PermissionsActivity.class);
//                    startActivity(permissionsIntent);
                }
            }
        });

        // Make a logout button
        Button fbLogoutButton = findViewById(R.id.btn_fb_logout);
        fbLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent loginIntent = new Intent(MainActivity.this, FacebookLoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_PROFILE_ACTIVITY:
                if (resultCode == RESULT_OK) {
//                    Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
//                    startActivity(profileIntent);
                }
                break;
            case RESULT_POSTS_ACTIVITY:
                if (resultCode == RESULT_OK) {
//                    Intent postFeedIntent = new Intent(MainActivity.this, PostFeedActivity.class);
//                    startActivity(postFeedIntent);
                }
                break;
            case RESULT_PERMISSIONS_ACTIVITY:
                if (resultCode == RESULT_OK) {
//                    Intent permissionsIntent = new Intent(MainActivity.this, PermissionsActivity.class);
//                    startActivity(permissionsIntent);
                }
                break;
            default:
                super.onActivityResult(requestCode,resultCode, data);
        }
    }
}
