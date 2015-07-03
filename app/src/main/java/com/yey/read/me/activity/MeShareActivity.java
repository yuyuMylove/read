package com.yey.read.me.activity;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yey.read.R;
import com.yey.read.common.activity.BaseActivity;

public class MeShareActivity extends BaseActivity {
//    @ViewInject(R.id.left_btn) ImageView leftbtn;
    //    @ViewInject(R.id.header_title)TextView tv;
    @ViewInject(R.id.tv_me_share_website)TextView tv_website;
    @ViewInject(R.id.tv_me_share)TextView tv_share;
//    @ViewInject(R.id.me_share_bariv) ImageView iv_erweima;
//    AccountInfo accountInfo;
    String sharetext="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_share);
        ViewUtils.inject(this);
//        accountInfo= AppServer.getInstance().getAccountInfo();
        initView();
//        ShareSDK.initSDK(this);
    }
    private void initView() {
//        leftbtn.setVisibility(View.VISIBLE);
//        tv.setText("分享APP");
//        iv_erweima.setImageResource(R.drawable.erweima);
//        Bitmap b = BitmapUtil.drawableToBitmap(getResources().getDrawable(R.drawable.erweima));
//        BitmapUtil.saveMyBitmap(b, "shareapp.jpg");
        String html="<a href='http://sgs.yey.com/'>点击打开下载:http://sgs.yey.com/</a>";
        tv_website.setText(Html.fromHtml(html));
        tv_website.setMovementMethod(LinkMovementMethod.getInstance());
        sharetext="分享一个好玩好用的免费APP给你。\n把幼儿园装进手机，一切尽在“掌”握。\n方便实用的管理工具,\n丰富的教育资源,\n人性化的家园沟通平台。\n把你从日常的琐碎中解放出来，\n让您的工作更轻松！";
        tv_share.setText(sharetext);
    }



    @OnClick({(R.id.left_btn),(R.id.btn_share_weixin)})
    public void onclick(View v){
//        Intent intent;
        switch (v.getId()) {
//            case R.id.left_btn:
//                this.finish();
//                break;
            case R.id.btn_share_weixin:
/*
                OnekeyShare oks = new OnekeyShare();
                //关闭sso授权
//		        oks.setDialogMode();
                // 分享时Notification的图标和文字
                //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
                // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
                oks.setTitle("时光树分享");
                // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
                oks.setTitleUrl("http://sgs.yey.com/");
                // text是分享文本，所有平台都需要这个字段
                String  shareContent="分享一个好玩好用的免费APP给你。把幼儿园装进手机，一切尽在“掌”握。方便实用的管理工具,丰富的教育资源,人性化的家园沟通平台。把你从日常的琐碎中解放出来，让您的工作更轻松！下载链接:http://sgs.yey.com";
                oks.setText(shareContent);


                // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
                // oks.setImageUrl("http://sgs.yey.com/qrcode.png");
                // oks.setImagePath("http://sgs.yey.com/qrcode.png");
                // oks.setImagePath(FileUtils.getSDRoot() + "yey/" + "shareapp.png");
                // url仅在微信（包括好友和朋友圈）中使用
                oks.setUrl("http://sgs.yey.com/");
                // comment是我对这条分享的评论，仅在人人网和QQ空间使用
                oks.setComment("");
                // site是分享此内容的网站名称，仅在QQ空间使用
                oks.setSite(getString(R.string.app_name));
                // siteUrl是分享此内容的网站地址，仅在QQ空间使用
                oks.setSiteUrl("http://sgs.yey.com/");
                oks.setTheme(OnekeyShareTheme.CLASSIC);
                oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
                    @Override
                    public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                        if (QZone.NAME.equals(platform.getName())) {
                            //qq空间
                            paramsToShare.setImageUrl("http://sgs.yey.com/qrcode.png");
                        }else if(Wechat.NAME.equals(platform.getName())){
                            paramsToShare.setImageUrl("http://sgs.yey.com/qrcode.png");
                        }else if(QQ.NAME.equals(platform.getName())){
                            paramsToShare.setImageUrl("http://sgs.yey.com/qrcode.png");
                        }else if(Email.NAME.equals(platform.getName())){
                            paramsToShare.setImageUrl("http://sgs.yey.com/qrcode.png");
                        }
                    }
                });
                // 启动分享GUI
                oks.show(AppContext.getInstance());
*/

                break;
            default:
                break;
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
    protected void onDestroy() {
//        ShareSDK.stopSDK(this);
        super.onDestroy();
    }
}
