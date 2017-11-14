package com.example.yls.newsclient.activity;

import android.os.SystemClock;

import com.example.yls.newsclient.R;
import com.example.yls.newsclient.util.SharedPreUtil;
import com.example.yls.newsclient.base.BaseActivity;

/**
 * Created by yls on 2017/6/26.
 */

public class StartActivity extends BaseActivity {

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_start;
    }

    @Override
    protected void initViews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                Boolean isFirstRun = SharedPreUtil.isFirstRun(getApplicationContext());
                if (isFirstRun){
                    goTo(GuideActivity.class,true);
                    SharedPreUtil.saveFirstRun(getApplicationContext(),true);
                }else {
                    goTo(MainActivity.class,true);
                }
                /*try {
                    Thread.sleep(3000);
                    goTo(GuideActivity.class,true);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }).start();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initDate() {

    }
}
