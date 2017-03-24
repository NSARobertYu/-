package com.example.project_myqq2.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/10/10.
 */
public class MessageDao {

    private Context Context;
    public MessageDao(Context Context){
        this.Context = Context;
    }

    //分页显示的方法
    public List<com.example.project_myqq2.util.Message> queryByPage(int page, int size, long time){
        MessageDBHelper helper = new MessageDBHelper(Context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from message where time < ? order by time limit?,?",
                new String[]{String.valueOf(time),String.valueOf((page-1)*size),String.valueOf(size)});
        List<com.example.project_myqq2.util.Message> list = new ArrayList<Message>();
        while(cursor.moveToNext()){
            String message = cursor.getString(cursor.getColumnIndex("message"));
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            long time1 =  cursor.getLong(cursor.getColumnIndex("time"));
            int type = cursor.getType(cursor.getColumnIndex("type"));
            com.example.project_myqq2.util.Message m = new com.example.project_myqq2.util.Message(
                    id,message,time1,type);
            list.add(m);
        }

        cursor.close();
        db.close();
        helper.close();
        return list;
    }

    //总计多少条数据
    public long total (long time) {
        MessageDBHelper helper = new MessageDBHelper(Context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from message where time < ?",
                new String[]{String.valueOf(time)});
        long total = 0;
        while(cursor.moveToNext()){
            total = cursor.getLong(0);
        }

        cursor.close();
        db.close();
        helper.close();
        return total;
    }
    //添加的方法
    public void add(com.example.project_myqq2.util.Message message){

        MessageDBHelper dbHelper = new MessageDBHelper(Context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("message",message.getMessage());
        values.put("type",message.getType());
        values.put("time",message.getTime());

        db.insert("message",null,values);
        db.close();
        dbHelper.close();

    }

}
