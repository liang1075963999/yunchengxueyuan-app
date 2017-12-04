package com.example.liang.siruanbei;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liang.siruanbei.activity.item;
import com.example.liang.siruanbei.bean.jiaoxueneirong;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import github.chenupt.dragtoplayout.AttachUtil;

/**
 * Created by chenupt@gmail.com on 1/30/15.
 * Description :
 */
public class WebViewFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private GridView gridView;
    private adapter adapter;
    private DisplayMetrics displayMetrics;
    private int width;
    private int height;
    private Intent intent;
    private ArrayList<String> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        displayMetrics=new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width=displayMetrics.widthPixels;
        height=displayMetrics.heightPixels;
        return inflater.inflate(R.layout.fragment_webview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        gridView = (GridView) getView().findViewById(R.id.grid_view);
//        adapter.setList(DataService.getInstance().getList());
//        adapter.notifyDataSetChanged();
        adapter = new adapter(getContext(), R.layout.buju, gongju.getjiaoxueneirongList());
        gridView.setAdapter(adapter);
        swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.material_blue);
        //swipeRefreshLayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.material_red));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new adapter(getContext(), R.layout.buju, gongju.getjiaoxueneirongList());
                        gridView.setAdapter(adapter);
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "只有这些数据了", Toast.LENGTH_SHORT).show();
                    }
                },1000);
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList=new ArrayList<String>();
                intent=new Intent(getActivity(), item.class);
                arrayList.add(gongju.getNewsneirongList().get(position).getTupian());
                arrayList.add(gongju.getNewsneirongList().get(position).getZhengwen());
                intent.putStringArrayListExtra("xinxi",arrayList);
                startActivity(intent);
            }
        });
        // attach top
        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                EventBus.getDefault().post(AttachUtil.isAdapterViewAttach(view));
            }
        });

    }
    class adapter extends ArrayAdapter<jiaoxueneirong> {
        cheng c;
        int h;
        List<jiaoxueneirong> list;
        public adapter(Context context, int resource, List<jiaoxueneirong> objects) {
            super(context, resource, objects);
            h = resource;
            list=objects;
        }

        @Override
        public int getCount() {
            try {
                return list.size();
            } catch (Exception e) {

                return 0;
            }
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = LayoutInflater.from(getContext()).inflate(h, parent, false);
            try {
                jiaoxueneirong f = getItem(position);
                c = new cheng();
                c.tt = (ImageView) v.findViewById(R.id.title);
                c.ii = (TextView) v.findViewById(R.id.date);
                c.tt.setLayoutParams(new LinearLayout.LayoutParams(width/3,height/5));
//                    c.oo = (TextView) v.findViewById(R.id.href);
                if(f.getTupian().equals("tupianweikong"))
                {
                    Log.i("jiequ","meitupian");
                }
                else {
                    for(String s:f.getTupian().split(","))
                    {
                        Log.i("jiequ",s.substring(3).replace("]","").replace("'",""));
                        //c.tt.setImageURI(Uri.parse("http://www.ycu.edu.cn/"+s.substring(3).replace("]","").replace("'","")));
                        Picasso.with(getContext()).load("http://www.ycu.edu.cn/"+s.substring(3).replace("]","").replace("'","")).fit().memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(c.tt);
                    }

                }
                Log.i("wangzhi","http://www.ycu.edu.cn/"+f.getTupian());
                c.ii.setText(f.getZhengwen().substring(0,40)+"...");
//                    c.oo.setText(f.getHref());

            } catch (Exception e) {

            }
//                    c.oo.setText(f.getHref());
            return v;
        }

        class cheng {
            ImageView tt;
            TextView ii;
//            TextView oo;
        }
    }

}
