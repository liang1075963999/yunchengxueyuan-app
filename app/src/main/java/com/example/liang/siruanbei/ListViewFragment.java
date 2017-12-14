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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liang.siruanbei.activity.itemactivity;
import com.example.liang.siruanbei.bean.tongzhi;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;

/**
 * Created by chenupt@gmail.com on 1/23/15.
 * Description :
 */
public class ListViewFragment extends Fragment {
    private ListView listView;
    private ListAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Intent intent;
    private ArrayList<String>arrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("haha","onDestroy");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.i("haha","onViewStateRestored");
    }

    private void initView() {
        listView = (ListView)getView().findViewById(R.id.list_view);
        adapter = new adapter(getActivity(), R.layout.buju0, gongju.gettongzhiList(getContext()));
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
                        adapter = new adapter(getActivity(), R.layout.buju0, gongju.gettongzhiList(getContext()));
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
//                arrayList=new ArrayList<String>();
                intent=new Intent(getActivity(), itemactivity.class);
                /*arrayList.add(gongju.getxibuList().get(position).getTupian());
                arrayList.add(gongju.getxibuList().get(position).getZhengwen());*/
//                intent.putStringArrayListExtra("xinxi",arrayList);
                intent.putExtra("wangzhi",gongju.gettongzhiList(getContext()).get(position).getHref());
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

    @Override
    public void onPause() {
        super.onPause();
    }

    class adapter extends ArrayAdapter<tongzhi> {
        cheng c;
        int h;
        List<tongzhi> o;

        public adapter(Context context, int resource, List<tongzhi> objects) {
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
                tongzhi f = getItem(position);
                c = new cheng();
                    c.tt = (TextView) v.findViewById(R.id.title);
                    c.ii = (TextView) v.findViewById(R.id.date);
//                    c.oo = (TextView) v.findViewById(R.id.href);
                    c.tt.setText(f.getTitle());
                    c.ii.setText(f.getDate());
//                    c.oo.setText(f.getHref());

            } catch (Exception e) {

            }
            return v;
        }

        class cheng {
            TextView tt;
            TextView ii;
//            TextView oo;
        }
    }
}
