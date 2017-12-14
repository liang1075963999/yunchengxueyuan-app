package com.example.liang.siruanbei.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.adapter.SimpleListAdapter;
import com.example.liang.siruanbei.shimmer.Shimmer;
import com.example.liang.siruanbei.shimmer.ShimmerTextView;

import java.util.ArrayList;

import gr.escsoft.michaelprimez.searchablespinner.SearchableSpinner;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.IStatusListener;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.OnItemSelectedListener;

import static com.example.liang.siruanbei.BmobApplication.getContext;
import static com.example.liang.siruanbei.R.id.dianji;
import static com.example.liang.siruanbei.R.id.shi;

/**
 * Created by liang on 2017/12/10.
 */

public class KongJiaoShi extends AppCompatActivity {
    private Toolbar toolbar;
    private SearchableSpinner mSearchableSpinner;
    private SearchableSpinner mSearchableSpinner1;
    private SearchableSpinner mSearchableSpinner2;
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
    private com.example.liang.siruanbei.utils.kongjiaoshi kongjiaoshi = com.example.liang.siruanbei.utils.kongjiaoshi.getInstance();
    private ShimmerTextView shimmerTextView;
    private Shimmer shimmer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kongjiaoshi);
        initListValues();
        initListValues1();
        textView= (TextView)findViewById(R.id.text);
        initListValues2();
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        shimmerTextView= (ShimmerTextView)findViewById(shi);
        shimmer = new Shimmer();
        shimmer.start(shimmerTextView);
        button = (Button)findViewById(dianji);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((string!=null)&&(string1!=null)&&(string2!=null))
                {
                    string3= kongjiaoshi.guolv(string,string1,string2);
                    Log.i("xinxing",string3);
                    intent=new Intent(getContext(),xiangqing.class);
                    intent.putExtra("zhi",string3);
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(KongJiaoShi.this).toBundle());
                }else {
                    Toast.makeText(getContext(),"请全部选择后再点击",Toast.LENGTH_SHORT).show();
                }
                //textView.setText(string3);
            }
        });
        mSimpleListAdapter = new SimpleListAdapter(getContext(), mStrings);
        mSimpleListAdapter1 = new SimpleListAdapter(getContext(), keshi);
        mSimpleListAdapter2 = new SimpleListAdapter(getContext(), didian);
        mSearchableSpinner = (SearchableSpinner)findViewById(R.id.SearchableSpinner);
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

        mSearchableSpinner1 = (SearchableSpinner)findViewById(R.id.SearchableSpinner1);
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
        mSearchableSpinner2 = (SearchableSpinner)findViewById(R.id.SearchableSpinner2);
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
    }
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
}
