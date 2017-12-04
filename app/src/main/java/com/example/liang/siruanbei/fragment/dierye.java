package com.example.liang.siruanbei.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.activity.xiangqing;
import com.example.liang.siruanbei.adapter.SimpleListAdapter;
import com.example.liang.siruanbei.shimmer.Shimmer;
import com.example.liang.siruanbei.shimmer.ShimmerTextView;
import com.example.liang.siruanbei.utils.kongjiaoshi;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

import static com.example.liang.siruanbei.R.id.dianji;


/**
 * Created by liang on 2017/10/22.
 */

public class dierye extends Fragment {
    private SearchableSpinner mSearchableSpinner;
    private SearchableSpinner mSearchableSpinner1;
    private SearchableSpinner mSearchableSpinner2;
    //    private SearchableSpinner mSearchableSpinner3;
    private SimpleListAdapter mSimpleListAdapter;
    private SimpleListAdapter mSimpleListAdapter1;
    private SimpleListAdapter mSimpleListAdapter2;
    private ArrayList<String> mStrings = new ArrayList<>();
    private ArrayList<String> keshi = new ArrayList<>();
    private ArrayList<String> didian = new ArrayList<>();
    private Button button;
    private String string;
    private String string1;
    private String string2;
    private String  string3;
    private TextView textView;
    private Intent intent;
    private kongjiaoshi kongjiaoshi = com.example.liang.siruanbei.utils.kongjiaoshi.getInstance();
    private ShimmerTextView shimmerTextView;
    private Shimmer shimmer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dier, null);
        initListValues();
        initListValues1();
        textView= (TextView) view.findViewById(R.id.text);
        initListValues2();
        shimmerTextView= (ShimmerTextView) view.findViewById(R.id.shi);
        shimmer = new Shimmer();
        shimmer.start(shimmerTextView);
        button = (Button) view.findViewById(dianji);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((string!=null)&&(string1!=null)&&(string2!=null))
                {
                    string3=kongjiaoshi.guolv(string,string1,string2);
                    Log.i("xinxing",string3);
                    intent=new Intent(getContext(),xiangqing.class);
                    intent.putExtra("zhi",string3);
                    startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }else {
                    Toast.makeText(getContext(),"请全部选择后再点击",Toast.LENGTH_SHORT).show();
                }
                //textView.setText(string3);
            }
        });
        mSimpleListAdapter = new SimpleListAdapter(getContext(), mStrings);
        mSimpleListAdapter1 = new SimpleListAdapter(getContext(), keshi);
        mSimpleListAdapter2 = new SimpleListAdapter(getContext(), didian);
        mSearchableSpinner = (SearchableSpinner) view.findViewById(R.id.SearchableSpinner);
        mSearchableSpinner.setAdapter(mSimpleListAdapter);
        mSearchableSpinner.setOnItemSelectedListener(mOnItemSelectedListener);
        mSearchableSpinner.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                mSearchableSpinner1.hideEdit();
                mSearchableSpinner2.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });

        mSearchableSpinner1 = (SearchableSpinner) view.findViewById(R.id.SearchableSpinner1);
        mSearchableSpinner1.setAdapter(mSimpleListAdapter1);
        mSearchableSpinner1.setOnItemSelectedListener(mOnItemSelectedListener1);
        mSearchableSpinner1.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                mSearchableSpinner.hideEdit();
                mSearchableSpinner2.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });
        mSearchableSpinner2 = (SearchableSpinner) view.findViewById(R.id.SearchableSpinner2);
        mSearchableSpinner2.setAdapter(mSimpleListAdapter2);
        mSearchableSpinner2.setOnItemSelectedListener(mOnItemSelectedListener2);
        mSearchableSpinner2.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {
                mSearchableSpinner.hideEdit();
                mSearchableSpinner1.hideEdit();
            }

            @Override
            public void spinnerIsClosing() {

            }
        });

//        mSearchableSpinner3 = (SearchableSpinner) view.findViewById(R.id.SearchableSpinner3);
//        mSearchableSpinner3.setAdapter(mSimpleListAdapter);
//        mSearchableSpinner3.setOnItemSelectedListener(mOnItemSelectedListener);
//        mSearchableSpinner3.setStatusListener(new IStatusListener() {
//            @Override
//            public void spinnerIsOpening() {
//                mSearchableSpinner.hideEdit();
//                mSearchableSpinner3.hideEdit();
//            }
//
//            @Override
//            public void spinnerIsClosing() {
//
//            }
//        });
        return view;
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (!mSearchableSpinner.isInsideSearchEditText(event)) {
//            mSearchableSpinner.hideEdit();
//        }
//        if (!mSearchableSpinner1.isInsideSearchEditText(event)) {
//            mSearchableSpinner1.hideEdit();
//        }
//        if (!mSearchableSpinner2.isInsideSearchEditText(event)) {
//            mSearchableSpinner2.hideEdit();
//        }
//        return super.onTouchEvent(event);
//    }

    private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            //Toast.makeText(getActivity(), "Item on position " + position + " : " + mSimpleListAdapter.getItem(position+1) + " Selected", Toast.LENGTH_SHORT).show();
            string = (String) mSimpleListAdapter.getItem(position+1);
        }

        @Override
        public void onNothingSelected() {
            //Toast.makeText(getActivity(), "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };
    private OnItemSelectedListener mOnItemSelectedListener1 = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            //Toast.makeText(getActivity(), "Item on position " + position + " : " + mSimpleListAdapter1.getItem(position+1) + " Selected", Toast.LENGTH_SHORT).show();
            string1 = (String) mSimpleListAdapter1.getItem(position+1);
        }

        @Override
        public void onNothingSelected() {
            //Toast.makeText(getActivity(), "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };
    private OnItemSelectedListener mOnItemSelectedListener2 = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
           // Toast.makeText(getActivity(), "Item on position " + position + " : " + mSimpleListAdapter2.getItem(position+1) + " Selected", Toast.LENGTH_SHORT).show();
            string2 = (String) mSimpleListAdapter2.getItem(position+1);
        }

        @Override
        public void onNothingSelected() {
            //Toast.makeText(getActivity(), "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };

    private void initListValues() {
        mStrings.add("星期一");
        mStrings.add("星期二");
        mStrings.add("星期三");
        mStrings.add("星期四");
        mStrings.add("星期五");
        mStrings.add("星期六");
        mStrings.add("星期日");
    }

    private void initListValues1() {
        keshi.add("一二节");
        keshi.add("三四节");
        keshi.add("五六节");
        keshi.add("七八节");
    }

    private void initListValues2() {
        didian.add("王通a");
        didian.add("王通b");
        didian.add("王通c");
        didian.add("王通d");
        didian.add("子夏a");
        didian.add("子夏b");
        didian.add("子夏c");
        didian.add("裴秀楼");
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_reset) {
//            mSearchableSpinner.setSelectedItem(0);
//            mSearchableSpinner1.setSelectedItem(0);
//            mSearchableSpinner2.setSelectedItem(0);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
