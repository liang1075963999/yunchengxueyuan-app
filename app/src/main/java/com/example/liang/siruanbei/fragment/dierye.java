package com.example.liang.siruanbei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.activity.KongJiaoShi;
import com.example.liang.siruanbei.activity.RiJiBen;


/**
 * Created by liang on 2017/10/22.
 */

public class dierye extends Fragment{
    private GridView gridView;
    private GridView gridView1;
    private Intent intent;
    private String string[] = {"空教室", "日记本", "待开发", "待开发", "待开发", "待开发", "待开发", "待开发"};
    private String string1[] = {"待开发", "待开发", "待开发", "待开发", "待开发", "待开发"};
    private int tubiao[] = {R.drawable.tubiao1, R.drawable.tubiao1, R.drawable.tubiao1, R.drawable.tubiao1,
            R.drawable.tubiao1, R.drawable.tubiao1, R.drawable.tubiao1, R.drawable.tubiao1,};
    private int tubiao1[] = {R.mipmap.yuan, R.mipmap.yuan1, R.mipmap.yuan2, R.mipmap.yuan3,
            R.mipmap.yuan4, R.mipmap.yuan5};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dier, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gridView = (GridView) view.findViewById(R.id.gridview);
        gridView1 = (GridView) view.findViewById(R.id.gridview1);
        gridView.setAdapter(new ShiPei());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    intent = new Intent(getActivity(), KongJiaoShi.class);
                    startActivity(intent);
                } else if (position == 1) {
                    intent = new Intent(getActivity(), RiJiBen.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "待开发", Toast.LENGTH_SHORT).show();
                }
            }
        });
        gridView1.setAdapter(new ShiPei1());
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "待开发", Toast.LENGTH_SHORT).show();
            }
        });
       /* setListViewHeightBasedOnChildren(gridView);
        setListViewHeightBasedOnChildren(gridView1);*/
    }

    class ShiPei extends BaseAdapter {
        ShiTu shiTu;

        public ShiPei() {
            shiTu = new ShiTu();
        }

        @Override
        public int getCount() {
            return string.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(parent.getContext(), R.layout.xiaobuju, null);
            shiTu.imageView = (ImageView) view.findViewById(R.id.imageView);
            shiTu.textView = (TextView) view.findViewById(R.id.textView);
            shiTu.imageView.setImageResource(tubiao[position]);
            shiTu.textView.setText(string[position]);
            return view;
        }

        class ShiTu {
            ImageView imageView;
            TextView textView;
        }
    }

    class ShiPei1 extends BaseAdapter {
        ShiTu shiTu;

        public ShiPei1() {
            shiTu = new ShiTu();
        }

        @Override
        public int getCount() {
            return string1.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(parent.getContext(), R.layout.xiaobuju, null);
            shiTu.imageView = (ImageView) view.findViewById(R.id.imageView);
            shiTu.textView = (TextView) view.findViewById(R.id.textView);
            //shiTu.imageView.setImageBitmap(Utils.toRoundBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.heart_on),null));
            shiTu.imageView.setImageResource(tubiao1[position]);
            shiTu.textView.setText(string1[position]);
            return view;
        }

        class ShiTu {
            ImageView imageView;
            TextView textView;
        }
    }
   /* public void setListViewHeightBasedOnChildren(GridView gridView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = 3; i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, gridView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight+ (gridView.getHeight() * 2)-20;
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        gridView.setLayoutParams(params);
    }*/
}
