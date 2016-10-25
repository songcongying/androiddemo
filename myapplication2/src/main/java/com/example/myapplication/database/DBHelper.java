package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.example.myapplication.model.WordModel;

/**
 * Created by Administrator on 2016/10/24.
 */
public class DBHelper extends SQLiteOpenHelper  {

    /** 数据库名称 */
    private static String DATABASE_NAME = "word_remember.db";
    public static final String DATA_BASE_AUTHORITY = "com.haixiaoxing.database";
    public static String WORD_DB_URL = "content://" + DATA_BASE_AUTHORITY +"/";

    /** 数据库版本号 */
    public static int VERSION_CODE = 1;
    private static DBHelper mSingleInstance;
    private Context mContext;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_CODE);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + WordModel.TABLE_NAME + " (_id INTEGER PRiMARY KEY AUTOINCREMENT,"
                + " " + WordModel.ENGLISH_WORD + " TEXT,"
                + " " + WordModel.ENGLISH_WORD + " TEXT,"
                + " " + WordModel.TRANSLATION + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + WordModel.TABLE_NAME);
        onCreate(db);
    }

        public synchronized static DBHelper getInstance(Context context) {
            if (mSingleInstance == null) {
                mSingleInstance = new DBHelper(mContext);
            }
        return mSingleInstance;
    }
}
