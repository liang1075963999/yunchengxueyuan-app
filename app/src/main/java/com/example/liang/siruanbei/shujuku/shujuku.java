package com.example.liang.siruanbei.shujuku;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class shujuku extends SQLiteOpenHelper {
    public shujuku(Context context) {
        super(context, "kebiaoshujuan.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String kebiaoSQL = "CREATE TABLE kebiao( "+"courseName varchar(15) ,section int ,sectionSpan int,week int,classRoom varchar(15),courseFlag int)";
        db.execSQL(kebiaoSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
