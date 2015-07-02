package com.yey.read.common;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.yey.read.util.DialogTips;

import java.io.File;
import java.io.FileOutputStream;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.
 *
 * @author way
 *
 */
public class CrashHandler {
    private Thread.UncaughtExceptionHandler mDefaultHandler;// 系统默认的UncaughtException处理类
    private static CrashHandler INSTANCE;// CrashHandler实例
    private Context mContext;// 程序的Context对象

    /** 保证只有一个CrashHandler实例 */
    private CrashHandler() {

    }

    /** 获取CrashHandler实例 ,单例模式 */
    public static CrashHandler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CrashHandler();
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
//    public void init(Context context) {
//        mContext = context;
//
//        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();// 获取系统默认的UncaughtException处理器
//        Thread.setDefaultUncaughtExceptionHandler(this);// 设置该CrashHandler为程序的默认处理器
//    }

    /**
     * 当UncaughtException发生时会转入该重写的方法来处理
     */
//    @Override
//    public void uncaughtException(Thread thread, Throwable ex) {
//        System.out.println(ex.getMessage()+"----xxxx");
//        if (!handleException(thread,ex) && mDefaultHandler != null) {
//            // 如果自定义的没有处理则让系统默认的异常处理器来处理
//            mDefaultHandler.uncaughtException(thread, ex);
//        }else{
//            Intent intent = new Intent(mContext, MainActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
//                    Intent.FLAG_ACTIVITY_NEW_TASK);
//            mContext.startActivity(intent);
////	        android.os.Process.killProcess(android.os.Process.myPid());
//        }
//    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     *            异常信息
     * @return true 如果处理了该异常信息;否则返回false.
     */
//    public boolean handleException(Thread thread,final Throwable ex) {
//        if (ex == null || mContext == null)
//            return false;
//        String cause = null;
//        StringBuffer buffer = new StringBuffer();
//        if(ex.getStackTrace()!=null&ex.getStackTrace().length!=0){
//            buffer.append(ex.getCause().getCause().toString()).append("\n").append(ex.getMessage().toString()+"\n").append(ex.getLocalizedMessage()+"\n");
//            for(int i=0;i<ex.getStackTrace().length;i++){
//                buffer.append(ex.getStackTrace()[i]+"\n");
//            }
//
//            save2File(buffer.toString());
//        }
//        showToast(mContext,"很抱歉，程序发生异常");
//        try {
//            thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ExitAppUtils.getInstance().exit();
//        return true;
//    }

//    private File save2File(String crashReport) {
//        // TODO Auto-generated method stub
//        String fileName = "crash-" + System.currentTimeMillis() + ".txt";
//        if (Environment.getExternalStorageState().equals(
//                Environment.MEDIA_MOUNTED)) {
//            try {
//                File dir = new File(Environment.getExternalStorageDirectory()
//                        .getAbsolutePath() + File.separator + "crash");
//                if (!dir.exists())
//                    dir.mkdir();
//                File file = new File(dir, fileName);
//                FileOutputStream fos = new FileOutputStream(file);
//                fos.write(crashReport.toString().getBytes());
//                fos.close();
//                return file;
//            } catch (Exception e) {
//                Log.i("save2File error:" + e.getMessage());
//            }
//        }
//        return null;
//    }

//    private void sendAppCrashReport(final Context context,
//                                    final String crashReport) {
//        DialogTips dialog = new DialogTips(mContext,"抱歉，时光树运行时发生错误", "告诉我们","不告诉","程序发生错误",true);
//        // 设置成功事件
//        dialog.SetOnSuccessListener(new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//                Intent intent = new Intent(mContext,MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(1);
//            }
//        });
//        dialog.SetOnCancelListener(new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface arg0, int arg1) {
//                Intent intent = new Intent(mContext,MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
//                android.os.Process.killProcess(android.os.Process.myPid());
//                System.exit(1);
//            }
//        });
//        dialog.getWindow()
//                .setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        dialog.show();
//        dialog = null;
//    }

    private void showToast(final Context context, final String msg){
        new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
    }


    /**
     * 获取APP崩溃异常报告
     *
     * @param ex
     * @return
     */
//    private String getCrashReport(Context context, Throwable ex) {
//        PackageInfo pinfo = getPackageInfo(context);
//        StringBuffer exceptionStr = new StringBuffer();
//        exceptionStr.append("Version: " + pinfo.versionName + "("
//                + pinfo.versionCode + ")\n");
//        exceptionStr.append("Android: " + android.os.Build.VERSION.RELEASE
//                + "(" + android.os.Build.MODEL + ")\n");
//        exceptionStr.append("Exception: " + ex.getMessage() + "\n");
//        StackTraceElement[] elements = ex.getStackTrace();
//        for (int i = 0; i < elements.length; i++) {
//            exceptionStr.append(elements[i].toString() + "\n");
//        }
//        return exceptionStr.toString();
//    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
//    private PackageInfo getPackageInfo(Context context) {
//        PackageInfo info = null;
//        try {
//            info = context.getPackageManager().getPackageInfo(
//                    context.getPackageName(), 0);
//        } catch (PackageManager.NameNotFoundException e) {
//            // e.printStackTrace(System.err);
//            Log.i("getPackageInfo err = " + e.getMessage());
//        }
//        if (info == null)
//            info = new PackageInfo();
//        return info;
//    }

}
