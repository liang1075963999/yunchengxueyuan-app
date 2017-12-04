package com.example.liang.siruanbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.example.liang.siruanbei.R;

/**
 * Created by liang on 2017/10/17.
 */

public class itemactivity extends ActionBarActivity {
    private WebView webView;
    private Intent intent;
    private String wangzhi;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemactivity);
        webView= (WebView) findViewById(R.id.web_view);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        intent=getIntent();
        wangzhi=intent.getStringExtra("wangzhi");
        webView.loadUrl(wangzhi);
        webView.setWebChromeClient(new WebChromeClient());
        toolbar.setTitle("运城学院");
        setSupportActionBar(toolbar);
    }
}
