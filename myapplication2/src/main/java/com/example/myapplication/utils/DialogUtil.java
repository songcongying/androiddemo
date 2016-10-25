package com.example.myapplication.utils;

import android.app.Dialog;
import android.content.Context;
/**
 * 
 * @author songcongying
 * 
 */

public class DialogUtil {
    static Dialog mDialog = null;

    public static Dialog showDialog(Context context, int theme) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
                mDialog = null;
            }
            mDialog = new Dialog(context, theme);
            return mDialog;
    }
}
