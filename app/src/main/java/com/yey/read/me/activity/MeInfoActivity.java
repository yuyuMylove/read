package com.yey.read.me.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.me.util.ListViewUtils;
import com.yey.read.util.UtilsLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeInfoActivity extends BaseActivity {
    public static final String TAG = "MeInfoActivity";
    private static final String PATH = Environment
            .getExternalStorageDirectory() + "/yey/kindergaten/uploadimg/";
    private String name;
    private static final int CAMERA_SUCCESS = 2;    //拍照成功

    @ViewInject(R.id.lv_me_baby_info)ListView lv_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_info);
        UtilsLog.i(TAG, "onCreate");
        ViewUtils.inject(this);
        initListView();
    }

    private void initListView() {
        String[] items = {"性别","生日","昵称","兴趣爱好"};
        List<Map<String,String>> data = new ArrayList<>();
        for(String item:items){
            Map<String,String> map = new HashMap<>();
            map.put("item",item);
            //放入动态数据
            map.put("info","xxx");
            data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,data, R.layout.item_me_baby_info,
                new String[]{"item","info"},
                new int[]{R.id.tv_me_baby_item, R.id.tv_me_baby_info});
        lv_info.setAdapter(adapter);
        //设置listview高度
        ListViewUtils.setListViewHeightBasedOnChild(lv_info);
    }

    @OnClick(R.id.layout_me_baby_head)
    public void onClick(View v){
        //点击更换头像
        CharSequence[] items = {"相册", "拍照"};
        showDialogItems(items, "选择图片", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if(item==0){   //相册
                    openActivity(GalleryActivity.class);
//                                    Intent getImage = new Intent(Intent.ACTION_PICK);
//			                       /* getImage.addCategory(Intent.CATEGORY_OPENABLE);
//			                        getImage.setType("image/*");
//			                        getImage.putExtra("return-data", true);*/
//                                    getImage.setDataAndType(
//                                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
//                                    startActivityForResult(getImage, PHOTO_SUCCESS);   	//1
                }else{ 		   //相机
                    /*Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    name = DateFormat.format("yyyyMMddhhmmss",
                            Calendar.getInstance(Locale.CHINA))
                            + ".jpg";
                    File file = new File(PATH+"takephoto/");
                    if(!file.exists()){
                        file.mkdirs();// 创建文件夹
                    }
                    Uri imageUri = Uri.fromFile(new File(PATH, name));
                    System.out.println("imageUri----"+imageUri.toString());
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CAMERA_SUCCESS);*/
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*if(requestCode==CAMERA_SUCCESS){     //2
            if (!Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                showToast("SD不可用");
                return;
            }
            isFromCamera = true;
            startCropImage(PATH+name);

            accountInfo.setAvatar(PATH+name);
            AppServer.getInstance().getAccountBean().setAvatar(PATH+name);
            UtilsLog.i(TAG,"requestCode==CAMERA_SUCCESS set accountBean success,path is: " + PATH + name + "");

		    *//*Bitmap bitmap = BitmapUtil.getImageByPath(PATH+name, false);
		    if(bitmap!=null){
		       	 showLoadingDialog("正在上传...");
		    	 uploadImage(accountInfo.getUid(),save(PATH+name));
		    }*//*
        }*/
    }
}
