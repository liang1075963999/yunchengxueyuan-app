package com.example.liang.siruanbei;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;

/**
 * Created by liang on 2017/11/26.
 */
public class Guanyuwomen extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guanyuwomen);
        getWindow().setEnterTransition(new Slide().setDuration(1000));
    }
}
