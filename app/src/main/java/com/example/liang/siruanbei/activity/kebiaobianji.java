package com.example.liang.siruanbei.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.kebiao.CourseModel;
import com.example.liang.siruanbei.shujuku.shujukucaozuo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/11/4.
 */

public class kebiaobianji extends Activity {
    private Spinner spinner;
    private Spinner spinner1;
    private EditText editText;
    private Button button;
    private String s[]={"1","3","5","7","9"};
    private String s1[]={"1","2","3","4","5","6","7"};
    private String string;
    public static String string1;
    private EditText editText1;
    public static List<CourseModel> courseModels[] = new ArrayList[7];
    public static List<CourseModel> models_1 = new ArrayList<>();
    public static final String FILENAME = "xth";
    SharedPreferences.Editor editor;
    private shujukucaozuo shujukucaozuo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kebiaobianji);
        shujukucaozuo=new shujukucaozuo(this);
//        SharedPreferences sharedPreferences=getSharedPreferences(kebiaobianji.FILENAME,MODE_MULTI_PROCESS);
//        try{
//            Log.i("cun",sharedPreferences.getString("shi",""));
//        }catch (Exception e){
//
//        }
        spinner= (Spinner) findViewById(R.id.spinner);
        spinner1= (Spinner) findViewById(R.id.spinner1);
        editText= (EditText) findViewById(R.id.edit);
        editText1= (EditText) findViewById(R.id.jiaoshi);
        for (int i = 0; i < courseModels.length; i++) {
            courseModels[i] = new ArrayList<>();
        }
        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s));
        spinner1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s1));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string=s[position];
//                Log.i("kebiao",string);
//                editor=getSharedPreferences(FILENAME, MODE_MULTI_PROCESS).edit();
//                editor.putString("shi",string);
//                editor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                string1=s1[position];
                Log.i("kebiao",string1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=(int)(Math.random() * 10);
                shujukucaozuo.shoucang(new CourseModel(0, editText.getText().toString(), Integer.parseInt(string), 2, Integer.parseInt(string1), editText1.getText().toString(),i));
                Log.i("color",i+"");
                Toast.makeText(kebiaobianji.this,"提交成功,记得在课表页面下拉刷新查看课表",Toast.LENGTH_SHORT).show();
//                models_1.add(new CourseModel(0, editText.getText().toString(), Integer.parseInt(string), 2, Integer.parseInt(string1), editText1.getText().toString(), (int) (Math.random() * 10)));
//                courseModels[Integer.parseInt(string1)-1].addAll(models_1);
            }
        });
    }
}
