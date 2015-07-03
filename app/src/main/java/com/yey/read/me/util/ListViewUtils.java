package com.yey.read.me.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * listview工具类
 * Created by cm_pc2 on 2015/7/1.
 */
public class ListViewUtils {
    /**
     * 动态设置listview高度
     * @param lv 传入一个listview
     */
    public static void setListViewHeightBasedOnChild(ListView lv) {
        ListAdapter adapter = lv.getAdapter();
        if(adapter==null)
            return;
        //动态测量第一个item高度
        View item = adapter.getView(0,null,lv);
        item.measure(0,0);
        //item高度*item数量+分割线高度*(item数量-1)
        int totalHeight=item.getMeasuredHeight()*adapter.getCount()+lv.getDividerHeight()*(adapter.getCount()-1);
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight;
        lv.setLayoutParams(params);
    }
}
