package com.example.liang.siruanbei.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.liang.siruanbei.DragTopLayout;
import com.example.liang.siruanbei.GridViewFragment;
import com.example.liang.siruanbei.ListViewFragment;
import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.ScrollViewFragment;
import com.example.liang.siruanbei.WebViewFragment;
import com.example.liang.siruanbei.activity.itemactivity;
import com.example.liang.siruanbei.listview1Fragment;
import com.example.liang.siruanbei.viewpager.BannerViewPager;
import com.example.liang.siruanbei.viewpager.OnPageClickListener;
import com.example.liang.siruanbei.viewpager.ViewPagerAdapter;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;

/**
 * Created by liang on 2017/10/22.
 */

public class zhuyeFragment extends Fragment {
    @Nullable
    private DragTopLayout dragLayout;
    private ModelPagerAdapter adapter;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private BannerViewPager bannerViewPager;
    private ImageView topImageView;
    private ViewPagerAdapter mAdapter;
    final List<View> mViews = new ArrayList<>();
    public Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.zhuye,container,false);
        viewPager = (ViewPager)view.findViewById(R.id.view_pager);
        dragLayout = (DragTopLayout)view.findViewById(R.id.drag_layout);
        //topImageView = (ImageView) findViewById(R.id.image_view);
        pagerSlidingTabStrip = (PagerSlidingTabStrip)view.findViewById(R.id.tabs);
        PagerModelManager factory = new PagerModelManager();
        factory.addCommonFragment(getFragments(), getTitles());
        adapter = new ModelPagerAdapter(getActivity().getSupportFragmentManager(), factory);
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        bannerViewPager = (BannerViewPager)view.findViewById(R.id.banner);
        intent=new Intent(getContext(),itemactivity.class);
        ImageView iv1 = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.viewpageritem, bannerViewPager, false);
        ImageView iv2 = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.viewpageritem, bannerViewPager, false);
        ImageView iv3 = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.viewpageritem, bannerViewPager, false);
        ImageView iv4 = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.viewpageritem, bannerViewPager, false);
        ImageView iv5 = (ImageView) LayoutInflater.from(getActivity()).inflate(R.layout.viewpageritem, bannerViewPager, false);
        mViews.add(iv1);
        mViews.add(iv2);
        mViews.add(iv3);
        mViews.add(iv4);
        mViews.add(iv5);
        iv1.setImageResource(R.mipmap.tu1);
        iv2.setImageResource(R.mipmap.tu2);
        iv3.setImageResource(R.mipmap.tu3);
        iv4.setImageResource(R.mipmap.tu4);
        iv5.setImageResource(R.mipmap.tu5);
        mAdapter = new ViewPagerAdapter(mViews, new OnPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                if (position == 0) {
                    intent.putExtra("wangzhi", "http://www.ycu.edu.cn/N20171016223728.html");
                } else if (position == 1) {
                    intent.putExtra("wangzhi", "http://www.ycu.edu.cn/N20170926170544.html");
                } else if (position == 2) {
                    intent.putExtra("wangzhi", "http://www.ycu.edu.cn/N20170910170942.html");
                } else if (position == 3) {
                    intent.putExtra("wangzhi", "http://www.ycu.edu.cn/N20170907223240.html");
                } else if (position == 4) {
                    intent.putExtra("wangzhi", "http://www.ycu.edu.cn/N20170906201145.html");
                }
                startActivity(intent);
            }
        });
        bannerViewPager.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("hehe","onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("hehe","onDestroy");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private List<String> getTitles() {
        return Lists.newArrayList( "通知公告", "学术动态","学院新闻", "系部信息", "实践教学");
    }

    private List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        Fragment listFragment = new ListViewFragment();
        Fragment recyclerFragment = new listview1Fragment();
        Fragment gridViewFragment = new GridViewFragment();
        Fragment scrollViewFragment = new ScrollViewFragment();
        Fragment webViewFragment = new WebViewFragment();
        list.add(listFragment);
        list.add(recyclerFragment);
        list.add(gridViewFragment);
        list.add(scrollViewFragment);
        list.add(webViewFragment);
        return list;
    }
}
