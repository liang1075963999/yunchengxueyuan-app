package com.example.liang.siruanbei.activity.rijiben;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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


public class AddDiaryActivity extends AppCompatActivity implements View.OnClickListener{

    TextView mAddDiaryTvDate;
    EditText mAddDiaryEtTitle;
    LinedEditText mAddDiaryEtContent;
    FloatingActionButton mAddDiaryFabBack;
    FloatingActionButton mAddDiaryFabAdd;

    FloatingActionsMenu mRightLabels;

    TextView mCommonTvTitle;

    LinearLayout mCommonTitleLl;

    ImageView mCommonIvBack;

    ImageView mCommonIvTest;

    private DiaryDatabaseHelper mHelper;

    private void init() {
        mAddDiaryTvDate=(TextView)findViewById(R.id.add_diary_tv_date);
        mAddDiaryEtTitle= (EditText) findViewById(R.id.add_diary_et_title);
        mAddDiaryEtContent=(LinedEditText)findViewById(R.id.add_diary_et_content);
        mAddDiaryFabBack=(FloatingActionButton)findViewById(R.id.add_diary_fab_back);
        mAddDiaryFabAdd=(FloatingActionButton)findViewById(R.id.add_diary_fab_add);
        mRightLabels=(FloatingActionsMenu)findViewById(R.id.right_labels);
        mCommonTvTitle=(TextView)findViewById(R.id.common_tv_title);
        mCommonTitleLl=(LinearLayout)findViewById(R.id.common_title_ll);
        mCommonIvBack=(ImageView)findViewById(R.id.common_iv_back);
        mCommonIvTest=(ImageView)findViewById(R.id.common_iv_test);
        mCommonIvBack.setOnClickListener(this);
        mAddDiaryEtTitle.setOnClickListener(this);
        mAddDiaryEtContent.setOnClickListener(this);
        mAddDiaryFabBack.setOnClickListener(this);
        mAddDiaryFabAdd.setOnClickListener(this);
    }
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AddDiaryActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String title, String content) {
        Intent intent = new Intent(context, AddDiaryActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);
        init();
        AppManager.getAppManager().addActivity(this);
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
        Intent intent = getIntent();
        mAddDiaryEtTitle.setText(intent.getStringExtra("title"));
        StatusBarCompat.compat(this, Color.parseColor("#161414"));

        mCommonTvTitle.setText("添加日记");
        mAddDiaryTvDate.setText("今天，" + GetDate.getDate());
        mAddDiaryEtContent.setText(intent.getStringExtra("content"));
        mHelper = new DiaryDatabaseHelper(this, "Diary.db", null, 1);
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
            case R.id.add_diary_et_title:
                break;
            case R.id.add_diary_et_content:
                break;
            case R.id.add_diary_fab_back:
                String date = GetDate.getDate().toString();
                String tag = String.valueOf(System.currentTimeMillis());
                String title = mAddDiaryEtTitle.getText().toString() + "";
                String content = mAddDiaryEtContent.getText().toString() + "";
                if (!title.equals("") || !content.equals("")) {
                    SQLiteDatabase db = mHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("date", date);
                    values.put("title", title);
                    values.put("content", content);
                    values.put("tag", tag);
                    db.insert("Diary", null, values);
                    values.clear();
                }
                RiJiBen.startActivity(this);
                break;
            case R.id.add_diary_fab_add:
                final String dateBack = GetDate.getDate().toString();
                final String titleBack = mAddDiaryEtTitle.getText().toString();
                final String contentBack = mAddDiaryEtContent.getText().toString();
                if(!titleBack.isEmpty() || !contentBack.isEmpty()){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setMessage("是否保存日记内容？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            SQLiteDatabase db = mHelper.getWritableDatabase();
                            ContentValues values = new ContentValues();
                            values.put("date", dateBack);
                            values.put("title", titleBack);
                            values.put("content", contentBack);
                            db.insert("Diary", null, values);
                            values.clear();
                            RiJiBen.startActivity(AddDiaryActivity.this);
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //RiJiBen.startActivity(AddDiaryActivity.this);
                        }
                    }).show();
                }else{
                    RiJiBen.startActivity(this);
                }
                break;
        }
    }
}











