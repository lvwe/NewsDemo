package com.example.yls.newsclient.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class SharedPreUtil {

    private static final String SHARED_PREFERENCES_NAME = "first_run_sp";
    private static final String FIRST_RUN = "first_fun";

    public static boolean isFirstRun(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(FIRST_RUN,true);
    }

    public static void saveFirstRun(Context context,boolean b){
        SharedPreferences sharedPreferences =  context.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(FIRST_RUN,false);
        editor.commit();
    }
}
