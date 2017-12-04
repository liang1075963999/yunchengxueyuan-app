package com.example.liang.siruanbei;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.liang.siruanbei.activity.item;
import com.example.liang.siruanbei.bean.itembean;
import com.example.liang.siruanbei.shujuku.itemshujukucaozuo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/11/26.
 */
public class Gerenshoucang extends AppCompatActivity{
    private ListView listView;
    private itemshujukucaozuo itemshujukucaozuo;
    private List<itembean> arrayList;
    private List<String> list;
    private List<String>list1;
    private List<String>list2;
    private Intent intent;
    private ArrayList<String>arrayList1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenshoucang);
        listView= (ListView) findViewById(R.id.listview);
        list=new ArrayList<>();
        list1=new ArrayList<>();
        list2=new ArrayList<>();
        intent=new Intent(this,item.class);
        arrayList1=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                itemshujukucaozuo=new itemshujukucaozuo(Gerenshoucang.this);
                arrayList= itemshujukucaozuo.xianshi();
                for(itembean itembean:arrayList){
                    list.add(itembean.getZhengwen().substring(0,20)+"...");
                    list2.add(itembean.getZhengwen());
                    list1.add(itembean.getTupian());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listView.setAdapter(new ArrayAdapter<>(Gerenshoucang.this,android.R.layout.simple_list_item_1,list));
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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog dialog=new AlertDialog.Builder(Gerenshoucang.this)
                        .setTitle("是否删除该收藏?")
                        .setNegativeButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                itemshujukucaozuo.delete(list1.get(position));
                                list.remove(position);
                                listView.setAdapter(new ArrayAdapter<>(Gerenshoucang.this,android.R.layout.simple_list_item_1,list));
                            }
                        })
                        .setPositiveButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .create();
                dialog.show();
                return true;
            }
        });
    }
}
