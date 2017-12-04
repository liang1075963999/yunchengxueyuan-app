package com.example.liang.siruanbei.shujuku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liang on 2017/11/26.
 */

public class zuijinlishishujuku extends SQLiteOpenHelper {
    public zuijinlishishujuku(Context context) {
        super(context, "zuijinlishi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String itemSQL = "CREATE TABLE lishi( "+"tupian varchar(35) primary key,zhengwen varchar(200),date varchar(20))";
        db.execSQL(itemSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}