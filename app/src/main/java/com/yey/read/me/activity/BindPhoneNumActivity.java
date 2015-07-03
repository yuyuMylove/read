package com.yey.read.me.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.util.UtilsLog;

public class BindPhoneNumActivity extends BaseActivity{
    public static final String TAG = "BindPhoneNumActivity";
//    public Boolean isShowRecordFragmnet;
//    @ViewInject(R.id.header_title)TextView tv_title;
//    @ViewInject(R.id.left_btn)ImageView iv_left;
    @ViewInject(R.id.tv_bind_phone)TextView tv_bind;
    @ViewInject(R.id.tv_bind_phone_change)TextView tv_change;
    @ViewInject(R.id.iv_bind_phone_checkbox)ImageView iv_ischecked;
    @ViewInject(R.id.tv_bind_phone_ischecked)TextView tv_ischecked;

//    AccountInfo accountInfo;
    boolean isbinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone_num);
        UtilsLog.i(TAG, "onCreate");
        ViewUtils.inject(this);
/*
        accountInfo= AppServer.getInstance().getAccountInfo();
        if(accountInfo.getPhone()==null||accountInfo.getPhone().equals("")){
            isbinding=false;
        }else{
            isbinding=true;
        }
*/
        prepareView();
    }

    private void prepareView() {
//        iv_left.setVisibility(View.VISIBLE);
        if(isbinding){
            iv_ischecked.setImageResource(R.drawable.icon_safe_checkbox_true);
//            tv_title.setText("取消绑定");
//            tv_bind.setText(accountInfo.getPhone());
            tv_change.setText("取消绑定");
        }else{
            iv_ischecked.setImageResource(R.drawable.icon_safe_checkbox_false);
            tv_ischecked.setText("未绑定");
            tv_ischecked.setTextColor(getResources().getColor(R.color.bind_phone_cancel_text));
//            tv_title.setText("绑定手机");
            tv_bind.setText("手机号");
            tv_change.setText("绑定手机");
        }
    }

    @OnClick({(R.id.layout_bind_change)})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
//            case R.id.left_btn:
//                this.finish();
//                break;
            case R.id.layout_bind_change:
                if (isbinding) {
                    showLoadingDialog("正在取消绑定..");
                /*    AppServer.getInstance().bindPhone(accountInfo.getUid(), "", "", new OnAppRequestListener() {
                        @Override
                        public void onAppRequest(int code, String message, Object obj) {
                            cancelLoadingDialog();
                            if (code == AppServer.REQUEST_SUCCESS) {
                                showToast(message);
                                accountInfo.setPhone("");
                                AppServer.getInstance().setmAccountInfo(accountInfo);
                                AppServer.getInstance().setmAccountBean(new AccountBean(accountInfo));
                                DbHelper.updateAccountInfo(accountInfo);
                                IdSafeActivityChange.this.finish();
                            } else {
                                showToast(message);
                            }
                        }
                    });
                }else{
                    intent=new Intent(IdSafeActivityChange.this,BindPhoneActivity.class);
                    startActivity(intent);
                    finish();
                }*/
                    break;
                }
        }
    }

    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            openActivity(AccountSafeActivity.class);
            this.finish();
        }
        return false;
    }
}
