package com.example.liang.siruanbei.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by liang on 2017/10/18.
 */

public class shipin extends BmobObject {
    String title;
    String href;
    String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
