package com.yey.read.me;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yey.read.R;
import com.yey.read.common.fragment.BaseFragment;
import com.yey.read.login.controller.LoginActivity;
import com.yey.read.me.activity.AboutUsActivity;
import com.yey.read.me.activity.AccountSafeActivity;
import com.yey.read.me.activity.MeInfoActivity;
import com.yey.read.me.activity.VIPCenterActivity;
import com.yey.read.common.adapter.ServicesAdapter;
import com.yey.read.me.util.ListViewUtils;
import com.yey.read.me.viewmodel.MeViewModel;
import com.yey.read.util.UtilsLog;

import java.util.ArrayList;
import java.util.List;

public class MeFragment extends BaseFragment {
    public static final String TAG = "MeFragment";
    @ViewInject(R.id.lv_me_services) ListView lv_services;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UtilsLog.i(TAG, "onCreateView");
        //初始化ListView
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        ViewUtils.inject(this,view);
        //初始化数据
        initListView();
        return view;
    }

    private void initListView() {
        //图标数据
        List<Integer> iconList = new ArrayList<>();
        iconList.add(R.drawable.icon_me_vip_center);
        iconList.add(R.drawable.icon_me_my_point);
        iconList.add(R.drawable.icon_me_safty);
        iconList.add(R.drawable.icon_me_about);
        //文本数据
        List<String> textList = new ArrayList<>();
        textList.add("会员中心");
        textList.add("积分");
        textList.add("安全");
        textList.add("关于");
        ServicesAdapter adapter = new ServicesAdapter(getActivity(),iconList,textList);
        lv_services.setAdapter(adapter);
        //根据item数量重新设置listview高度
        ListViewUtils.setListViewHeightBasedOnChild(lv_services);
    }
    @OnClick({R.id.layout_me_info,R.id.btn_me_signout})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.layout_me_info:     //点击我的宝贝
                startAnimActivity(MeInfoActivity.class);
                break;
            case R.id.btn_me_signout:     //点击退出登录
                startAnimActivity(LoginActivity.class);
                //退出登录相关处理
                MeViewModel vm = new MeViewModel();
                vm.logOutHandle();
                getActivity().finish();
                break;
        }
    }
    @OnItemClick(R.id.lv_me_services)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:         //点击会员中心
                startAnimActivity(VIPCenterActivity.class);
                break;
            case 1:         //点击积分
                ShowToast("功能还没开放哦，敬请期待！");
                break;
            case 2:         //点击安全
                startAnimActivity(AccountSafeActivity.class);
                break;
            case 3:         //点击关于
                startAnimActivity(AboutUsActivity.class);
                break;
        }
    }
}
