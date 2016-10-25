package com.example.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.myapplication.utils.Config;


public class SharedPreferencesUtil {
    private SharedPreferences sp = null;
    private int mode = 0;

    public SharedPreferencesUtil(Context context) {
        sp = context.getSharedPreferences(Config.APP_NAME,mode);
    }

    /**
     * 使用示例
     */
    public void setIsDialogRemaind(boolean flag) {
        sp.edit().putBoolean(Config.TEST, flag).commit();
    }

}
