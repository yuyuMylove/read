package com.yey.read.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationManager;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yey.read.db.DbHelper;
import com.yey.read.util.FileUtils;
import com.yey.read.util.SharedPreferencesHelper;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局应用程序类：用于保存和调用全局应用配置及访问网络数据
 * @version 1.0
 * Created by sunnie on 15/5/23.
 */
public class AppContext extends Application {

    public boolean isRefresh;

    private static AppContext appContext;
    private NotificationManager mNotificationManager;
    private static AccountInfo accountInfo;

    public static List<Activity> activitys = new ArrayList<Activity>();

    public void addActivity(Activity activity) {
        //activitys.clear();
        activitys.add(activity);
    }

    public void  finishActivitys () {
        if (activitys!=null && activitys.size()!=0){
            for (int i=0;i<activitys.size();i++){
                activitys.get(i).finish();
            }
            activitys.clear();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        updateDb();
        ActivityManager activityManager = (ActivityManager) this.getSystemService(this.ACTIVITY_SERVICE);          // 内存最大值（单位：KB）
        setMinHeapSize((activityManager.getMemoryClass() * 1024 * 1024 * 30));
        InitImageLoad();
        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
    }

    private void updateDb() {
        DbHelper.getDB(appContext);
    }

    public static void setMinHeapSize(long size) {
        try {
            Class<?> cls = Class.forName("dalvik.system.VMRuntime");
            Method getRuntime = cls.getMethod("getRuntime");
            Object obj = getRuntime.invoke(null);// obj就是Runtime
            if (obj == null) {
                System.err.println("obj is null");
            } else {
                System.out.println(obj.getClass().getName());
                Class<?> runtimeClass = obj.getClass();
                Method setMinimumHeapSize = runtimeClass.getMethod(
                        "setMinimumHeapSize", long.class);

                setMinimumHeapSize.invoke(obj, size);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void InitImageLoad() {
        if (ImageLoader.getInstance().isInited()) {
            ImageLoader.getInstance().clearMemoryCache();
            ImageLoader.getInstance().destroy();
        }
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), FileUtils.getSDRoot() + "yey/imageloader/Cache");
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(appContext)
                .diskCacheExtraOptions(480, 800, null)
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new WeakMemoryCache())
//                .memoryCache(new FIFOLimitedMemoryCache(ValuesOf(AppUtils.getmem_TOLAL())-1024*4)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .discCacheFileNameGenerator(new Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .discCacheFileCount(100) //缓存的文件数量
                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(appContext, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs() // Remove for release app
                .build();//开始构建
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

    }



    public synchronized static AppContext getInstance() {
        return appContext;
    }

    public boolean isRefresh() {
        return isRefresh;
    }

    public void setRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public AccountInfo getAccountInfo() {//问题
        if (this.accountInfo == null || accountInfo.getUid() == 0) {
            this.accountInfo = new AccountInfo();
            this.accountInfo.setUid(SharedPreferencesHelper.getInstance(appContext).getInt(AppConstants.PARAM_UID, 0));
//            this.accountInfo.setRole(SharedPreferencesHelper.getInstance(appContext).getInt(AppConfig.ROLE, 0));
//            this.accountInfo.setKid(SharedPreferencesHelper.getInstance(appContext).getInt(AppConfig.KID, 0));
//            this.accountInfo.setNum(SharedPreferencesHelper.getInstance(appContext).getInt(AppConfig.NUM, 0));
//            this.accountInfo.setCid(SharedPreferencesHelper.getInstance(appContext).getInt(AppConstants.PARAM_CID, 0));
            this.accountInfo.setNickname(SharedPreferencesHelper.getInstance(appContext).getString(AppConstants.PREF_NICKNAME, ""));
        }
        return this.accountInfo;
    }

    public NotificationManager getNotificationManager() {
        if (mNotificationManager == null){
            mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        }

        return mNotificationManager;
    }


}
