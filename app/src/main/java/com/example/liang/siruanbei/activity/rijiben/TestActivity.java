package com.example.liang.siruanbei.activity.rijiben;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.liang.siruanbei.R;


/**
 * Created by developerHaoz on 2017/5/11.
 */

public class TestActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "TestActivity";


    Button mButton1;

    Button mButton2;

    Button mButton3;

    Button mButton4;

    private void init() {
        mButton1=(Button)findViewById(R.id.button1);
        mButton2=(Button)findViewById(R.id.button2);
        mButton3=(Button)findViewById(R.id.button3);
        mButton4=(Button)findViewById(R.id.button4);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Log.d(TAG, "onViewClicked: You Click button1");
                break;
            case R.id.button2:
                Log.d(TAG, "onViewClicked: You Click button2");
                break;
            case R.id.button3:
                Log.d(TAG, "onViewClicked: You Click button3");
                break;
        }
    }
}
