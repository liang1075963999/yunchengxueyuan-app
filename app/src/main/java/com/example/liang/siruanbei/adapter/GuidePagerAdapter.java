package com.example.liang.siruanbei.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liang.siruanbei.DragTopActivity;
import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.utils.APPUtil;
import com.example.liang.siruanbei.utils.Constant;
import com.example.liang.siruanbei.utils.ResUtil;
import com.example.liang.siruanbei.utils.SPUtil;
import com.example.liang.siruanbei.utils.StartActivityUtil;

import java.util.List;

/**
 * 导航页Adapter
 *
 * @author Phoenix
 * @date 2016-10-13 14:03
 */
public class GuidePagerAdapter extends PagerAdapter {
    private List<View> views;
    private Activity activity;

    public GuidePagerAdapter(List<View> views, Activity activity) {
        this.views = views;
        this.activity = activity;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public int getCount() {
        return (views == null) ? 0 : views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position), 0);
        //当滑动到最后一页的时候，监听按钮
        if (position == views.size() - 1) {
            TextView tvEnter = (TextView) container.findViewById(R.id.tv_guide_enter);

            tvEnter.setText(String.format(ResUtil.getString(R.string.guide_enter), APPUtil.getVersionName(activity)));
            tvEnter.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    SPUtil.put(Constant.SP_IS_FIRST_LOGIN, true);

                    StartActivityUtil.startActivity(activity, DragTopActivity.class);
                    activity.finish();
                }
            });
        }
        return views.get(position);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return (arg0 == arg1);
    }
}
