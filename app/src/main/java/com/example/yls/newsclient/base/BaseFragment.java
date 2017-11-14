package com.example.yls.newsclient.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by yls on 2017/6/27.
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if (mRootView == null){
        mRootView = LayoutInflater.from(getContext()).inflate(getLayoutRes(),container,false);
        initViews();
        initListener();
        initDate();
       }
        return mRootView;
    }

    public abstract int getLayoutRes();


    protected abstract void initViews();

    protected abstract void initListener();

    protected abstract void initDate();

    private Toast mToast;
    public void showToast(String msg){
        if (mToast == null){
            mToast = Toast.makeText(getContext(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

}
