package com.example.liang.siruanbei;

import android.util.Log;

import com.example.liang.siruanbei.bean.dongtaineirong;
import com.example.liang.siruanbei.bean.jiaoxueneirong;
import com.example.liang.siruanbei.bean.news;
import com.example.liang.siruanbei.bean.newsneirong;
import com.example.liang.siruanbei.bean.shipin;
import com.example.liang.siruanbei.bean.tongzhi;
import com.example.liang.siruanbei.bean.xibuneirong;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class gongju{
    private static List<news> resultList;
    private static List<shipin> shipinList;
    private static List<tongzhi> tongzhiList;
    private static List<xibuneirong> xibuList;
    private static List<newsneirong> newsneirongList;
    private static List<dongtaineirong> dongtaineirongList;
    private static List<jiaoxueneirong> jiaoxueneirongList;
    private static BmobQuery a;
    public static List<news> getList() {
        a = new BmobQuery("news");
        a.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                resultList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        news n = new news();
                        n.setDate(jsonArray.getJSONObject(i).getString("date"));
                        n.setHref(jsonArray.getJSONObject(i).getString("href"));
                        n.setTitle(jsonArray.getJSONObject(i).getString("title"));
                        Log.i("xinxi", n.getTitle() + "," + n.getDate() + "," + n.getHref());
                        resultList.add(n);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        try{
            Log.i("xingxi", String.valueOf(resultList.size()));
        }catch (Exception e)
        {

        }
        return resultList;
    }
    public static List<shipin> getshipinList() {
        a = new BmobQuery("shipin");
        a.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                shipinList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        shipin n = new shipin();
                        n.setDate(jsonArray.getJSONObject(i).getString("date"));
                        n.setHref(jsonArray.getJSONObject(i).getString("href"));
                        n.setTitle(jsonArray.getJSONObject(i).getString("title"));
                        Log.i("xinxiq", n.getTitle() + "," + n.getDate() + "," + n.getHref());
                        shipinList.add(n);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        try{
            Log.i("xingxi", String.valueOf(shipinList.size()));
        }catch (Exception e)
        {

        }

        return shipinList;
    }
    public static List<tongzhi> gettongzhiList() {
        a = new BmobQuery("tongzhi");
        a.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                tongzhiList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        tongzhi n = new tongzhi();
                        n.setDate(jsonArray.getJSONObject(i).getString("date"));
                        n.setHref(jsonArray.getJSONObject(i).getString("href"));
                        n.setTitle(jsonArray.getJSONObject(i).getString("title"));
                        Log.i("xinxiq", n.getTitle() + "," + n.getDate() + "," + n.getHref());
                        tongzhiList.add(n);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        try{
            Log.i("xingxi", String.valueOf(tongzhiList.size()));
        }catch (Exception e)
        {

        }

        return tongzhiList;
    }
    public static List<xibuneirong> getxibuList() {
        a = new BmobQuery("xibuneirong");
        a.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                xibuList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        xibuneirong n = new xibuneirong();
                        n.setTupian(jsonArray.getJSONObject(i).getString("tupian"));
                        n.setZhengwen(jsonArray.getJSONObject(i).getString("zhengwen"));
                        Log.i("ppp", n.getTupian() + "," + n.getZhengwen() );
                        xibuList.add(n);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        try{
            Log.i("xingxi", String.valueOf(tongzhiList.size()));
        }catch (Exception e)
        {

        }

        return xibuList;
    }
    public static List<newsneirong> getNewsneirongList() {
        a = new BmobQuery("newsneirong");
        a.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                newsneirongList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        newsneirong n = new newsneirong();
                        n.setTupian(jsonArray.getJSONObject(i).getString("tupian"));
                        n.setZhengwen(jsonArray.getJSONObject(i).getString("zhengwen"));
                        //Log.i("aq", n.getTupian() + "," + n.getZhengwen() );
                        newsneirongList.add(n);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        try{
            Log.i("xingxi", String.valueOf(newsneirongList.size()));
        }catch (Exception e)
        {

        }

        return newsneirongList;
    }
    public static List<dongtaineirong> getdongtaineirongList() {
        a = new BmobQuery("dongtaineirong");
        a.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                dongtaineirongList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        dongtaineirong n = new dongtaineirong();
                        n.setTupian(jsonArray.getJSONObject(i).getString("tupian"));
                        n.setZhengwen(jsonArray.getJSONObject(i).getString("zhengwen"));
                        Log.i("aq", n.getTupian() + "," + n.getZhengwen() );
                        dongtaineirongList.add(n);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        try{
            Log.i("xingxi", String.valueOf(dongtaineirongList.size()));
        }catch (Exception e)
        {

        }

        return dongtaineirongList;
    }
    public static List<jiaoxueneirong> getjiaoxueneirongList() {
        a = new BmobQuery("jiaoxueneirong");
        a.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                jiaoxueneirongList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        jiaoxueneirong n = new jiaoxueneirong();
                        n.setTupian(jsonArray.getJSONObject(i).getString("tupian"));
                        n.setZhengwen(jsonArray.getJSONObject(i).getString("zhengwen"));
                        Log.i("aq", n.getTupian() + "," + n.getZhengwen() );
                        jiaoxueneirongList.add(n);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        try{
            Log.i("xingxi", String.valueOf(jiaoxueneirongList.size()));
        }catch (Exception e)
        {

        }

        return jiaoxueneirongList;
    }
}
