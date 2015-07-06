package com.yey.read.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yey.read.R;
import com.yey.read.common.adapter.ServicesAdapter;
import com.yey.read.common.fragment.BaseFragment;
import com.yey.read.me.util.ListViewUtils;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends BaseFragment {
    @ViewInject(R.id.lv_service_basic)ListView lv_basic;
    @ViewInject(R.id.lv_service_vip)ListView lv_vip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sevice, container, false);
        ViewUtils.inject(this,view);
        initListView();
        return view;
    }

    private void initListView() {
        //基本服务listview
        List<Integer> iconList = new ArrayList<>();
        List<String> textList = new ArrayList<>();
        iconList.add(R.drawable.icon_service_guide);
        iconList.add(R.drawable.icon_service_booklist);
        iconList.add(R.drawable.icon_service_aiyuestory);
        textList.add("绘本指引");
        textList.add("个性书单");
        textList.add("爱阅故事");
        ServicesAdapter adapter = new ServicesAdapter(getActivity(),iconList,textList);
        lv_basic.setAdapter(adapter);
        ListViewUtils.setListViewHeightBasedOnChild(lv_basic);

        //会员服务listview
        List<Integer> iconList1 = new ArrayList<>();
        List<String> textList1 = new ArrayList<>();
        iconList1.add(R.drawable.icon_vip_privilege1);
        iconList1.add(R.drawable.icon_vip_privilege2);
        iconList1.add(R.drawable.icon_vip_privilege3);
        textList1.add("绘本动画");
        textList1.add("子函讲绘本");
        textList1.add("图画书经典");
        adapter = new ServicesAdapter(getActivity(),iconList1,textList1);
        lv_vip.setAdapter(adapter);
        ListViewUtils.setListViewHeightBasedOnChild(lv_vip);
    }
}
