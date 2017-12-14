package com.example.liang.siruanbei.activity.rijiben;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.activity.RiJiBen;
import com.example.liang.siruanbei.db.DiaryDatabaseHelper;
import com.example.liang.siruanbei.utils.AppManager;
import com.example.liang.siruanbei.utils.GetDate;
import com.example.liang.siruanbei.utils.StatusBarCompat;
import com.example.liang.siruanbei.widget.LinedEditText;

import cc.trity.floatingactionbutton.FloatingActionButton;
import cc.trity.floatingactionbutton.FloatingActionsMenu;


public class UpdateDiaryActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mUpdateDiaryTvDate;
    EditText mUpdateDiaryEtTitle;
    LinedEditText mUpdateDiaryEtContent;
    FloatingActionButton mUpdateDiaryFabBack;
    FloatingActionButton mUpdateDiaryFabAdd;
    FloatingActionButton mUpdateDiaryFabDelete;
    FloatingActionsMenu mRightLabels;
    TextView mCommonTvTitle;
    LinearLayout mCommonTitleLl;
    ImageView mCommonIvBack;
    ImageView mCommonIvTest;
    TextView mTvTag;

    private DiaryDatabaseHelper mHelper;

    private void init() {
        mUpdateDiaryTvDate= (TextView) findViewById(R.id.update_diary_tv_date);
        mUpdateDiaryEtTitle= (EditText) findViewById(R.id.update_diary_et_title);
        mUpdateDiaryEtContent= (LinedEditText) findViewById(R.id.update_diary_et_content);
        mUpdateDiaryFabBack= (FloatingActionButton) findViewById(R.id.update_diary_fab_back);
        mUpdateDiaryFabAdd= (FloatingActionButton) findViewById(R.id.update_diary_fab_add);
        mUpdateDiaryFabDelete= (FloatingActionButton) findViewById(R.id.update_diary_fab_delete);
        mRightLabels= (FloatingActionsMenu) findViewById(R.id.right_labels);
        mCommonTvTitle= (TextView) findViewById(R.id.common_tv_title);
        mCommonTitleLl= (LinearLayout) findViewById(R.id.common_title_ll);
        mCommonIvBack= (ImageView) findViewById(R.id.common_iv_back);
        mCommonIvTest= (ImageView) findViewById(R.id.common_iv_test);
        mTvTag= (TextView) findViewById(R.id.update_diary_tv_tag);
        mCommonIvBack.setOnClickListener(this);
        mUpdateDiaryTvDate.setOnClickListener(this);
        mUpdateDiaryEtTitle.setOnClickListener(this);
        mUpdateDiaryEtContent.setOnClickListener(this);
        mUpdateDiaryFabBack.setOnClickListener(this);
        mUpdateDiaryFabAdd.setOnClickListener(this);
        mUpdateDiaryFabDelete.setOnClickListener(this);
    }

    public static void startActivity(Context context, String title, String content, String tag) {
        Intent intent = new Intent(context, UpdateDiaryActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("tag", tag);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_diary);
        AppManager.getAppManager().addActivity(this);
        init();
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
        initTitle();
        StatusBarCompat.compat(this, Color.parseColor("#161414"));

        Intent intent = getIntent();
        mUpdateDiaryTvDate.setText("今天，" + GetDate.getDate());
        mUpdateDiaryEtTitle.setText(intent.getStringExtra("title"));
        mUpdateDiaryEtContent.setText(intent.getStringExtra("content"));
        mTvTag.setText(intent.getStringExtra("tag"));
    }

    private void initTitle() {
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
        mCommonTvTitle.setText("修改日记");
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //RiJiBen.startActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.common_iv_back:
                RiJiBen.startActivity(this);
            case R.id.update_diary_tv_date:
                break;
            case R.id.update_diary_et_title:
                break;
            case R.id.update_diary_et_content:
                break;
            case R.id.update_diary_fab_back:
                android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("确定要删除该日记吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

//                        String title = mUpdateDiaryEtTitle.getText().toString();
                        String tag = mTvTag.getText().toString();
                        SQLiteDatabase dbDelete = mHelper.getWritableDatabase();
                        dbDelete.delete("Diary", "tag = ?", new String[]{tag});
                        RiJiBen.startActivity(UpdateDiaryActivity.this);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
                break;
            case R.id.update_diary_fab_add:
                SQLiteDatabase dbUpdate = mHelper.getWritableDatabase();
                ContentValues valuesUpdate = new ContentValues();
                String title = mUpdateDiaryEtTitle.getText().toString();
                String content = mUpdateDiaryEtContent.getText().toString();
                valuesUpdate.put("title", title);
                valuesUpdate.put("content", content);
                dbUpdate.update("Diary", valuesUpdate, "title = ?", new String[]{title});
                dbUpdate.update("Diary", valuesUpdate, "content = ?", new String[]{content});
                RiJiBen.startActivity(this);
                break;
            case R.id.update_diary_fab_delete:
                RiJiBen.startActivity(this);

                break;
        }
    }
}