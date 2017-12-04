package com.example.liang.siruanbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.widget.Button;

import com.example.liang.siruanbei.R;

/**
 * Created by liang on 2017/10/27.
 */

public class xiangqing extends AppCompatActivity {
    private Intent intent;
    private String s;
    private Button textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiangqing);
        textView = (Button) findViewById(R.id.textView);
        getWindow().setEnterTransition(new Explode().setDuration(1000));
        //getWindow().setExitTransition(new Fade().setDuration(100));
        intent = getIntent();
        s = intent.getStringExtra("zhi");
        textView.setText(s);
    }
}
