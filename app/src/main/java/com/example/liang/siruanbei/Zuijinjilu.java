package com.example.liang.siruanbei;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liang.siruanbei.activity.item;
import com.example.liang.siruanbei.bean.lishibean;
import com.example.liang.siruanbei.shujuku.lishicaozuo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/11/26.
 */
public class Zuijinjilu extends AppCompatActivity{
    private ListView listView;
    private lishicaozuo lishicaozuo;
    private ArrayList<lishibean>arrayList;
    private List<String> list;
    private List<String>list1;
    private List<String>list2;
    private List<String>list3;
    private ArrayList<String>arrayList1;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zuijinjilu);
        listView= (ListView) findViewById(R.id.listview1);
        list=new ArrayList<>();
        list1=new ArrayList<>();
        list2=new ArrayList<>();
        list3=new ArrayList<>();
        intent=new Intent(this,item.class);
        arrayList1=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lishicaozuo=new lishicaozuo(Zuijinjilu.this);
                arrayList= (ArrayList<lishibean>) lishicaozuo.xianshi();
                for(int i=arrayList.size()-1;i>-1;i--){
                    try{
                        list.add(arrayList.get(i).getZhengwen().substring(0,20)+"...");
                        list2.add(arrayList.get(i).getZhengwen());
                        list1.add(arrayList.get(i).getTupian());
                        list3.add(arrayList.get(i).getDate());
                    }catch (Exception e){
                        Toast.makeText(Zuijinjilu.this,"暂无更多浏览记录",Toast.LENGTH_SHORT).show();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(new shipei(Zuijinjilu.this,R.layout.zuijinitem));
                    }
                });
            }
        }).start();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList1.add(list1.get(position));
                arrayList1.add(list2.get(position));
                intent.putStringArrayListExtra("xinxi",arrayList1);
                startActivity(intent);
            }
        });
    }
    class shipei extends ArrayAdapter{
        shitu shitu=new shitu();
        int res;
        public shipei(Context context, int resource) {
            super(context, resource);
            res=resource;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(Zuijinjilu.this,res,null);
            shitu.textView= (TextView) view.findViewById(R.id.textView8);
            shitu.textView1= (TextView) view.findViewById(R.id.textView7);
            shitu.textView.setText(list.get(position));
            shitu.textView1.setText(list3.get(position));
            return view;
        }
    }
    class shitu{
        TextView textView;
        TextView textView1;
    }
}
