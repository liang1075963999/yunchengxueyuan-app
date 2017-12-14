package com.example.liang.siruanbei.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.activity.rijiben.AddDiaryActivity;
import com.example.liang.siruanbei.activity.rijiben.DiaryAdapter;
import com.example.liang.siruanbei.activity.rijiben.UpdateDiaryActivity;
import com.example.liang.siruanbei.bean.DiaryBean;
import com.example.liang.siruanbei.db.DiaryDatabaseHelper;
import com.example.liang.siruanbei.event.StartUpdateDiaryEvent;
import com.example.liang.siruanbei.utils.AppManager;
import com.example.liang.siruanbei.utils.GetDate;
import com.example.liang.siruanbei.utils.SpHelper;
import com.example.liang.siruanbei.utils.StatusBarCompat;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by liang on 2017/12/10.
 */

public class RiJiBen extends AppCompatActivity implements View.OnClickListener {

    ImageView mCommonIvBack;
    TextView mCommonTvTitle;
    ImageView mCommonIvTest;
    LinearLayout mCommonTitleLl;
    ImageView mMainIvCircle;
    TextView mMainTvDate;
    TextView mMainTvContent;
    LinearLayout mItemLlControl;

    RecyclerView mMainRvShowDiary;
    FloatingActionButton mMainFabEnterEdit;
    RelativeLayout mMainRlMain;
    LinearLayout mItemFirst;
    LinearLayout mMainLlMain;
    private List<DiaryBean> mDiaryBeanList;

    private DiaryDatabaseHelper mHelper;

    private static String IS_WRITE = "true";

    private int mEditPosition = -1;

    /**
     * 标识今天是否已经写了日记
     */
    private boolean isWrite = false;
    private static TextView mTvTest;
    private Toolbar toolbar;
    private void init() {
        mCommonIvBack = (ImageView) findViewById(R.id.common_iv_back);
        mCommonTvTitle = (TextView) findViewById(R.id.common_tv_title);
        mCommonIvTest = (ImageView) findViewById(R.id.common_iv_test);
        mCommonTitleLl = (LinearLayout) findViewById(R.id.common_title_ll);
        mMainIvCircle = (ImageView) findViewById(R.id.main_iv_circle);
        mMainTvDate = (TextView) findViewById(R.id.main_tv_date);
        mMainTvContent = (TextView) findViewById(R.id.main_tv_content);
        mItemLlControl = (LinearLayout) findViewById(R.id.item_ll_control);
        mMainRvShowDiary = (RecyclerView) findViewById(R.id.main_rv_show_diary);
        mMainFabEnterEdit = (FloatingActionButton) findViewById(R.id.main_fab_enter_edit);
        mMainRlMain = (RelativeLayout) findViewById(R.id.main_rl_main);
        mItemFirst = (LinearLayout) findViewById(R.id.item_first);
        mMainLlMain = (LinearLayout) findViewById(R.id.main_ll_main);
        mMainFabEnterEdit.setOnClickListener(this);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, RiJiBen.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rijiben);
        AppManager.getAppManager().addActivity(this);
        init();
        StatusBarCompat.compat(this, Color.parseColor("#161414"));
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
        EventBus.getDefault().register(this);
        SpHelper spHelper = SpHelper.getInstance(this);
        getDiaryBeanList();
        initTitle();
        mMainRvShowDiary.setLayoutManager(new LinearLayoutManager(this));
        mMainRvShowDiary.setAdapter(new DiaryAdapter(this, mDiaryBeanList));
        mTvTest = new TextView(this);
        mTvTest.setText("hello world");
    }

    private void initTitle() {
        mMainTvDate.setText("今天，" + GetDate.getDate());
        mCommonTvTitle.setText("日记本");
        mCommonIvBack.setVisibility(View.INVISIBLE);
        mCommonIvTest.setVisibility(View.INVISIBLE);

    }

    private List<DiaryBean> getDiaryBeanList() {

        mDiaryBeanList = new ArrayList<>();
        List<DiaryBean> diaryList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = mHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query("Diary", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String dateSystem = GetDate.getDate().toString();
                if (date.equals(dateSystem)) {
                    mMainLlMain.removeView(mItemFirst);
                    break;
                }
            } while (cursor.moveToNext());
        }


        if (cursor.moveToFirst()) {
            do {
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String tag = cursor.getString(cursor.getColumnIndex("tag"));
                mDiaryBeanList.add(new DiaryBean(date, title, content, tag));
            } while (cursor.moveToNext());
        }
        cursor.close();

        for (int i = mDiaryBeanList.size() - 1; i >= 0; i--) {
            diaryList.add(mDiaryBeanList.get(i));
        }

        mDiaryBeanList = diaryList;
        return mDiaryBeanList;
    }

    @Subscribe
    public void startUpdateDiaryActivity(StartUpdateDiaryEvent event) {
        String title = mDiaryBeanList.get(event.getPosition()).getTitle();
        String content = mDiaryBeanList.get(event.getPosition()).getContent();
        String tag = mDiaryBeanList.get(event.getPosition()).getTag();
        UpdateDiaryActivity.startActivity(this, title, content, tag);
        Log.i("xinxi","我执行了");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        Log.i("xinxi","onDestroy");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppManager.getAppManager().AppExit(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_fab_enter_edit)
            AddDiaryActivity.startActivity(this);
    }
}