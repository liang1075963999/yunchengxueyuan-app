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
 * limitations under the License.
 */

package com.example.liang.siruanbei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.liang.siruanbei.activity.kebiaobianji;
import com.example.liang.siruanbei.fragment.dierye;
import com.example.liang.siruanbei.fragment.gerenyemian;
import com.example.liang.siruanbei.fragment.kebiao;
import com.example.liang.siruanbei.fragment.zhuyeFragment;

import de.greenrobot.event.EventBus;


public class DragTopActivity extends ActionBarActivity {

    public static final String TAG = "gongju";
    private final int CAMERA_REQUEST_CODE = 1;
    private Toolbar toolbar;
    private BottomNavigationBar bottomNavigationBar;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private zhuyeFragment zhuyeFragment=new zhuyeFragment();
    private dierye dierye;
    private kebiao kebiao;
    private gerenyemian gerenyemian;
    private FragmentTransaction fragmentTransaction;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragtop);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.commit();
//        fragmentTransaction=fragmentManager.beginTransaction();
//        replacefragment(new zhuyeFragment());
//        zhuyeFragment = new zhuyeFragment();
        fragmentTransaction.add(R.id.frame,zhuyeFragment);
        fragmentTransaction.commit();
       /// requestPermission7();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);
        initview();
        // Optional setting or set them in your xml.
//        dragLayout.setOverDrag(true)
//                .setCollapseOffset(100)
//                .listener(new DragTopLayout.SimplePanelListener() {
//                    @Override
//                    public void onSliding(float ratio) {
//                        super.onSliding(ratio);
//                    }
//                })
//                .closeTopView(false);


        // init pager


    }

    public void hideFragments(FragmentTransaction ft) {
        if (zhuyeFragment != null)
            ft.hide(zhuyeFragment);
        if (dierye != null)
            ft.hide(dierye);
        if (kebiao != null)
            ft.hide(kebiao);
        if (gerenyemian != null)
            ft.hide(gerenyemian);
    }
//    private void replacefragment(Fragment fragment) {
////        fragmentManager=getSupportFragmentManager();
////        fragmentTransaction=fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frame,fragment);
//        fragmentTransaction.commit();
//    }

    private void initview() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom);
        frameLayout = (FrameLayout) findViewById(R.id.frame);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_home_blue_100_48dp, "主页")).setActiveColor(R.color.material_blue);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_account_balance_blue_100_48dp, "空教室")).setActiveColor(R.color.material_blue);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_content_paste_blue_100_48dp, "课表")).setActiveColor(R.color.material_blue);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.ic_face_blue_100_48dp, "个人中心")).setActiveColor(R.color.material_blue);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(android.R.color.white);
        bottomNavigationBar.initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (position == 0) {
                    toolbar.setTitle("主页");
                    setSupportActionBar(toolbar);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    hideFragments(fragmentTransaction);
                    if (zhuyeFragment != null)
                        fragmentTransaction.show(zhuyeFragment);
                        // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                    else {
                        zhuyeFragment = new zhuyeFragment();
                        fragmentTransaction.add(R.id.frame, zhuyeFragment);
                    }
                    fragmentTransaction.commit();
//                    replacefragment(new zhuyeFragment());
                } else if (position == 1) {
                    toolbar.setTitle("空教室");
                    setSupportActionBar(toolbar);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    hideFragments(fragmentTransaction);
                    if (dierye != null)
                        fragmentTransaction.show(dierye);
                        // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                    else {
                        dierye = new dierye();
                        fragmentTransaction.add(R.id.frame, dierye);
                    }
//                    replacefragment(new dierye());
                    fragmentTransaction.commit();
                }
                else if (position == 2) {
                    toolbar.setTitle("课表");
                    toolbar.inflateMenu(R.menu.caidan);
                    toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            intent = new Intent(DragTopActivity.this, kebiaobianji.class);
                            startActivity(intent);
                            return true;
                        }
                    });
//                    setSupportActionBar(toolbar);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    hideFragments(fragmentTransaction);
                    if (kebiao != null)
                        fragmentTransaction.show(kebiao);
                        // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                    else {
                        kebiao = new kebiao();
                        fragmentTransaction.add(R.id.frame, kebiao);
                    }
//                    replacefragment(new gerenyemian());
                    fragmentTransaction.commit();
                }
                else if (position == 3) {
                    toolbar.setTitle("个人中心");
                    setSupportActionBar(toolbar);
                    fragmentTransaction = fragmentManager.beginTransaction();
                    hideFragments(fragmentTransaction);
                    if (gerenyemian != null)
                        fragmentTransaction.show(gerenyemian);
                        // 否则是第一次切换则添加fragment1，注意添加后是会显示出来的，replace方法也是先remove后add
                    else {
                        gerenyemian = new gerenyemian();
                        fragmentTransaction.add(R.id.frame, gerenyemian);
                    }
//                    replacefragment(new gerenyemian());
                    fragmentTransaction.commit();
                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }

   /* private void requestPermission7() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                new AlertDialog.Builder(this)
                        .setMessage("申请相机权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(DragTopActivity.this,
                                        new String[]{Manifest.permission.READ_PHONE_STATE}, CAMERA_REQUEST_CODE);
                            }
                        })
                        .show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE}, CAMERA_REQUEST_CODE);
            }
        } else {
        }
    }*/


    // Handle scroll event from fragments
    public void onEvent(Boolean b) {
//        dragLayout.setTouchMode(b);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.caidan, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if (id == R.id.action_menu_icon) {
//            if (topImageView.getVisibility() == View.GONE) {
//                topImageView.setVisibility(View.VISIBLE);
//            } else {
//                topImageView.setVisibility(View.GONE);
//            }
//            return true;
//        } else if (id == R.id.action_toggle) {
//            dragLayout.toggleTopView();
//            return true;
//        } else if (id == R.id.action_over_drag) {
//            dragLayout.setOverDrag(!dragLayout.isOverDrag());
//            Toast.makeText(this, "overDrag:" + dragLayout.isOverDrag(), Toast.LENGTH_SHORT).show();
//            return true;
//        } else if (id == R.id.action_offset) {
//            if (dragLayout.getCollapseOffset() == 0) {
//                dragLayout.openTopView(true);
//                dragLayout.setCollapseOffset(200);
//            } else {
//                dragLayout.setCollapseOffset(0);
//            }
//            Toast.makeText(this, "offset:" + dragLayout.getCollapseOffset(), Toast.LENGTH_SHORT).show();
//            return true;
//        } else if (id == R.id.action_pulltorefresh) {
//            Intent intent = new Intent(this, PullToRefreshActivity.class);
//            startActivity(intent);
//            return true;
//        } else if (id == R.id.action_about) {
//            Intent intent = new Intent(this, AboutActivity.class);
//            startActivity(intent);
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
