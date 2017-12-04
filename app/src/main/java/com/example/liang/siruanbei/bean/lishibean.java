package com.example.liang.siruanbei.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liang on 2017/11/26.
 */

public class lishibean implements Parcelable {
    String tupian;
    String zhengwen;
    String date;

    public String getTupian() {
        return tupian;
    }

    public void setTupian(String tupian) {
        this.tupian = tupian;
    }

    public String getZhengwen() {
        return zhengwen;
    }

    public void setZhengwen(String zhengwen) {
        this.zhengwen = zhengwen;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tupian);
        dest.writeString(this.zhengwen);
        dest.writeString(this.date);
    }

    public lishibean() {
    }

    protected lishibean(Parcel in) {
        this.tupian = in.readString();
        this.zhengwen = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<lishibean> CREATOR = new Parcelable.Creator<lishibean>() {
        @Override
        public lishibean createFromParcel(Parcel source) {
            return new lishibean(source);
        }

        @Override
        public lishibean[] newArray(int size) {
            return new lishibean[size];
        }
    };
}
