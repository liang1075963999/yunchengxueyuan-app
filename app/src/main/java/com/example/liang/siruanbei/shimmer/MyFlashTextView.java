//package com.example.liang.siruanbei.utils;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//
//import com.example.liang.siruanbei.R;
//import com.example.liang.siruanbei.shimmer.Shimmer;
//import com.example.liang.siruanbei.shimmer.ShimmerButton;
//import com.example.liang.siruanbei.shimmer.ShimmerTextView;
//
//
//public class MyFlashTextView extends AppCompatActivity {
//    private ShimmerTextView shimmerTv;
//    private Button btn01;
//    private ShimmerButton btn02;
//    Shimmer shimmer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_flash_text_view);
//
//
//        initView();
//    }
//
//    private void initView() {
//        shimmerTv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
//        btn01 = (Button) findViewById(R.id.btn_01);
//        btn02 = (ShimmerButton) findViewById(R.id.btn_02);
//        btn01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (shimmer != null && shimmer.isAnimating()) {
//                    shimmer.cancel();
//                } else {
//                    shimmer = new Shimmer();
//                    shimmer.start(shimmerTv);
//                    shimmer.start(btn02);
//                }
//            }
//        });
//    }
//
//
//}
