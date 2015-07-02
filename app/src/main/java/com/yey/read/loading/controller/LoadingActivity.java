package com.yey.read.loading.controller;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import android.widget.ImageView;

import com.yey.read.MainActivity;
import com.yey.read.R;
import com.yey.read.common.AccountInfo;
import com.yey.read.common.AppConstants;
import com.yey.read.common.AppContext;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.util.SharedPreferencesHelper;
import com.yey.read.util.TimeUtil;

public class LoadingActivity extends BaseActivity {

    private static final int GO_HOME = 100;
    private static final int GO_GUIDE =200;
    private static final int sleepTime = 2000;

    AppContext appContext;

    ImageView iv_image;
    private int[] welcome = new int[]{R.drawable.welcome,R.drawable.welcome,R.drawable.welcome};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //窗口全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);

        iv_image = (ImageView)this.findViewById(R.id.iv_loading);
        int splash = SharedPreferencesHelper.getInstance(AppContext.getInstance()).getInt(AppConstants.PREF_SPLASH,0);
        String lasttime = SharedPreferencesHelper.getInstance(AppContext.getInstance()).getString(AppConstants.PREF_LASTTIME, TimeUtil.getCurrentTimeYMD());
        String currenttime = TimeUtil.getCurrentTimeYMD();

        appContext = AppContext.getInstance();
        if(!currenttime.equals(lasttime)){

            if(splash>=2){
                splash = 0;
            }else{
                splash++;
            }

            Drawable imageBackground =getResources().getDrawable(welcome[splash]);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                iv_image.setBackground(imageBackground);
            } else {
                iv_image.setBackgroundDrawable(imageBackground);
            }

            SharedPreferencesHelper.getInstance(AppContext.getInstance()).setInt(AppConstants.PREF_SPLASH,splash);
            SharedPreferencesHelper.getInstance(AppContext.getInstance()).setString(AppConstants.PREF_LASTTIME, TimeUtil.getCurrentTimeYMD());
        }else{
            SharedPreferencesHelper.getInstance(AppContext.getInstance()).setString(AppConstants.PREF_LASTTIME, TimeUtil.getCurrentTimeYMD());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        int islogin = SharedPreferencesHelper.getInstance(AppContext.getInstance()).getInt(AppConstants.PREF_ISLOGIN,0);
        final AccountInfo accountInfo =  appContext.getAccountInfo();
        if (accountInfo.getUid()==0 && islogin == 0) {
            SharedPreferencesHelper.getInstance(AppContext.getInstance()).setBoolean(AppConstants.FLAG_FIRST_LOGINSUCCESS, true);
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, sleepTime);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_HOME, sleepTime);
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    openActivity(MainActivity.class);
                    finish();
                    break;
                case GO_GUIDE:
                    openActivity(GuideActivity.class);
                    finish();
                    break;
            }
        }
    };

}
