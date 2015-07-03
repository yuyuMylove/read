package com.yey.read.me.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.util.UtilsLog;


public class AccountSafeActivity extends BaseActivity{
    public static final String TAG = "AccountSafeActivity";
    //    private ArrayList<String> list =new ArrayList<String>();
//    private ArrayList<String> alist =new ArrayList<String>();
//    @ViewInject(R.id.layout_idsafe_updatepw)RelativeLayout layout_updatepw;
//    @ViewInject(R.id.layout_idsafe_bangdephone)RelativeLayout layout_bangdephone;
//    @ViewInject(R.id.tv_idsafe_phone)TextView tv_phone;
    //    AccountInfo accountInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_safe);
        UtilsLog.i(TAG, "onCreate");
//        accountInfo= AppServer.getInstance().getAccountInfo();
        ViewUtils.inject(this);
        initView();
    }
    private void initView() {
//        titletv.setText("账号安全");
//        leftbtn.setVisibility(View.VISIBLE);
    }


    @OnClick({(R.id.layout_idsafe_updatepw),(R.id.layout_idsafe_bangdephone)})
    public void onClick(View v) {
//        Intent intent;
        switch (v.getId()) {
//            case R.id.left_btn:
//                this.finish();
//                break;
            case R.id.layout_idsafe_updatepw:
                openActivity(UpdatePasswordActivity.class);
                finish();
                break;
            case R.id.layout_idsafe_bangdephone:
                openActivity(BindPhoneNumActivity.class);
                finish();
                break;
            default:
                break;
        }
    }

    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
       /* if (accountInfo.getPhone()!=null&&!accountInfo.getPhone().equals("")) {
            phoneText.setText(accountInfo.getPhone());
        }else{
            phoneText.setText("该账号尚未绑定手机号");
        }*/
    }
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            this.finish();
        }
        return false;
    }
}
