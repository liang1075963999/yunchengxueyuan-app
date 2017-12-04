package com.example.liang.siruanbei.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.kebiao.ColorUtils;
import com.example.liang.siruanbei.kebiao.CornerTextView;
import com.example.liang.siruanbei.kebiao.CourseModel;
import com.example.liang.siruanbei.shujuku.shujukucaozuo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by liang on 2017/11/1.
 */

public class kebiao extends Fragment {
    List<LinearLayout> mWeekViews = new ArrayList<>();
    private LinearLayout weekNames;
    LinearLayout sections;
    MaterialRefreshLayout mFreshLayout;
    private int itemHeight;
    private int maxSection = 10;
    LinearLayout l1;
    LinearLayout l2;
    LinearLayout l3;
    LinearLayout l4;
    LinearLayout l5;
    LinearLayout l6;
    LinearLayout l7;
    private Button button;
    private Intent intent;
    List<CourseModel> courseModels[] = new ArrayList[7];
    List<CourseModel> models_1;

    private com.example.liang.siruanbei.shujuku.shujukucaozuo shujukucaozuo;

    //List<CourseModel>[]lists=new  ArrayList[7];
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.kebiao, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < courseModels.length; i++) {
            courseModels[i] = new ArrayList<>();
        }


//        SharedPreferences sharedPreferences=getActivity().getSharedPreferences(kebiaobianji.FILENAME,MODE_MULTI_PROCESS);
//        try{
//            Log.i("cun",sharedPreferences.getString("chi",""));
//        }catch (Exception e){
//
//        }
        itemHeight = getResources().getDimensionPixelSize(R.dimen.sectionHeight);
        weekNames = (LinearLayout) view.findViewById(R.id.weekNames);
        mFreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.mFreshLayout);
        sections = (LinearLayout) view.findViewById(R.id.sections);
     /*   button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getContext(), kebiaobianji.class);
                startActivity(intent);
            }
        });*/
        l1 = (LinearLayout) view.findViewById(R.id.weekPanel_1);
        l2 = (LinearLayout) view.findViewById(R.id.weekPanel_2);
        l3 = (LinearLayout) view.findViewById(R.id.weekPanel_3);
        l4 = (LinearLayout) view.findViewById(R.id.weekPanel_4);
        l5 = (LinearLayout) view.findViewById(R.id.weekPanel_5);
        l6 = (LinearLayout) view.findViewById(R.id.weekPanel_6);
        l7 = (LinearLayout) view.findViewById(R.id.weekPanel_7);
        mWeekViews.add(l1);
        mWeekViews.add(l2);
        mWeekViews.add(l3);
        mWeekViews.add(l4);
        mWeekViews.add(l5);
        mWeekViews.add(l6);
        mWeekViews.add(l7);
        initWeekNameView();
        initSectionView();
        initWeekCourseView();
        setRefreshListener();
    }

    /**
     * 初始化课程表
     */

    private void initWeekCourseView() {
        List<CourseModel> models1 = new ArrayList<>();
        List<CourseModel> models2 = new ArrayList<>();
        List<CourseModel> models3 = new ArrayList<>();
        List<CourseModel> models4 = new ArrayList<>();
        List<CourseModel> models5 = new ArrayList<>();
        List<CourseModel> models6 = new ArrayList<>();
        List<CourseModel> models7 = new ArrayList<>();
//        for (int i = 0; i < courseModels.length; i++) {
//            courseModels[i] = new ArrayList<>();
//        }
        shujukucaozuo = new shujukucaozuo(getContext());
        models_1 = shujukucaozuo.xianshi();

        //Log.i("oo",models_1.toString()+models1.size());
        if (models_1.size() != 0) {
            for (int i = 0; i < models_1.size(); i++) {
//                Log.i("cao",models_1.get(i).getCourseName());
                if ((models_1.get(i).getWeek() - 1) == 0) {
                    Log.i("caoa","models1"+","+models1.size()+"");
                    models1.add(models_1.get(i));
                }
                if ((models_1.get(i).getWeek() - 1) == 1) {
                    Log.i("caoa","models2"+","+models2.size()+"");
                    models2.add(models_1.get(i));
                }
                if ((models_1.get(i).getWeek() - 1) == 2) {
                    Log.i("caoa","models3"+","+models3.size()+"");
                    models3.add(models_1.get(i));
                }
                if ((models_1.get(i).getWeek() - 1) == 3) {
                    Log.i("caoa","models4"+","+models4.size()+"");
                    models4.add(models_1.get(i));
                }
                if ((models_1.get(i).getWeek() - 1) == 4) {
                    Log.i("caoa","models5"+","+models5.size()+"");
                    models5.add(models_1.get(i));
                }
                if ((models_1.get(i).getWeek() - 1) == 5) {
                    Log.i("caoa","models6"+","+models6.size()+"");
                    models6.add(models_1.get(i));
                }if ((models_1.get(i).getWeek() - 1) == 6) {
                    Log.i("caoa","models7"+","+models7.size()+"");
                    models7.add(models_1.get(i));
                }
            }
            if (models1.size() != 0)
            {
                Log.i("cao",courseModels[0].size()+"");
                courseModels[0]=new ArrayList<>();
                courseModels[0].addAll(models1);
            }
            if (models2.size() != 0)
            {
                Log.i("cao",courseModels[1].size()+"");
                courseModels[1]=new ArrayList<>();
                courseModels[1].addAll(models2);
            }

            if (models3.size() != 0)
            {
                Log.i("cao",courseModels[2].size()+"");
                courseModels[2]=new ArrayList<>();
                courseModels[2].addAll(models3);
            }

            if (models4.size() != 0)
            {
                Log.i("cao",courseModels[3].size()+"");
                courseModels[3]=new ArrayList<>();
                courseModels[3].addAll(models4);
            }

            if (models5.size() != 0)
            {
                Log.i("cao",courseModels[4].size()+"");
                courseModels[4]=new ArrayList<>();
                courseModels[4].addAll(models5);
            }

            if (models6.size() != 0)
            {
                Log.i("cao",courseModels[5].size()+"");
                courseModels[5]=new ArrayList<>();
                courseModels[5].addAll(models6);
            }

            if (models7.size() != 0)
            {
                Log.i("cao",courseModels[6].size()+"");
                courseModels[6]=new ArrayList<>();
                courseModels[6].addAll(models7);
            }

        }
//        models_1 = kebiaobianji.models_1;
//        try{
//            courseModels[Integer.parseInt(kebiaobianji.string1)-1].addAll(models_1);
//        }catch (Exception e){
//
//        }
        for (int i = 0; i < mWeekViews.size(); i++) {
            initWeekPanel(mWeekViews.get(i), courseModels[i]);
            //courseModels[i].removeAll(models1);
        }
//        courseModels[0].removeAll(models1);
//        courseModels[1].removeAll(models2);
//        courseModels[2].removeAll(models3);
//        courseModels[3].removeAll(models4);
//        courseModels[4].removeAll(models5);
//        courseModels[5].removeAll(models6);
//        courseModels[6].removeAll(models7);

    }

    /**
     * 下拉刷新
     */
    private void setRefreshListener() {
        mFreshLayout.setLoadMore(false);
        mFreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                clearChildView();
                initWeekCourseView();
                mFreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mFreshLayout.finishRefreshing();
                    }
                }, 500);
            }

        });
    }

    /**
     * 顶部周一到周日的布局
     **/
    private void initWeekNameView() {
        for (int i = 0; i < mWeekViews.size() + 1; i++) {
            TextView tvWeekName = new TextView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            if (i != 0) {
                lp.weight = 1;
                tvWeekName.setText("周" + intToZH(i));
                if (i == getWeekDay()) {
                    tvWeekName.setTextColor(Color.parseColor("#FF0000"));
                } else {
                    tvWeekName.setTextColor(Color.parseColor("#4A4A4A"));
                }
            } else {
                lp.weight = 0.8f;
                tvWeekName.setText(getMonth() + "月");
            }
            tvWeekName.setGravity(Gravity.CENTER_HORIZONTAL);
            tvWeekName.setLayoutParams(lp);
            weekNames.addView(tvWeekName);
        }
    }

    /**
     * 左边节次布局，设定每天最多12节课
     */
    private void initSectionView() {
        for (int i = 1; i <= maxSection; i++) {
            TextView tvSection = new TextView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, getResources().getDimensionPixelSize(R.dimen.sectionHeight));
            lp.gravity = Gravity.CENTER;
            tvSection.setGravity(Gravity.CENTER);
            tvSection.setText(String.valueOf(i));
            tvSection.setLayoutParams(lp);
            sections.addView(tvSection);
        }
    }

    /**
     * 当前星期
     */
    public int getWeekDay() {
        int w = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
        if (w <= 0) {
            w = 7;
        }
        return w;
    }

    /**
     * 当前月份
     */
    public int getMonth() {
        int w = Calendar.getInstance().get(Calendar.MONTH) + 1;
        return w;
    }

    /**
     * 每次刷新前清除每个LinearLayout上的课程view
     */
    private void clearChildView() {
        for (int i = 0; i < mWeekViews.size(); i++) {
            if (mWeekViews.get(i) != null)
                if (mWeekViews.get(i).getChildCount() > 0)
                    mWeekViews.get(i).removeAllViews();
        }
    }


    public void initWeekPanel(LinearLayout ll, List<CourseModel> data) {

        if (ll == null || data == null || data.size() < 1)
            return;
        CourseModel firstCourse = data.get(0);
        for (int i = 0; i < data.size(); i++) {
            final CourseModel courseModel = data.get(i);

            if (courseModel.getSection() == 0 || courseModel.getSectionSpan() == 0)
                return;
            FrameLayout frameLayout = new FrameLayout(getContext());

            CornerTextView tv = new CornerTextView(getContext(),
                    ColorUtils.getCourseBgColor(courseModel.getCourseFlag()),
                    dip2px(getContext(), 3));
            LinearLayout.LayoutParams frameLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    itemHeight * courseModel.getSectionSpan());
            LinearLayout.LayoutParams tvLp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);

            if (i == 0) {
                frameLp.setMargins(0, (courseModel.getSection() - 1) * itemHeight, 0, 0);
            } else {
                frameLp.setMargins(0, (courseModel.getSection() - (firstCourse.getSection() + firstCourse.getSectionSpan())) * itemHeight, 0, 0);
            }
            tv.setLayoutParams(tvLp);
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(12);
            tv.setTextColor(Color.parseColor("#FFFFFF"));
            tv.setText(courseModel.getCourseName() + "\n @" + courseModel.getClassRoom());

            frameLayout.setLayoutParams(frameLp);
            frameLayout.addView(tv);
            frameLayout.setPadding(2, 2, 2, 2);
            ll.addView(frameLayout);
            firstCourse = courseModel;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showToast(courseModel.getCourseName());
                }
            });
        }
    }

    /**
     * Toast
     */
    private void showToast(String msg) {
        if (TextUtils.isEmpty(msg))
            return;
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 数字转换中文
     */
    public static String intToZH(int i) {
        String[] zh = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] unit = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十"};

        String str = "";
        StringBuffer sb = new StringBuffer(String.valueOf(i));
        sb = sb.reverse();
        int r = 0;
        int l = 0;
        for (int j = 0; j < sb.length(); j++) {
            r = Integer.valueOf(sb.substring(j, j + 1));
            if (j != 0)
                l = Integer.valueOf(sb.substring(j - 1, j));
            if (j == 0) {
                if (r != 0 || sb.length() == 1)
                    str = zh[r];
                continue;
            }
            if (j == 1 || j == 2 || j == 3 || j == 5 || j == 6 || j == 7 || j == 9) {
                if (r != 0)
                    str = zh[r] + unit[j] + str;
                else if (l != 0)
                    str = zh[r] + str;
                continue;
            }
            if (j == 4 || j == 8) {
                str = unit[j] + str;
                if ((l != 0 && r == 0) || r != 0)
                    str = zh[r] + str;
                continue;
            }
        }
        if (str.equals("七"))
            str = "日";
        return str;
    }
}
