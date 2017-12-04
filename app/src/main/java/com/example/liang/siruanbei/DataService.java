package com.example.liang.siruanbei;

import android.util.Log;

import com.example.liang.siruanbei.bean.news;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by chenupt@gmail.com on 1/30/15.
 * Description :
 */
public class DataService {
    private news news;
    private List<news> resultList;
    private BmobQuery a;

    public List<news> getList() {
        a = new BmobQuery("news");
        a.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                resultList= new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        Log.i("xinxi", jsonArray.getJSONObject(i).getString("date") + "," + jsonArray.getJSONObject(i).getString("href") + "," + jsonArray.getJSONObject(i).getString("title"));
                        news=new news();
                        news.setDate(jsonArray.getJSONObject(i).getString("date"));
                        news.setHref(jsonArray.getJSONObject(i).getString("href"));
                        news.setTitle(jsonArray.getJSONObject(i).getString("title"));
                        resultList.add(news);
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        return resultList;
    }




    private static volatile DataService instance = null;

    public DataService(){
    }
//
//    public static DataService getInstance() {
//        if (instance == null) {
//            synchronized (DataService.class) {
//                if (instance == null) {
//                    instance = new DataService();
//                }
//            }
//        }
//        return instance;
//    }

}
