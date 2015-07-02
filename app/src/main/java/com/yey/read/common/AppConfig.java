package com.yey.read.common;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by sunnie on 15/6/2.
 */
public class AppConfig {
    public static final String CAHE_DIR = "/Kindergarten/cache/";
    public static final String APP_CACAHE_WEBVIEW = "/webcache";
    private final static String APP_CONFIG = "config";
    public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";
    /**下载二维码存放的目录*/
    public final static String PATH= Environment
            .getExternalStorageDirectory() + "/yey/kindergaten/barcode/";
    /**记录登陆最后一次的配置文件*/
    public final static String LOGIN_DEFALUTE_VALUE="login_defaul_pref";
    /**登录名默认显示*/
    public final static String LOGIN_DEFAULTE_ACCOUNT="login_defaul_account";
    /**登陆密码默认显示*/
    public final static String LOGIN_DEFAULTE_PASSWORD="login_defaul_password";
    /**登陆头像url默认显示*/
    public final static String LOGIN_DEFAULTE_AVATER="login_defaul_avatar";
    /**首页进入记日程跳转标示符*/
    public final static String SWITCH_TYPE_HOME="hometype";
    /**服务进入记日程跳转标示符*/
    public final static String SWITCH_TYPE_SERVICE="servicetype";
    /**通讯录进入记日程跳转标示符*/
    public final static String SWITCH_TYPE_CONTACTS="contactstype";
    /**个人资料进入到记日程跳转标示符*/
    public final static String SWITCH_TYPE_ME="metype";
    //app中的key
    public static final String USERID = "userid";
    public static final String ROLE = "role";
    public static final String KID = "kid";
    public  static final String NUM = "num";
    public static final String UPLOAD_PATH = Environment
            .getExternalStorageDirectory() + "/yey/kindergaten/uploadphoto/";

    private static AppConfig appConfig;
    private Context mContext;



    public static AppConfig getAppConfig(Context context)
    {
        if(appConfig == null){
            appConfig = new AppConfig();
            appConfig.mContext = context;
        }
        return appConfig;
    }

    private void setProps(Properties p) {
        FileOutputStream fos = null;
        try{
            //把config建在files目录下
            //fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);

            //把config建在(自定义)app_config的目录下
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            File conf = new File(dirConf, APP_CONFIG);
            fos = new FileOutputStream(conf);

            p.store(fos, null);
            fos.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                fos.close();
            } catch (Exception e) {}
        }
    }

    public void set(Properties ps)
    {
        Properties props = get();
        props.putAll(ps);
        setProps(props);
    }

    public void set(String key,String value)
    {
        Properties props = get();
        props.setProperty(key, value);
        setProps(props);
    }

    public void remove(String...key)
    {
        Properties props = get();
        for(String k : key)
            props.remove(k);
        setProps(props);
    }

    public String get(String key)
    {
        Properties props = get();
        return (props!=null)?props.getProperty(key):null;
    }

    public Properties get() {
        FileInputStream fis = null;
        Properties props = new Properties();
        try{
            //读取files目录下的config
            //fis = activity.openFileInput(APP_CONFIG);

            //读取app_config目录下的config
            File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
            fis = new FileInputStream(dirConf.getPath() + File.separator + APP_CONFIG);

            props.load(fis);
        }catch(Exception e){
        }finally{
            try {
                fis.close();
            } catch (Exception e) {}
        }
        return props;
    }
}
