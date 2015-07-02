package com.yey.read.login.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yey.read.R;
import com.yey.read.common.AccountInfo;
import com.yey.read.common.AppConfig;
import com.yey.read.common.AppContext;
import com.yey.read.common.activity.BaseActivity;
import com.yey.read.util.DialogTips;
import com.yey.read.util.ImageLoadOptions;
import com.yey.read.util.SharedPreferencesHelper;
import com.yey.read.widget.loginwidget.CircleImageView;
import com.yey.read.widget.loginwidget.CustomAutoCompleteTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends BaseActivity {

    @ViewInject(R.id.btn_activity_login)Button loginButton;
    @ViewInject(R.id.tv_activity_login_login_forget)TextView forgetTextView;
    @ViewInject(R.id.tv_activity_login_login_register)TextView registerTextView;
    CustomAutoCompleteTextView accountEditText;
    @ViewInject(R.id.edt_activity_login_login_password)EditText passwordEditText;
    @ViewInject(R.id.login_logo)CircleImageView avatarImageView;

    boolean isRun, flag;
    DialogTips dialog;
    AccountInfo info;
    AppContext appcontext;
    SharedPreferences settings;
    List<Map<String,String>> list;

    private Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            isRun = false;
            flag = false;
            if(msg.what==111){
                String path=(String) msg.obj;
                ImageLoader.getInstance().displayImage(path, avatarImageView, ImageLoadOptions.getAppPicOptions());

            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appcontext = AppContext.getInstance();
        settings=this.getSharedPreferences(SharedPreferencesHelper.PREF_LOGIN_FILE, Context.MODE_PRIVATE);
        ViewUtils.inject(this);

        accountEditText = (CustomAutoCompleteTextView)this.findViewById(R.id.edt_activity_login_login_id);
        accountEditText.setThreshold(1);
        accountEditText.setDropDownBackgroundResource(R.color.white);

        final Map<String, ?> allMap= settings.getAll();
        final List<Map<String,String>> aList = new ArrayList<Map<String,String>>();
        list= new ArrayList<Map<String,String>>();
        Set<String> keysSet = allMap.keySet();
        Iterator<String> iterator = keysSet.iterator();

        while(iterator.hasNext()) {
            Object key = iterator.next();//key
            Object value = allMap.get(key);//value

            if(value.toString().contains("||")){
                HashMap<String, String> hm = new HashMap<String,String>();
                HashMap<String, String> pic = new HashMap<String,String>();
                String account=value.toString().substring(0,value.toString().indexOf("||"));
                String url=value.toString().substring(value.toString().lastIndexOf("||")+2);
                hm.put("txt", account);
                pic.put(account, url);
                aList.add(hm);
                list.add(pic);
            }
        }
        thread.start();

        String[] from = {"txt"};
        int[] to = { R.id.txt};
        SimpleAdapter adapter = new SimpleAdapter(this, aList, R.layout.common_autocomplete_layout, from, to);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                String path=null;
                for(int i=0;i<list.size();i++){
                    if(list.get(i).get(accountEditText.getText().toString())!=null){
                        path=list.get(i).get(accountEditText.getText().toString());
                        //imageLoader.init(ImageLoaderConfiguration.createDefault(LoginActivity.this));
                        imageLoader.displayImage(path, avatarImageView, ImageLoadOptions.getAppPicOptions());
                    }
                }
                String pas_pref=allMap.get(accountEditText.getText().toString().trim()).toString();

                String password_has_key=pas_pref.substring(pas_pref.indexOf("||")+2,pas_pref.lastIndexOf("||"));

                String password=password_has_key.substring(0,password_has_key.length()-5);

                passwordEditText.setText(password);
            }
        };
        accountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {


            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {

            }
            @Override
            public void afterTextChanged(Editable arg0) {
                String account=arg0.toString();
                String path=null;
                for(int i=0;i<list.size();i++){
                    if(list.get(i).get(account)!=null){
                        path=list.get(i).get(accountEditText.getText().toString());
                        //imageLoader.init(ImageLoaderConfiguration.createDefault(LoginActivity.this));
                        imageLoader.displayImage(path, avatarImageView, ImageLoadOptions.getAppPicOptions());
                    }
                }
            }
        });
        accountEditText.setOnItemClickListener(itemClickListener);
        accountEditText.setAdapter(adapter);
        SharedPreferences setting=this.getSharedPreferences(AppConfig.LOGIN_DEFALUTE_VALUE, Context.MODE_PRIVATE);
        if(setting!=null&&!setting.getAll().isEmpty()){
            String password=setting.getString(AppConfig.LOGIN_DEFAULTE_PASSWORD, "");
            String account=setting.getString(AppConfig.LOGIN_DEFAULTE_ACCOUNT, "");
            String url=setting.getString(AppConfig.LOGIN_DEFAULTE_AVATER, "");
            passwordEditText.setText(password.substring(0,password.length()-5).trim());
            accountEditText.setText(account);
            // imageLoader.init(ImageLoaderConfiguration.createDefault(LoginActivity.this));
            imageLoader.displayImage(url, avatarImageView, ImageLoadOptions.getAppPicOptions());
        }
    }

    Thread thread=new Thread(new Runnable() {
        String path=null;
        @Override
        public void run() {
            isRun = true;
            flag = true;
            while(isRun){
                if(flag){
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).get(accountEditText.getText().toString())!=null){
                            path=list.get(i).get(accountEditText.getText().toString());
                        }
                        System.out.println(path+"aaaa"+list.get(i));
                    }
                    Message msg=new Message();
                    msg.what=111;
                    msg.obj=path;
                    handler.sendMessage(msg);
                }
            }
        }
    });




    /**
     * 登录
     * @param username
     * @param pwd
     * @param listener
     *   REQUEST_LOGIN_ERROR_ACCOUNT(账号不存在)
     *   REQUEST_LOGIN_ERROR_POSSWORD(密码不正确)
     *   REQUEST_SUCCESS(登录成功)
     *   REQUEST_FAILED(登录失败)
     *
     */
//    public void login(final Context context,final String username, final  String pwd,final String clientId, final OnAppRequestListener listener){
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put(AppConstants.PARAM_ACCOUNT, username);
//        params.put(AppConstants.PARAM_PASSWORD, AppUtils.getShaMD5(pwd));
//        params.put(AppConstants.PARAM_CLIENTID,clientId);
//        params.put(AppConstants.PARAM_APPVER, AppUtils.getVersionName());
//        params.put(AppConstants.PARAM_SYSVER, "1");//系统平台android:1，ios:2
//
//        String timestamp = ReadUrl.urlkey;
//        params.put(AppConstants.PARAM_TIMESTAMP,  timestamp);
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(username).append(AppUtils.getShaMD5(pwd)).append(timestamp);
//
//        params.put(AppConstants.PARAM_KEY, AppUtils.Md5(stringBuilder.toString()));
//        String url = AppContext.getInstance().getMainGateWay()+"/main/login";
//
//        sendVolleyRequestString(params,url, new OnSendRequestListener() {
//            @Override
//            public void onSendRequest(int code, String message, String result) {
//                Object obj;
//                if(code == REQUEST_SUCCESS){
//                    UtilsLog.i(TAG, "login interface is success");
//                    Gson gson = new Gson();
//                    obj = gson.fromJson(result, AccountInfo.class);
//                    mAccountInfo = (AccountInfo)obj;
//                    UtilsLog.i(TAG, "login interface is success,save obj to accountifo ok");
//
//
//                    // mAccountInfo.setAccount(username);
//                    mAccountInfo.setPassword(pwd);
//                    AppContext.getInstance().setAccountInfo(mAccountInfo);
//                    UtilsLog.i(TAG, "start to save accountinfo to sharedPreferences ,and uid is: " + mAccountInfo.getUid()+"");
//                    SharedPreferencesHelper.getInstance(context).setInt(AppConstants.PARAM_UID, mAccountInfo.getUid());
//                    SharedPreferencesHelper.getInstance(context).setInt(AppConstants., mAccountInfo.getRole());
//                    SharedPreferencesHelper.getInstance(context).setInt(AppConfig.KID, mAccountInfo.getKid());
//                    SharedPreferencesHelper.getInstance(context).setInt(AppConfig.NUM, mAccountInfo.getNum());
//                    SharedPreferencesHelper.getInstance(context).setInt(AppConstants.PARAM_CID, mAccountInfo.getCid());
//                    SharedPreferencesHelper.getInstance(context).setInt(AppConstants.PREF_ISLOGIN, 1);
//                    //SharedPreferencesHelper.getInstance(context).setBoolean(AppConstants.FLAG_FIRST_LOGINSUCCESS, false);
//                    DbHelper.updateAccountInfo(mAccountBean);
//                    UtilsLog.i(TAG, "login interface is success,uodateaccountinfo ok");
//                    AppContext.getInstance().setAccountInfo(mAccountInfo);
//                    if(accountlist!=null&&accountlist.size()!=0){
//                        for (int i=0;i<accountlist.size();i++){
//                            RelationShipBean bean = new RelationShipBean();
//                            bean.setDefaultrelation(mAccountInfo.getDefaultrelation());
//                            bean.setRelationship(accountlist.get(i).getRelationship());
//                            bean.setHxregtag(accountlist.get(i).getHxregtag());
//                            list.add(bean);
//                        }
//                        try {
//                            DbHelper.getDB(AppContext.getInstance()).deleteAll(RelationShipBean.class);
//                            UtilsLog.i(TAG, "login interface is success,deleteAll RelationShipBean ok");
//                            DbHelper.getDB(AppContext.getInstance()).saveAll(list);
//                            UtilsLog.i(TAG, "login interface is success,saveAll RelationShipBean ok");
//                        } catch (DbException e) {
//                            UtilsLog.i(TAG, "login interface is success,deleteAll or saveAll RelationShipBean DbException");
//                            e.printStackTrace();
//                        }
//                    }else{
//                        try {
//                            UtilsLog.i(TAG, "mAccountInfo.getRelationships() is null value,start to deleteAll RelationShipBean");
//                            DbHelper.getDB(AppContext.getInstance()).deleteAll(RelationShipBean.class);
//                        } catch (DbException e) {
//                            UtilsLog.i(TAG, "deleteAll RelationShipBean fail DbException");
//                            e.printStackTrace();
//                        }
//                    }
//                    if((accountlist==null||accountlist.size()==0)&&mAccountInfo.getRole()!=2){
//                        RelationShipBean bean = new RelationShipBean();
//                        bean.setDefaultrelation(mAccountInfo.getDefaultrelation());
//                        bean.setRelationship(0);
//                        bean.setHxregtag(0);
//                        list.add(bean);
//                        try {
//                            DbHelper.getDB(AppContext.getInstance()).deleteAll(RelationShipBean.class);
//                            UtilsLog.i(TAG, "role == 2 ,deleteAll RelationShipBean ok");
//                            DbHelper.getDB(AppContext.getInstance()).saveAll(list);
//                            UtilsLog.i(TAG, "role == 2 ,saveAll RelationShipBean ok");
//                        } catch (DbException e) {
//                            e.printStackTrace();
//                            UtilsLog.i(TAG, "deleteAll or saveall RelationShipBean fail DbException");
//                        }
//                    }
//
//                    //记住登录历史记录
//                    String accouts=username.trim()+"||"+(pwd+"zgyey")+"||"+mAccountInfo.getAvatar();
//                    SharedPreferences settings=context.getSharedPreferences(SharedPreferencesHelper.PREF_LOGIN_FILE,  Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor=settings.edit();
//                    editor.putString(username.trim(), accouts);
//                    editor.commit();
//                    UtilsLog.i(TAG, "saveAll login status sharedpreferences ok");
//                    //记住最后一次登录的配置文件
//                    SharedPreferences set=context.getSharedPreferences(AppConfig.LOGIN_DEFALUTE_VALUE, Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editors=set.edit();
//                    editors.putString(AppConfig.LOGIN_DEFAULTE_ACCOUNT, username.trim());
//                    editors.putString(AppConfig.LOGIN_DEFAULTE_PASSWORD, pwd+"zgyey");
//                    editors.putString(AppConfig.LOGIN_DEFAULTE_AVATER, mAccountInfo.getAvatar());
//                    editors.commit();
//
//                } else {
//                    obj = result;
//                }
//                setmAccountInfo(mAccountInfo);
//                SharedPreferencesHelper.getInstance(context).setInt(AppConstants.PARAM_UID, mAccountInfo.getUid());
//                if (listener != null) {
//                    UtilsLog.i(TAG, "listener is not null,start to onAppRequest code: " + code + "");
//                    listener.onAppRequest(code, message, obj);
//                } else {
//                    UtilsLog.i(TAG, "sorry, listener is null");
//                }
//            }
//        });
//
//    }
}
