package com.yey.read.me.activity;

import android.os.Bundle;

import com.lidroid.xutils.ViewUtils;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;

public class MeOpinionActivity extends BaseActivity{
//    @ViewInject(R.id.left_btn)ImageView left_btn;
//    @ViewInject(R.id.right_tv)TextView  right_text;
//    @ViewInject(R.id.header_title)TextView  title_tv;
//    @ViewInject(R.id.et_me_opinion) EditText et_opinion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_opinion);
        ViewUtils.inject(this);
        initView();
    }
    private void initView() {
//        left_btn.setVisibility(View.VISIBLE);
//        left_btn.setOnClickListener(this);
//        right_text.setVisibility(View.VISIBLE);
//        right_text.setOnClickListener(this);
//        right_text.setText("完成");
//        title_tv.setText("意见反馈");
    }
   /* @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.right_tv:
                if (!et.getText().toString().trim().equals("")) {
                    hideSoftInputViewV2();
                    AppServer.getInstance().feedback(AppContext.getInstance().getAccountInfo().getUid(), et.getText().toString(), new OnAppRequestListener() {
                        @Override
                        public void onAppRequest(int code, String message, Object obj) {
                            // TODO Auto-generated method stub
                            if (code==AppServer.REQUEST_SUCCESS) {
                                finish();
                                showToast("反馈成功，谢谢您的宝贵意见");
                            }else{
                                showToast("反馈失败，待会再试试吧");
                            }
                        }
                    });
                }else{
                    showToast("反馈意见不能为空哦。");
                }
                break;
            case R.id.left_btn:
                finish();
                break;
            default:
                break;
        }
    }*/
}
