package com.yey.read.me.activity;

import android.os.Bundle;
import android.widget.GridView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.me.adapter.VIPPrivilegesAdapter;
import com.yey.read.util.UtilsLog;

public class VIPCenterActivity extends BaseActivity {
    public static final String TAG = "VIPCenterActivity";
    @ViewInject(R.id.gv_vip_privilege)GridView gv_privilege;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_center);
        UtilsLog.i(TAG, "onCreate");
        ViewUtils.inject(this);
        initViews();
    }

    private void initViews() {
        //初始化特权
        int[] icons = {
                R.drawable.icon_vip_privilege1,
                R.drawable.icon_vip_privilege2,
                R.drawable.icon_vip_privilege3
        };
        String[] privileges = {"免费绘本动画","子涵讲绘本","图画书经典"};
        VIPPrivilegesAdapter adapter = new VIPPrivilegesAdapter(icons,privileges,this);
        gv_privilege.setAdapter(adapter);
    }
}
