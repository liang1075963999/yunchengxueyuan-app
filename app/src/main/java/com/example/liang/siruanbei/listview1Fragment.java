package com.example.liang.siruanbei;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liang.siruanbei.activity.item;
import com.example.liang.siruanbei.bean.dongtaineirong;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenupt@gmail.com on 1/30/15.
 * Description :
 */
public class listview1Fragment extends Fragment {
    private ListView listView;
    private shipei adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Intent intent;
    private ArrayList<String>arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        listView = (ListView) getView().findViewById(R.id.list_view);
        adapter = new shipei(getContext(),R.layout.buju1, gongju.getdongtaineirongList());
        listView.setAdapter(adapter);
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.material_blue);
        //swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.material_red));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new shipei(getContext(),R.layout.buju1,gongju.getdongtaineirongList());
                        listView.setAdapter(adapter);
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "只有这些数据了", Toast.LENGTH_SHORT).show();
                    }
                },1000);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList=new ArrayList<String>();
                intent=new Intent(getActivity(), item.class);
                arrayList.add(gongju.getdongtaineirongList().get(position).getTupian());
                arrayList.add(gongju.getdongtaineirongList().get(position).getZhengwen());
                intent.putStringArrayListExtra("xinxi",arrayList);
                startActivity(intent);
            }
        });

    }
    class shipei extends ArrayAdapter<dongtaineirong>{
        cheng c;
        int h;
        List<dongtaineirong> objects;
        public shipei(Context context, int resource, List<dongtaineirong> objects) {
            super(context, resource, objects);
            h=resource;
            this.objects=objects;
        }

        @Override
        public int getCount() {
            try{
                return objects.size();
            }catch (Exception e)
            {
                return 0;
            }

        }
        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(getContext()).inflate(h, parent, false);
            try {
                dongtaineirong f = getItem(position);
                c = new cheng();
                c.tt = (ImageView) v.findViewById(R.id.imageView);
                c.ii = (TextView) v.findViewById(R.id.textview);
//                    c.oo = (TextView) v.findViewById(R.id.href);
//                Glide.with(getContext()).load(f.getTupian()).into(c.tt);
//                c.tt.setImageBitmap(new Bitmap().f.getTupian());
                if(f.getTupian().equals("tupianweikong"))
                {
//                    Log.i("jiequ","meitupian");
                }
                else {
                    for(String s:f.getTupian().split(","))
                    {
//                        Log.i("jiequ",s.substring(3).replace("]","").replace("'",""));
                        //c.tt.setImageURI(Uri.parse("http://www.ycu.edu.cn/"+s.substring(3).replace("]","").replace("'","")));
                        Picasso.with(getContext()).load("http://www.ycu.edu.cn/"+s.substring(3).replace("]","").replace("'","")).fit().into(c.tt);
                    }

                }
//                Log.i("wangzhi","http://www.ycu.edu.cn/"+f.getTupian());
                c.ii.setText(f.getZhengwen().substring(0,50)+"...");
//                    c.oo.setText(f.getHref());

            } catch (Exception e) {

            }
            return v;
        }

        class cheng {
            ImageView tt;
            TextView ii;
//            TextView oo;
        }
    }
}


