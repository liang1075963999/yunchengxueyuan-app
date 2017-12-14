package com.example.liang.siruanbei;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.liang.siruanbei.bean.news;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by liang on 2017/12/9.
 */

public class CeShi extends AppCompatActivity implements View.OnClickListener{
    private ListView listView;
    private Button button;
    private BmobQuery<news> a;
    private  List<String> resultList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ceshi);
        listView= (ListView) findViewById(R.id.listview);
        button= (Button) findViewById(R.id.button2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //listView.setAdapter(new ArrayAdapter<String>(CeShi.this,android.R.layout.simple_list_item_1,getList()));
        getList();
    }
    public  List<String> getList() {
        a = new BmobQuery("news");
        a.findObjects(new FindListener<news>() {
            @Override
            public void done(List<news> list, BmobException e) {
                while (list.iterator().hasNext()){
                    Log.i("xinxi",list.iterator().next().toString());
                }
                Log.i("xinxin",e.toString());
            }
        });
       /* a.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                resultList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        resultList.add(jsonArray.getJSONObject(i).getString("title"));
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });*/
        try{
            Log.i("xingxi", String.valueOf(resultList.size()));
        }catch (Exception e)
        {

        }
        return resultList;
    }
}
