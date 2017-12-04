package com.example.liang.siruanbei.fragment;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.example.liang.siruanbei.Gerenshoucang;
import com.example.liang.siruanbei.Guanyuwomen;
import com.example.liang.siruanbei.R;
import com.example.liang.siruanbei.Zuijinjilu;
import com.example.liang.siruanbei.utils.Utils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.ActionSheetDialog;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.app.Activity.RESULT_OK;

/**
 * Created by liang on 2017/10/28.
 */

public class gerenyemian extends Fragment {
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;
    protected static Uri tempUri;
    private ImageView iv_personal_icon;
    private LinearLayout linearLayout;
    String imagePath;
    private String newName = "image.jpg";
    private PullToZoomScrollViewEx pullToZoomScrollViewEx;
    private String actionUrl = "http://user.ucplat.zhangmingyue.com/upload";
    private RelativeLayout relativeLayout;
    FileInputStream fStream ;
    private ImageView imageView;
    private View content;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.gerenyemian,null);
        View head=LayoutInflater.from(getContext()).inflate(R.layout.head,null);
        View zoom=LayoutInflater.from(getContext()).inflate(R.layout.zoom,null);
        content=LayoutInflater.from(getContext()).inflate(R.layout.content,null);
        pullToZoomScrollViewEx= (PullToZoomScrollViewEx) view.findViewById(R.id.pullzoom);
//        relativeLayout= (RelativeLayout) view.findViewById(R.id.rela);
//        linearLayout= (LinearLayout) view.findViewById(R.id.linear);
        iv_personal_icon = (ImageView)head.findViewById(R.id.iv_personal_icon);
        imageView= (ImageView) view.findViewById(R.id.iv);
        pullToZoomScrollViewEx.setHeaderView(head);
        pullToZoomScrollViewEx.setZoomView(zoom);
        pullToZoomScrollViewEx.setScrollContentView(content);
        iv_personal_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
				/*showChoosePicDialog();*/
                ActionSheetDialog();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        content.findViewById(R.id.tv_test1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(),Gerenshoucang.class);
                startActivity(intent);
            }
        });
        content.findViewById(R.id.tv_test2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(),Zuijinjilu.class);
                startActivity(intent);
            }
        });
        content.findViewById(R.id.tv_test3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getContext(),Guanyuwomen.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     *
    //	 * @param picdata
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            iv_personal_icon.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        imagePath = Utils.savePhoto(bitmap, Environment
                .getExternalStorageDirectory().getAbsolutePath(), String
                .valueOf(System.currentTimeMillis()));
        Log.e("imagePath", imagePath + "");
        if (imagePath != null) {
            // 拿着imagePath上传了
            // ...
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }



    /* 上传文件至Server的方法 */
    private void uploadFile() {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        try {

            URL url = new URL(actionUrl);//服务器地址
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
			/* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
			/* 设置传送的method=POST */
            con.setRequestMethod("POST");

			/* setRequestProperty *///设置请求属性
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);
			/* 设置DataOutputStream *///数据输出流
            //heading为服务器接收的键
            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; "
                    + "name=\"headimg\";filename=\"" + newName + "\"" + end);
            ds.writeBytes(end);
			/* 取得文件的FileInputStream */ //文件输入流
            fStream = new FileInputStream(imagePath);//要上传的图片路径，
			/* 设置每次写入1024bytes */
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
			/* 从文件读取数据至缓冲区 */
            while ((length = fStream.read(buffer)) != -1) {
				/* 将资料写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
            fStream.close();
            ds.flush();
			/* 取得Response内容 */
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
			/* 将Response显示于Dialog */
            showDialog("上传结果" + b.toString().trim());
            Log.e("文件传输结果", b.toString().trim());
			/* 关闭DataOutputStream */
            ds.close();
        } catch (Exception e) {
            showDialog("上传失败" + e);
            Log.i("败", "错误原因：" + e);
        }
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            Looper.prepare();// 创建消息循环

            uploadFile();
            Message msg = new Message();
//			data.putString("image",  fStream);
            handler.sendMessage(msg);
            Looper.loop();// 从消息队列取消
        }
    };
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        };
    };

    /* 显示Dialog的method */
    private void showDialog(String mess) {
        new AlertDialog.Builder(getContext()).setTitle("Message").setMessage(mess)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }


    private void ActionSheetDialog() {
        final String[] stringItems = {"相册", "拍照"};
        final ActionSheetDialog dialog = new ActionSheetDialog(getActivity(), stringItems, null);
        dialog.title("请选择以下方式")//
                .titleTextSize_SP(14.5f)//
                .show();

        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
//				T.showShort(JGHeadImgActivity.this, stringItems[position]);
                switch (position) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        Intent intent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setDataAndType(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                "image/*");
                        startActivityForResult(intent, CHOOSE_PICTURE);// //适用于4.4及以上android版本

                        break;
                    case TAKE_PICTURE: // 拍照
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);
                        break;
                }
                dialog.dismiss();
            }
        });
    }
}
