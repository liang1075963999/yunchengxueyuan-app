package com.example.liang.siruanbei.shujuku;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.liang.siruanbei.bean.itembean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/11/26.
 */

public class itemshujukucaozuo {
    private itemshujuku shujuku;

    public itemshujukucaozuo(Context context) {
        this.shujuku = new itemshujuku(context);
    }

    public void shoucang(itembean itembean) {
        SQLiteDatabase sqLiteDatabase = this.shujuku.getWritableDatabase();
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = itembean.getTupian();
        arrayOfObject[1] = itembean.getZhengwen();
        sqLiteDatabase.execSQL("insert into item(tupian,zhengwen) values(?,?)", arrayOfObject);
        sqLiteDatabase.close();
    }

    public List<itembean> xianshi() {
        List<itembean> localArrayList = new ArrayList<itembean>();
        SQLiteDatabase localSQLiteDatabase = this.shujuku.getWritableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select * from item " +
                "where 1=1", null);
        while (localCursor.moveToNext()) {
            itembean temp = new itembean();
            temp.setTupian(localCursor.getString(localCursor.getColumnIndex("tupian")));
            temp.setZhengwen(localCursor.getString(localCursor.getColumnIndex("zhengwen")));
            localArrayList.add(temp);
        }
        localSQLiteDatabase.close();
        return localArrayList;
    }

    public void delete(String title) {
        SQLiteDatabase localSQLiteDatabase = this.shujuku.getWritableDatabase();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = title;
        localSQLiteDatabase.execSQL("delete from item where tupian=?", arrayOfObject);
        localSQLiteDatabase.close();
    }
}