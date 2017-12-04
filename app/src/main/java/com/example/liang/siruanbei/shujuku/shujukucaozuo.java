package com.example.liang.siruanbei.shujuku;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.liang.siruanbei.kebiao.CourseModel;

import java.util.ArrayList;
import java.util.List;

public class shujukucaozuo {
    private shujuku shujuku;

    public shujukucaozuo(Context context) {
        this.shujuku = new shujuku(context);
    }

    public void shoucang(CourseModel courseModel) {
        SQLiteDatabase sqLiteDatabase = this.shujuku.getWritableDatabase();
        Object[] arrayOfObject = new Object[6];
        arrayOfObject[0] = courseModel.getCourseName();
        arrayOfObject[1] = courseModel.getSection();
        arrayOfObject[2] = courseModel.getSectionSpan();
        arrayOfObject[3] = courseModel.getWeek();
        arrayOfObject[4] = courseModel.getClassRoom();
        arrayOfObject[5] = courseModel.getCourseFlag();
        sqLiteDatabase.execSQL("insert into kebiao(courseName,section,sectionSpan,week,classRoom,courseFlag) values(?,?,?,?,?,?)", arrayOfObject);
        sqLiteDatabase.close();
    }

    public List<CourseModel> xianshi() {
        List<CourseModel> localArrayList = new ArrayList<CourseModel>();
        SQLiteDatabase localSQLiteDatabase = this.shujuku.getWritableDatabase();
        Cursor localCursor = localSQLiteDatabase.rawQuery("select * from kebiao " +
                "where 1=1", null);
        while (localCursor.moveToNext()) {
            CourseModel temp = new CourseModel();
            temp.setCourseName(localCursor.getString(localCursor.getColumnIndex("courseName")));
            temp.setSection(localCursor.getInt(localCursor.getColumnIndex("section")));
            temp.setSectionSpan(localCursor.getInt(localCursor.getColumnIndex("sectionSpan")));
            temp.setWeek(localCursor.getInt(localCursor.getColumnIndex("week")));
            temp.setClassRoom(localCursor.getString(localCursor.getColumnIndex("classRoom")));
            temp.setCourseFlag(localCursor.getInt(localCursor.getColumnIndex("courseFlag")));
            localArrayList.add(temp);
        }
        localSQLiteDatabase.close();
        return localArrayList;
    }

    public void delete(String title) {
        SQLiteDatabase localSQLiteDatabase = this.shujuku.getWritableDatabase();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = title;
        localSQLiteDatabase.execSQL("delete from kebiao where id=?", arrayOfObject);
        localSQLiteDatabase.close();
    }
}
