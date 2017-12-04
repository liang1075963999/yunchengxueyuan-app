package com.example.liang.siruanbei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.bean.itembean;
import com.example.liang.siruanbei.bean.lishibean;
import com.example.liang.siruanbei.shujuku.itemshujukucaozuo;
import com.example.liang.siruanbei.shujuku.lishicaozuo;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.liang.siruanbei.BmobApplication.getContext;

/**
 * Created by liang on 2017/10/27.
 */

public class item extends AppCompatActivity {
    private Intent intent;
    private ArrayList<String>arrayList;
    private String tupian;
    private String zhengwen;
    private TextView textView;
    private ImageView imageView;
    private ImageView imageView2;
    private NestedScrollView nestedScrollView;
    private LinearLayout linearLayout;
    private DisplayMetrics displayMetrics;
    private int width;
    private int height;
    private itemshujukucaozuo itemshujukucaozuo;
    private Toolbar toolbar;
    private lishicaozuo lishicaozuo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);
        Log.i("shijian",new SimpleDateFormat("MM月dd日HH:mm:ss").format(new Date(System.currentTimeMillis())));//输出结果为：11月26日20:21:21
        itemshujukucaozuo=new itemshujukucaozuo(this);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.itemmenu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.shoucang)
                {
                    itembean itembean=new itembean();
                    itembean.setTupian(tupian);
                    itembean.setZhengwen(zhengwen);
                    try{
                        itemshujukucaozuo.shoucang(itembean);
                        Toast.makeText(item.this,"收藏成功",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(item.this,"你已经收藏过了",Toast.LENGTH_SHORT).show();
                    }

                }
                return true;
            }
        });
        textView= (TextView) findViewById(R.id.textView4);
        imageView= (ImageView) findViewById(R.id.imageview12);
        linearLayout= (LinearLayout) findViewById(R.id.linea);
        nestedScrollView= (NestedScrollView) findViewById(R.id.scrollview);
        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width=displayMetrics.widthPixels;
        height=displayMetrics.heightPixels;
        intent=getIntent();
        arrayList=intent.getStringArrayListExtra("xinxi");
        tupian=arrayList.get(0);
        zhengwen=arrayList.get(1);
        lishicaozuo=new lishicaozuo(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                lishibean lishibean=new lishibean();
                lishibean.setTupian(tupian);
                lishibean.setZhengwen(zhengwen);
                lishibean.setDate(new SimpleDateFormat("MM月dd日HH:mm:ss").format(new Date(System.currentTimeMillis())));
                try{
                    lishicaozuo.shoucang(lishibean);
                }catch (Exception e){

                }
            }
        }).start();
        if(tupian.equals("tupianweikong"))
        {
//                    Log.i("jiequ","meitupian");
            textView.setVisibility(View.GONE);
        }
        else {
            for(String s:tupian.split(","))
            {

                if(s.equals(tupian.split(",")[tupian.split(",").length-1]))
                {
                    Picasso.with(getContext()).load("http://www.ycu.edu.cn/"+s.substring(3).replace("]","").replace("'","")).fit().memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(imageView);
                }
                else
                {
                    Log.i("tt",s.substring(3).replace("]","").replace("'",""));
                    imageView2=new ImageView(this);
//                    imageView2.setLayoutParams(new LinearLayout.LayoutParams());
                    LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(new Double(width*0.8).intValue(),height/3);
                    layoutParams.gravity= Gravity.CENTER_HORIZONTAL;
                    layoutParams.setMargins(5,17,5,10);
                    imageView2.setLayoutParams(layoutParams);
                    Picasso.with(getContext()).load("http://www.ycu.edu.cn/"+s.substring(3).replace("]","").replace("'","")).fit().memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(imageView2);
                    linearLayout.addView(imageView2);
                }
                //c.tt.setImageURI(Uri.parse("http://www.ycu.edu.cn/"+s.substring(3).replace("]","").replace("'","")));

            }

        }
        textView.setText("      "+zhengwen);
    }
}
