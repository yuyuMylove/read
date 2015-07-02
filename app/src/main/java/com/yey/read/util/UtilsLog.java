package com.yey.read.util;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Log统一管理类
 * @author way
 * 
 */
public class UtilsLog {

    /** 是否需要打印日志 */
    public static boolean isDebug = true;
    /** 是否需要将日志保存到文件 */
    public static boolean isSaveFile = true;
    /** 本类的TAG */
    private static final String TAG = "timetree";
    /** log日志保存地址 */
    /*public static final String DEFAULT_LOG_FILE_DIR = "/data/log/yey/";*/
    /** 总路径: mnt/sdcard/yey/log/timetree */
    public static final String DEFAULT_LOG_FILE_DIR = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + "yey/log/";
    /** log日志保存的文件名 */
    private static final String LOG_FILE_NAME = "timetree";
    /** 文件644权限 */
    public static final String PERMISSION_LOG_FILE = "644";
    /** 文件755权限 */
    private static final String PERMISSION_LOG_DIR = "755";
    /** 日志打印级别 */
    private static final char ERROR_LEVEL = 'e';
    private static final char WARN_LEVEL = 'w';
    private static final char INFO_LEVEL = 'i';
    private static final char DEBUG_LEVEL = 'd';
    private static final char VERBOSE_LEVEL = 'v';

    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private static BufferedWriter mBufferWriter = null;
    private static File mFileDirectory = null;
    private static File mLogFile = null;
    private static FileWriter mFileWriter = null;
    private static String mLogMessage = null;
    private static boolean needChmod = false;

    public static void e(String tag, String text) {
        if (isDebug){
            log(tag, text, ERROR_LEVEL);
        }
    }

    public static void w(String tag, String text) {
        if (isDebug){
            log(tag, text, ERROR_LEVEL);
        }
    }

    public static void d(String tag, String text) {
        if (isDebug){
            log(tag, text, ERROR_LEVEL);
        }
    }

    public static void i(String tag, String text) {
        if (isDebug){
            log(tag, text, ERROR_LEVEL);
        }
    }

    public static void v(String tag, String text) {
        if (isDebug){
            log(tag, text, ERROR_LEVEL);
        }
    }
    /**
     * 输出logs的级别
     * @param tag
     * @param msg
     * @param level
     * @return void
     */
    private static void log(String tag, String msg, char level) {

        switch(level) {
            case ERROR_LEVEL:
                android.util.Log.e(tag, msg);
                break;
            case WARN_LEVEL:
                android.util.Log.w(tag, msg);
                break;
            case DEBUG_LEVEL:
                android.util.Log.d(tag, msg);
                break;
            case INFO_LEVEL:
                android.util.Log.i(tag, msg);
                break;
            case VERBOSE_LEVEL:
                android.util.Log.v(tag, msg);
                break;
            default:
                android.util.Log.v(tag, msg);
                break;
        }

        if (isSaveFile) {
            writeLogtoFile(String.valueOf(level), tag, msg);
        }
    }

    /**
     * 设置log打打印时间
     */
    private static synchronized String getLogTime() {
        if (null == TIME_FORMAT) {
            android.util.Log.e(TAG, "TIME_FORMAT is null");
            return null;
        }
        return TIME_FORMAT.format(new Date());
    }

    /**
     * 将log日志保存到到指定文件.
     * @return
     * **/
    private static void writeLogtoFile(String mylogtype, String tag, String text) {
        if (!checkFilePath()) {
            android.util.Log.e(TAG, "Failed to make direction");
            return;
        }

        mLogMessage = getLogTime() + ": " + mylogtype + ": " + tag + ": " + text;
        mLogFile = new File(mFileDirectory, LOG_FILE_NAME);
        if (mLogFile.exists()) {
            needChmod = false;
        } else {
            needChmod = true;
        }

        try {
            mFileWriter = new FileWriter(mLogFile, true);
            mBufferWriter = new BufferedWriter(mFileWriter);
            mBufferWriter.write(mLogMessage);
            mBufferWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if (null != mBufferWriter) {
                    mBufferWriter.close();
                }
                if (null != mFileWriter) {
                    mFileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (needChmod) {
            String logFilePath = DEFAULT_LOG_FILE_DIR + "/" + LOG_FILE_NAME;
            android.util.Log.d(TAG, "logFilePath:" + logFilePath);
            chmod(PERMISSION_LOG_FILE, logFilePath);
        }
    }

    /**修改文件权限*/
    public static void chmod(String permission, String excuteFilePath) {
        try {
            String command = "chmod " + permission + " " + excuteFilePath;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (IOException e) {
            android.util.Log.e(TAG, "chmod permission error");
        }
    }

    /**
     * 检查文件是否存在，检查文件是否已破坏
     */
    private static boolean checkFilePath() {
        mFileDirectory = new File(DEFAULT_LOG_FILE_DIR);
        if (null == mFileDirectory) {
            android.util.Log.e(TAG, "file direction is null");
            return false;
        }

        try {
            if (!mFileDirectory.exists() || !mFileDirectory.isDirectory()) {
                boolean success = mFileDirectory.mkdirs();
                if (success) {
                    chmod(PERMISSION_LOG_DIR, DEFAULT_LOG_FILE_DIR);
                } else {
                    android.util.Log.e(TAG, "Failed to make direction");
                    return false;
                }
            }
        } catch(SecurityException e) {
            android.util.Log.e(TAG, "SecurityException");
            e.printStackTrace();
            return false;
        } catch(Exception e) {
            android.util.Log.e(TAG, "Exception");
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
