/*
 * Copyright 2015 chenupt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * imitations under the License.
 */

package com.example.liang.siruanbei;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liang.siruanbei.activity.item;
import com.example.liang.siruanbei.bean.xibuneirong;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;

/**
 * Created by chenupt@gmail.com on 1/23/15.
 * Description :
 */
public class ScrollViewFragment extends Fragment {
    private ListView listView;
    private adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Intent intent;
    private ArrayList<String>arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrollview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        listView = (ListView) getView().findViewById(R.id.list_view);
        adapter = new adapter(getContext(), R.layout.buju2, gongju.getxibuList());
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
                        adapter = new adapter(getContext(), R.layout.buju2, gongju.getxibuList());
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
                arrayList.add(gongju.getxibuList().get(position).getTupian());
                arrayList.add(gongju.getxibuList().get(position).getZhengwen());
                intent.putStringArrayListExtra("xinxi",arrayList);
                startActivity(intent);
            }
        });

        // attach top
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                EventBus.getDefault().post(AttachUtil.isAdapterViewAttach(view));
            }
        });

    }
    public void init(){

    }
    @Override
    public void onPause() {
        super.onPause();
    }

    class adapter extends ArrayAdapter<xibuneirong> {
        cheng c;
        int h;
        List<xibuneirong> o;

        public adapter(Context context, int resource, List<xibuneirong> objects) {
            super(context, resource, objects);
            h = resource;
            o = objects;
        }

        @Override
        public int getCount() {
            try {
                return o.size();
            } catch (Exception e) {

                return 0;
            }
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(getContext()).inflate(h, parent, false);
            try {
                xibuneirong f = getItem(position);
                c = new cheng();
                c.tt = (ImageView) v.findViewById(R.id.imageView);
                c.ii = (TextView) v.findViewById(R.id.textview);
//                    c.oo = (TextView) v.findViewById(R.id.href);
//                Glide.with(getContext()).load(f.getTupian()).into(c.tt);
//                c.tt.setImageBitmap(new Bitmap().f.getTupian());
                if(f.getTupian().equals("tupianweikong"))
                {
//                    Log.i("jiequ","meitupian");
                    c.tt.setVisibility(View.GONE);
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
                if(f.getZhengwen().equals("wenbenweikong")){
                    Log.i("xiao","wenbenweikong");
                    c.ii.setVisibility(View.GONE);
                }else
                {
                    c.ii.setText(f.getZhengwen().substring(0,50)+"...");
                }

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
