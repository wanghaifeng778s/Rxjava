package com.feng.com.rxjavade.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WHF.Javas on 2017/9/14.
 */

public class DpOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "book_provider_db";//数据库名字
    public static final  String BOOK_TABLE_NAME = "book";//表名
    public static final String CREATE_TABLE_BOOK = "create table " + BOOK_TABLE_NAME
            + "(_id INTEGER PRIMARY KEY ," + "name TEXT)";//创建表语句

    public DpOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }

    public DpOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
