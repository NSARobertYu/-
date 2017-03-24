package com.example.project_myqq2.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC on 2016/10/10.
 */
public class MessageDBHelper extends SQLiteOpenHelper {

    public MessageDBHelper(Context context) {
        super(context,"Message.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table message(_id integer primary key autoincrement," +
                "message text," +
                "type int," +
                "time long)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
