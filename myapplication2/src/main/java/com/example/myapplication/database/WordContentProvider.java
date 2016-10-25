package com.example.myapplication.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Songcongying on 2016/10/24.
 */
public class WordContentProvider extends ContentProvider {

    private DBHelper mDBHelper;
    private Context mContext;

    public static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static{
//        matcher.addURI(DBHelper.DATA_BASE_AUTHORITY);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext() ;
        mDBHelper = DBHelper.getInstance(mContext) ;
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        db.close() ;
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
//        SQLiteDatabase db
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }


    //    @Override
//    public boolean onCreate() {
////        mContext = getContext();
//
//        return false;
//    }
//
//    @Override
//    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
//        return null;
//    }
//
//    @Override
//    public String getType(Uri uri) {
//        return null;
//    }
//
//    @Override
//    public Uri insert(Uri uri, ContentValues contentValues) {
//        return null;
//    }
//
//    @Override
//    public int delete(Uri uri, String s, String[] strings) {
//        return 0;
//    }
//
//    @Override
//    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
//        return 0;
//    }
}
