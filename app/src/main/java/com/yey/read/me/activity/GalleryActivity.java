package com.yey.read.me.activity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yey.read.R;
import com.yey.read.common.AppManager;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.me.adapter.GalleryAdapter;

import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends BaseActivity {
    @ViewInject(R.id.gv_gallery)GridView gv_gallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ViewUtils.inject(this);
        initGridView();
    }

    private void initGridView() {
        //获取相册图片路径
        List<String> listImgPath = getImgPathList();
        //显示相册
        GalleryAdapter adapter = new GalleryAdapter(listImgPath,GalleryActivity.this);
        gv_gallery.setAdapter(adapter);
    }
    private List<String> getImgPathList() {
        ArrayList<String> list = new ArrayList<String>();
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { "_id", "_data" }, null, null, null);
        while (cursor.moveToNext()) {
            list.add("file://"+cursor.getString(1));// 将图片路径添加到list中
        }
        cursor.close();
        return list;
    }
    //相册item点击事件
    @OnItemClick(R.id.gv_gallery)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //显示确认上传对话框
        showDialog("友情提示：", "确认将这张照片作为头像上传？", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity();
        System.gc();
    }
}
