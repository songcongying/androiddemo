/*
 * Copyright (C) 2015 Geedeen.
 * All rights, including trade secret rights, reserved.
 */
package com.example.myapplication.database;


import com.geedeen.videocall.model.Account;
import com.geedeen.videocall.model.Friends;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


/**
 * 
 * @author xingyuna
 * 
 */
public class VideoCallContentProvider extends ContentProvider {

    private DBHelperaa mHelper;
    private Context context;

    //查询好友列表，删除好友（请求列表和好友列表）（两个）
    public static final int FRIENDS_CODE = 1;
    //删除好友（显示在好友列表的数据）
    public static final int FRIENDS_DEL_MAIN_CODE = 111;
    public static final String FRIENDS_DEL_MAIN_URI = "friends_del_main";
    //查询好友列表按照UID排序
    public static final int FRIENDS_REFRESH_CODE = 121;
    public static final String FRIENDS_REFRESH_URI = "friends_refresh";
    //删除好友列表中数据
    public static final int FRIENDS_DEL_CODE = 11;
    public static final String FRIENDS_DEL_URI = "friends_del";
    //删除请求好友列表中数据
    public static final int REQUEST_DEL_CODE = 12;
    public static final String REQUEST_DEL_URI = "request_del";
    //接受好友请求
    public static final int AGREE_FRIENDS_CODE = 13;
    public static final String AGREE_FRIENDS_URI = "agree_friends";
    //被接受好友请求
    public static final int ISAGREED_FRIENDS_CODE = 131;
    public static final String ISAGREED_FRIENDS_URI = "isagreed_friends";
    //根据UID查询好友
    public static final int FRIENDS_UID_CODE = 14;
    public static final String FRIENDS_UID_URI = "friends_uid";
    //好友请求列表
    public static final int REQUEST_CODE = 5;
    public static final String REQUEST_TABLE = "request_table";
    public static final int ACCOUNT_CODE = 3;
    
    //0是只在好友表中
    public static final int ONLY_FRIENDS_TABLE = 0;
    //1是请求表中未接受
    public static final int REQUEST_TABLE_NOT_ACCEPT = 1;
    //2是好友表中已接受
    public static final int FRIEND_TABLE_ACCEPT = 2;
    //3是只在请求表中
    public static final int ONLY_REQUEST = 3;
    //4是发送添加好友请求
    public static final int SEND_ADD_REQUEST = 4;

    public static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, Account.TABLE_NAME,
                ACCOUNT_CODE);
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, Friends.TABLE_NAME,
                FRIENDS_CODE);
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, REQUEST_TABLE,
                REQUEST_CODE);
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, FRIENDS_DEL_URI,
                FRIENDS_DEL_CODE);
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, REQUEST_DEL_URI,
                REQUEST_DEL_CODE);
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, AGREE_FRIENDS_URI,
                AGREE_FRIENDS_CODE);
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, FRIENDS_UID_URI,
                FRIENDS_UID_CODE);
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, ISAGREED_FRIENDS_URI,
                ISAGREED_FRIENDS_CODE);
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, FRIENDS_DEL_MAIN_URI,
                FRIENDS_DEL_MAIN_CODE);
        matcher.addURI(DBHelperaa.DATA_BASE_AUTHORITY, FRIENDS_REFRESH_URI,
                FRIENDS_REFRESH_CODE);
    }

    @Override
    public boolean onCreate() {
        context = getContext();
        mHelper = DBHelperaa.getInstance(context);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.close();


        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = null;
        int macherCode = matcher.match(uri);
        try {

            switch (macherCode) {
            case ACCOUNT_CODE: 
                cursor = db.query(Account.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
            case FRIENDS_CODE: 
                selectionArgs[0] = ONLY_FRIENDS_TABLE+"";
                selectionArgs[1] = FRIEND_TABLE_ACCEPT+"";
                cursor = db.query(Friends.TABLE_NAME, projection, "witchtable = ? or witchtable = ?",
                        selectionArgs, null, null, "lasttime_call desc");
                break;
            case FRIENDS_REFRESH_CODE: 
//                selectionArgs[0] = ONLY_FRIENDS_TABLE+"";
//                selectionArgs[1] = FRIEND_TABLE_ACCEPT+"";
                cursor = db.query(Friends.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, null);
                break;
            case REQUEST_CODE: 
                String selectionTem[] = new String[3];
                selectionTem[0] = REQUEST_TABLE_NOT_ACCEPT+"";
                selectionTem[1] = ONLY_REQUEST+"";
                selectionTem[2] = FRIEND_TABLE_ACCEPT+"";
                cursor = db.query(Friends.TABLE_NAME, projection, "witchtable = ? or witchtable = ? or witchtable =?",
                        selectionTem, null, null, "lasttime_request desc");
                break;
            case FRIENDS_UID_CODE: 
                cursor = db.query(Friends.TABLE_NAME, projection, selection,
                        selectionArgs, null, null, sortOrder);
                break;
//            case STATUS_LINK_FRIENDS_CODE: 
//                String sql = "select * from "+Friends.TABLE_NAME +" f left join "+ Status.TABLE_NAME
//                +" s on f.fid = s.fid where s.witchtable = 0 or s.witchtable = 2 order by s.lasttime_call";
//                cursor = db.rawQuery(sql, null);
//                break;
//            case STATUS_LINK_REQUEST_CODE: 
//                String sql1 = "select * from "+Friends.TABLE_NAME +" f left join "+ Status.TABLE_NAME
//                +" s on f.fid = s.fid where s.witchtable = 3 or s.witchtable = 2 order by s.lasttime_request";
//                cursor = db.rawQuery(sql1, null);
//                break;
            default:
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri result = null;
        long rowID = 0;
        int matcherCode = matcher.match(uri);
        SQLiteDatabase db = mHelper.getWritableDatabase();
        try {
            switch (matcherCode) {
            case FRIENDS_CODE: 
                rowID = db.insert(Friends.TABLE_NAME, Friends.FNAME,
                        values);
                break;
            case ACCOUNT_CODE: 
                rowID = db.insert(Account.TABLE_NAME, Friends.FNAME,
                        values);
                break;
            default:
                break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rowID > 0) {
            result = Uri.parse(uri + "/" + rowID);
        }

        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int macherCode = matcher.match(uri);
        int deletResult = -1;
        try {
            switch (macherCode) {
            case FRIENDS_CODE:
                deletResult = db.delete(Friends.TABLE_NAME, selection,
                        selectionArgs);
                break;
            case FRIENDS_DEL_MAIN_CODE:
//                String[] selectionArgsTemp = new String[2];
//                selectionArgsTemp[0] = ONLY_FRIENDS_TABLE+"";
//                selectionArgsTemp[1] = FRIEND_TABLE_ACCEPT+"";
                deletResult = db.delete(Friends.TABLE_NAME, selection,
                		selectionArgs);
                break;
            default:
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // db.close();
        return deletResult;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int matcherCode = matcher.match(uri);
        int updateResult = -1;
        
        switch (matcherCode) {
        case FRIENDS_CODE:
            try {
                updateResult = db.update(Friends.TABLE_NAME, values, selection,
                        selectionArgs);

                getContext().getContentResolver().notifyChange(uri, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case FRIENDS_DEL_CODE:
            ContentValues values_temp = new ContentValues();
            values_temp.put(Friends.WITCHTABLE, "3");
            try {
                updateResult = db.update(Friends.TABLE_NAME, values_temp, selection,
                        selectionArgs);

                getContext().getContentResolver().notifyChange(uri, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case REQUEST_DEL_CODE:
            ContentValues values_temp_del = new ContentValues();
            values_temp_del.put(Friends.WITCHTABLE, "0");
            try {
                updateResult = db.update(Friends.TABLE_NAME, values_temp_del, selection,
                        selectionArgs);

                getContext().getContentResolver().notifyChange(uri, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case AGREE_FRIENDS_CODE:
            ContentValues values_temp_agree = new ContentValues();
            values_temp_agree.put(Friends.WITCHTABLE, "2");
            try {
                updateResult = db.update(Friends.TABLE_NAME, values_temp_agree, selection,
                        selectionArgs);

                getContext().getContentResolver().notifyChange(uri, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        case ISAGREED_FRIENDS_CODE:
            ContentValues values_temp_isagreed = new ContentValues();
            values_temp_isagreed.put(Friends.WITCHTABLE, "0");
            try {
                updateResult = db.update(Friends.TABLE_NAME, values_temp_isagreed, selection,
                        selectionArgs);

                getContext().getContentResolver().notifyChange(uri, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        default:
            break;
        }
        // db.close();
        return updateResult;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        int numValues = 0;
        db.beginTransaction(); // 
        try {
            // 
            numValues = values.length;
            for (int i = 0; i < numValues; i++) {
                insert(uri, values[i]);
            }
            db.setTransactionSuccessful(); // 
        } finally {
            db.endTransaction(); // 
        }
        return numValues;
    }
}
