package com.example.liang.siruanbei.shujuku;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.liang.siruanbei.bean.lishibean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/11/26.
 */

public class lishicaozuo {
    private zuijinlishishujuku shujuku;
    private int i=0;
    public lishicaozuo(Context context) {
        this.shujuku = new zuijinlishishujuku(context);
    }

    public void shoucang(lishibean lishibean) {
        SQLiteDatabase sqLiteDatabase = this.shujuku.getWritableDatabase();
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = lishibean.getTupian();
        arrayOfObject[1] = lishibean.getZhengwen();
        arrayOfObject[2] = lishibean.getDate();
        sqLiteDatabase.execSQL("insert into lishi(tupian,zhengwen,date) values(?,?,?)", arrayOfObject);
        sqLiteDatabase.close();
    }

    public List<lishibean> xianshi() {
        List<lishibean> localArrayList = new ArrayList<lishibean>();
        SQLiteDatabase localSQLiteDatabase = this.shujuku.getWritableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select * from lishi " +
                "where 1=1", null);
        while (localCursor.moveToNext()&&(i<10)) {
            lishibean temp = new lishibean();
            temp.setTupian(localCursor.getString(localCursor.getColumnIndex("tupian")));
            temp.setZhengwen(localCursor.getString(localCursor.getColumnIndex("zhengwen")));
            temp.setDate(localCursor.getString(localCursor.getColumnIndex("date")));
            localArrayList.add(temp);
            i++;
        }
        localSQLiteDatabase.close();
        return localArrayList;
    }
}