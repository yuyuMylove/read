package com.yey.read;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yey.read.common.AccountInfo;
import com.yey.read.common.AppConfig;
import com.yey.read.common.AppContext;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.home.fragment.HomeFragment;
import com.yey.read.me.MeFragment;
import com.yey.read.service.ServiceFragment;
import com.yey.read.square.SquareFragment;


public class MainActivity extends BaseActivity {

    @ViewInject(R.id.btn_home)Button btn_home;
    @ViewInject(R.id.btn_square)Button btn_square;
    @ViewInject(R.id.btn_service)Button btn_service;
    @ViewInject(R.id.btn_me)Button btn_me;

    public static Fragment[] fragments;
    private HomeFragment homeFragment;
    private ServiceFragment serviceFragment;
    private SquareFragment squareFragement;
    private MeFragment meFragment;

    private AccountInfo accountInfo;
    private Button[] mTabs;
    private String type=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppContext.getInstance().finishActivitys();
        accountInfo= AppContext.getInstance().getAccountInfo();
        ViewUtils.inject(this);
        initView();
        initTab();
    }

    private void initView() {
        mTabs = new Button[4];
        mTabs[0] = btn_home;
        mTabs[1] = btn_square;
        mTabs[2] = btn_service;
        mTabs[3] = btn_me;
        //把第一个tab设为选中状态
        mTabs[0].setSelected(true);
        if(type!=null){
            if(type.equals(AppConfig.SWITCH_TYPE_HOME)){
                mTabs[0].setSelected(true);
            }else if(type.equals(AppConfig.SWITCH_TYPE_SERVICE)){
                mTabs[1].setSelected(true);
            }else if(type.equals(AppConfig.SWITCH_TYPE_SERVICE)){
                mTabs[2].setSelected(true);
            }else if(type.equals(AppConfig.SWITCH_TYPE_SERVICE)){
                mTabs[3].setSelected(true);
            }
        }
    }

    private void initTab() {
        homeFragment  = new HomeFragment();
//        serviceFragment = new ServiceFragment();
//        squareFragement = new SquareFragment();
//        meFragment = new MeFragment();

        fragments = new Fragment[] {homeFragment};
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, homeFragment)
                .show(homeFragment).commitAllowingStateLoss();
    }
}
