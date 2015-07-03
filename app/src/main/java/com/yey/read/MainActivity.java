package com.yey.read;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
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
    private SquareFragment squareFragment;
    private MeFragment meFragment;

    private AccountInfo accountInfo;
    private Button[] mTabs;
    private String type=null;

    private int index;
    private int currentTabIndex;


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
        squareFragment = new SquareFragment();
        serviceFragment = new ServiceFragment();
        meFragment = new MeFragment();

        fragments = new Fragment[] {homeFragment,squareFragment,serviceFragment,meFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homeFragment).show(homeFragment)
                .add(R.id.fragment_container, squareFragment).hide(squareFragment)
                .add(R.id.fragment_container, serviceFragment).hide(serviceFragment)
                .add(R.id.fragment_container, meFragment).hide(meFragment)
                .commitAllowingStateLoss();
    }
    /**
     * button点击事件
     * @param view
     */
    public void onTabSelect(View view) {
        /*homeFragment.hidePullMenu();
        serviceFragment.hidePullMenu();
        if(accountInfo!=null && accountInfo.getRole()==2){
            newcontactFragment.hidePullMenu();
        }else{
            contactFragment.hidePullMenu();
        }
        meFragment.hidePullMenu();*/
        switch (view.getId()) {
            case R.id.btn_home:
//                iv_recent_tips.setVisibility(View.GONE);
                index = 0;
                break;
            case R.id.btn_square:
                index = 1;
                break;
            case R.id.btn_service:
                index = 2;
                break;
            case R.id.btn_me:
                index = 3;
                break;
        }
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commitAllowingStateLoss();
        }
        mTabs[currentTabIndex].setSelected(false);
        //把当前tab设为选中状态
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }
}
