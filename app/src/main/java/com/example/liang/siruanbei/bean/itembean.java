package com.example.liang.siruanbei.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liang on 2017/11/26.
 */

public class itembean implements Parcelable {
    String tupian;
    String zhengwen;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tupian);
        dest.writeString(this.zhengwen);
    }

    public itembean() {
    }

    protected itembean(Parcel in) {
        this.tupian = in.readString();
        this.zhengwen = in.readString();
    }

    public static final Parcelable.Creator<itembean> CREATOR = new Parcelable.Creator<itembean>() {
        @Override
        public itembean createFromParcel(Parcel source) {
            return new itembean(source);
        }

        @Override
        public itembean[] newArray(int size) {
            return new itembean[size];
        }
    };
}
