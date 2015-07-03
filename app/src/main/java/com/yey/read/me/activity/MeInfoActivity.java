package com.yey.read.me.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
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
}
