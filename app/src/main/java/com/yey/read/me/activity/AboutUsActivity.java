package com.yey.read.me.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnItemClick;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.common.adapter.ServicesAdapter;
import com.yey.read.me.util.ListViewUtils;
import com.yey.read.util.UtilsLog;

import java.util.ArrayList;
import java.util.List;

public class AboutUsActivity extends BaseActivity{
    public static final String TAG = "AboutUsActivity";
//    @ViewInject(R.id.header_title)TextView titletv;
//    @ViewInject(R.id.left_btn)ImageView leftbtn;
    @ViewInject(R.id.lv_about_services)ListView lv_services;
    @ViewInject(R.id.tv_about_app_version)TextView tv_version;
    String currentVersion = "";
//    boolean firstcheck = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        UtilsLog.i(TAG, "onCreate");
        ViewUtils.inject(this);
        initview();
        initdata();
        tv_version.append(" 版本 " + getVersionName());
  /*      UmengUpdateAgent.setUpdateOnlyWifi(false);
        String updateAll = MobclickAgent.getConfigParams(this.getApplicationContext(), "updateAll");
        String updateByUserid = MobclickAgent.getConfigParams(this.getApplicationContext(), "updateByUserid");
        String updateByKid = MobclickAgent.getConfigParams(this.getApplicationContext(), "updateByKid");
        String deltaUpdate  = MobclickAgent.getConfigParams(this, "DeltaUpdate");
        UmengUpdateAgent.setDeltaUpdate(Boolean.valueOf(deltaUpdate));
        AccountInfo account = AppServer.getInstance().getAccountInfo();
        if(!updateAll.equals("-1")){
            UmengUpdateAgent.forceUpdate(MeAboutUsActivity.this);
            UmengUpdateAgent.setUpdateAutoPopup(false);
        }else if(!updateByKid.equals("-1")){
            if(updateByKid.contains(String.valueOf(account.getKid()))){
                UmengUpdateAgent.forceUpdate(MeAboutUsActivity.this);
                UmengUpdateAgent.setUpdateAutoPopup(false);
            }
        }else if(!updateByUserid.equals("-1")){
            if(updateByUserid.contains(String.valueOf(account.getUid()))){
                UmengUpdateAgent.forceUpdate(MeAboutUsActivity.this);
                UmengUpdateAgent.setUpdateAutoPopup(false);
            }
        }

        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {

                switch (updateStatus) {
                    case 0: // has update
                        currentVersion = "发现新版本";
                        list.set(1,"检查更新"+"|"+currentVersion);
                        adapter.notifyDataSetChanged();
                        if(!firstcheck){
                            UmengUpdateAgent.showUpdateDialog(AppContext.getInstance(), updateInfo);
                        }
                        break;
                    case 1: // has no update
                        currentVersion = "已经是最新版本";
                        list.set(1,"检查更新"+"|"+currentVersion);
                        adapter.notifyDataSetChanged();
                        if(!firstcheck){
                            showToast("已经是最新版本");
                        }

                        break;
                    case 2: // none wifi
                        //showToast("没有wifi连接， 只在wifi下更新");
                        break;
                    case 3: // time out
                        //  showToast("超时");
                        break;
                }
                firstcheck = false;
            }
        });

        UmengUpdateAgent.setDialogListener(new UmengDialogButtonListener() {

            @Override
            public void onClick(int status) {
                switch (status) {
                    case UpdateStatus.Update:
                        SharedPreferencesHelper.getInstance(AppContext.getInstance()).setInt(AppConstants.PREF_ISLOGIN,0);
                        break;
                    case UpdateStatus.Ignore:

                        break;
                    case UpdateStatus.NotNow:

                        break;
                }
            }
        });*/
    }

    public void initview()
    {
//        leftbtn.setVisibility(View.VISIBLE);
//        leftbtn.setOnClickListener(this);
//        titletv.setVisibility(View.VISIBLE);
//        titletv.setText("关于时光树");
    }

    public void initdata()
    {
        //图标数据
        List<Integer> iconList = new ArrayList<>();
        iconList.add(R.drawable.icon_me_shareapp);
        //iconList.add(R.drawable.icon_me_priceapp);
        iconList.add(R.drawable.icon_me_update);
        iconList.add(R.drawable.icon_me_suggest);
        //文本数据
        List<String> textList = new ArrayList<>();
        textList.add("分享App");
        currentVersion = "已经是最新版本";
        //textList.add("给APP评分");
        textList.add("检查更新"+"/"+currentVersion);
        textList.add("意见反馈");
        ServicesAdapter adapter = new ServicesAdapter(this,iconList,textList);
        lv_services.setAdapter(adapter);
        //根据item数量重新设置listview高度
        ListViewUtils.setListViewHeightBasedOnChild(lv_services);
//        lv_services.setOnItemClickListener(this);
    }

    @OnItemClick(R.id.lv_about_services)
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
//        Intent intent;
        switch (position) {
            case 0:                     //分享app
                openActivity(MeShareActivity.class);
                break;
			/*case 2:
				//评分
				Uri uri = Uri.parse("market://details?id="+getPackageName());
				Intent mark = new Intent(Intent.ACTION_VIEW,uri);  
				mark.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
				startActivity(mark);  
				break;*/
            /*case 1:                      //检查更新
                UmengUpdateAgent.setUpdateOnlyWifi(false);
                String updateAll = MobclickAgent.getConfigParams(this.getApplicationContext(), "updateAll");
                String updateByUserid = MobclickAgent.getConfigParams(this.getApplicationContext(), "updateByUserid");
                String updateByKid = MobclickAgent.getConfigParams(this.getApplicationContext(), "updateByKid");
                String deltaUpdate  = MobclickAgent.getConfigParams(this, "DeltaUpdate");
                UmengUpdateAgent.setDeltaUpdate(Boolean.valueOf(deltaUpdate));
                AccountInfo account = AppServer.getInstance().getAccountInfo();
                if(!updateAll.equals("-1")){
                    UmengUpdateAgent.forceUpdate(MeAboutUsActivity.this);
                    UmengUpdateAgent.setUpdateAutoPopup(true);
                }else if(!updateByKid.equals("-1")){
                    if(updateByKid.contains(String.valueOf(account.getKid()))){
                        UmengUpdateAgent.forceUpdate(MeAboutUsActivity.this);
                        UmengUpdateAgent.setUpdateAutoPopup(true);
                    }
                }else if(!updateByUserid.equals("-1")){
                    if(updateByUserid.contains(String.valueOf(account.getUid()))){
                        UmengUpdateAgent.forceUpdate(MeAboutUsActivity.this);
                        UmengUpdateAgent.setUpdateAutoPopup(true);
                    }
                }
                break;*/
            case 2:  //意见反馈
                openActivity(MeOpinionActivity.class);
                break;
            default:
                break;
        }

    }

    public String getVersionName()
    {
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            packInfo = packageManager.getPackageInfo(getPackageName(),0);
            return packInfo.versionName;
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
