package com.yey.read.me.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.util.UtilsLog;

public class UpdatePasswordActivity extends BaseActivity {
    public static final String TAG = "UpdatePasswordActivity";
    //    @ViewInject(R.id.header_title)TextView tv;
//    @ViewInject(R.id.left_btn)ImageView leftbtn;
    @ViewInject(R.id.et_idsafe_oldpw)EditText et_oldpw;
    @ViewInject(R.id.et_idsafe_newpw1)EditText et_newpw1;
    @ViewInject(R.id.et_idsafe_newpw2)EditText et_newpw2;
//    @ViewInject(R.id.btn_idsafe_submit)Button btn_submit;
    //    AccountInfo accountInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        UtilsLog.i(TAG, "onCreate");
//        accountInfo=AppServer.getInstance().getAccountInfo();
        ViewUtils.inject(this);
        initView();
    }
    private void initView() {
//        tv.setText("修改密码");
//        leftbtn.setVisibility(View.VISIBLE);
    }

    @OnClick({(R.id.btn_idsafe_submit)})
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.left_btn:
//                this.finish();
//                break;
            case R.id.btn_idsafe_submit:
                final String oldpw=et_oldpw.getText().toString().trim();
                final  String newpw1=et_newpw1.getText().toString().trim();
                String newpw2 = et_newpw2.getText().toString().trim();
//              System.out.println("accountInfo.getPassword()---"+accountInfo.getPassword());

                //验证输入长度(必须6~20)
                if(!checkInput(oldpw)) return;
                if(!checkInput(newpw1)) return;
                if(!checkInput(newpw2)) return;
                //验证新密码两次是否一致
                if (!newpw2.equals(newpw1)){
                    showToast("您新密码前后输入不一致");
                    return;
                }
                //验证旧密码是否正确
               /* if (!newpw.equals(accountInfo.getPassword())) {
                    showToast("当前密码验证失败");
                    return;
                }*/
                showLoadingDialog("正在修改...");
               /* AppServer.getInstance().modifyPassword(accountInfo.getUid(), accountInfo.getPassword(), confirmpw, new OnAppRequestListener() {
                    @Override
                    public void onAppRequest(int code, String message, Object obj) {
                        if (code == 0){
                            Toast.makeText(IdSafeActivityAmend.this, "修改成功", Toast.LENGTH_SHORT).show();
                            AppServer.getInstance().getAccountBean().setPassword(confirmpw);
                            DbHelper.updateAccountInfo(AppServer.getInstance().getAccountBean());
//                              AppServer.getInstance().setmAccountBean(accountInfo);
                            IdSafeActivityAmend.this.finish();
                        } else {
                            Toast.makeText(IdSafeActivityAmend.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                        cancelLoadingDialog();
                    }
                });*/
                break;
        }
    }

    /**
     * 验证输入长度是否正确(6~20)
     * 正确返回true
     * 不正确返回false
     * @param input 编辑框输入
     */
    private boolean checkInput(String input) {
        if("".equals(input)){
            showToast("密码不能为空");
            return false;
        }
        if (input.length() < 6){
            showToast("密码长度过短，请您重新设置");
            return false;
        }
        if (input.length() > 20) {
            showToast("密码长度过长，请您重新设置");
            return false;
        }
        return true;
    }

    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
}
