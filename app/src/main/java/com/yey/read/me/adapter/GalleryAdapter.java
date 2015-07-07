package com.yey.read.me.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.yey.read.R;

import java.util.List;

/**
 * Created by cm_pc2 on 2015/7/6.
 */
public class GalleryAdapter extends BaseAdapter {
    DisplayImageOptions options;
    private List<String> listImgPath;
    private Context context;
    public GalleryAdapter(List<String> listImgPath, Context context) {
        this.listImgPath = listImgPath;
        this.context = context;
        //设置用ImageLoader加载图片的属性
        setOptions();
    }

    private void setOptions() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheInMemory(false)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
    }

    @Override
    public int getCount() {
        return listImgPath.size();
    }

    @Override
    public Object getItem(int position) {
        return listImgPath.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_gallery,null);
            viewHolder.imageview = (ImageView) convertView.findViewById(R.id.iv_gallery_photo);
            convertView.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) convertView.getTag();
            //使用ImageLoader加载图片
            ImageLoader.getInstance().displayImage(listImgPath.get(position),viewHolder.imageview,options);
        return convertView;
    }
    class ViewHolder {
        public ImageView imageview;
    }
}
