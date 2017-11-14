package com.example.yls.newsclient.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by yls on 2017/6/26.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        initViews();
        initListener();
        initDate();
    }

    protected abstract int getLayoutRes();

    protected abstract void initViews();

    protected abstract void initListener();

    protected abstract void initDate();

    private Toast mToast;
    public void showToast(String msg){
        if (mToast == null){
            mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }
    protected void goTo(Class activity,boolean b){
        Intent intent = new Intent(this,activity);
        startActivity(intent);
        if (b){
            finish();
        }else {
            return;
        }
    }
}
