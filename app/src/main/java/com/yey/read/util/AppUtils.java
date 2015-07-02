package com.yey.read.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.yey.read.R;
import com.yey.read.common.AppConfig;
import com.yey.read.common.AppContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: feezoner
 * Date: 13-11-23
 * Time: 下午1:23
 * To change this template use File | Settings | File Templates.
 */
public class AppUtils {
	
	public static String Md5(String str) {
		if (str != null && !str.equals("")) {
			try {
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
				byte[] md5Byte = md5.digest(str.getBytes("UTF8"));
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < md5Byte.length; i++) {
					sb.append(HEX[(int) (md5Byte[i] & 0xff) / 16]);
					sb.append(HEX[(int) (md5Byte[i] & 0xff) % 16]);
				}
				str = sb.toString();
			} catch (NoSuchAlgorithmException e) {
			} catch (Exception e) {
			}
		}
		return str;
	}
	
	public static String getShaMD5(String str) {
        StringBuffer sb = new StringBuffer();
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] bts = md.digest();
            int len = bts.length;
            String tmp;
            for (int i = 0; i < len; i++) {
                tmp = (Integer.toHexString(bts[i] & 0xFF));
                if (tmp.length() == 1) {
                    sb.append("0");
                }
                sb.append(tmp);
            }
        } catch (NoSuchAlgorithmException e) {
            return sb.toString();
        }
        return sb.toString();
    }




    /**
     * 缩放图片
     * @param path
     * @param width
     * @param height
     * @return
     */
    public static Bitmap scaleBitmap(String path, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;//不加载bitmap到内存中

        BitmapFactory.decodeFile(path, options);
        int outWidth = options.outWidth;
        int outHeight = options.outHeight;
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inSampleSize = 1;

        if (outWidth != 0 && outHeight != 0 && width != 0 && height != 0
                && (outWidth > width || outHeight > height))
        {
            int s1 = outWidth/width;
            int s2 = outHeight/height;
            int sampleSize= s1 > s2 ? s1 : s2;

             options.inSampleSize = sampleSize;
        }

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
//        Bitmap b = BitmapFactory.decodeFile(path);
//        int sw = b.getWidth();
//        int sh = b.getHeight();
//        float dw = (float)sw/(float)width;
//        float dh = (float)sh/(float)height;
//        Matrix m = new Matrix();
//        m.setScale(dw, dh);
//        Bitmap newb = Bitmap.createBitmap(b, 0, 0, sw, sh, m, false);
//        b.recycle();
//        return newb;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, float width, float height) {

        int w = bitmap.getWidth();

        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();

        float scaleWidth = ((float) width / w);

        float scaleHeight = ((float) height / h);
        float scale = scaleWidth < scaleHeight ? scaleWidth : scaleHeight;
        matrix.postScale(scale, scale);// 利用矩阵进行缩放不会造成内存溢出

        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);

        return newbmp;

    }

    /**
     * 获取圆角图片
     * @param bitmap
     * @return
     */
    public static Bitmap roundBitmap(Bitmap bitmap) {
          return roundBitmap(bitmap, 12);
    }

    public static Bitmap roundBitmap(Bitmap bitmap, int radius){
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = radius;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }


    public static void saveBitmap(Bitmap bmp ,String filePath){
        FileOutputStream fileOutPutStream = null;
        try {
            fileOutPutStream = new FileOutputStream(filePath);//写入的文件路径
        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, fileOutPutStream);
        try {
            fileOutPutStream.flush();
            fileOutPutStream.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getSdcardDir(){
        File sdDir = null;
        String sdpath = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); //判断sd卡是否存在
        if (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
            sdpath = sdDir.toString();
        }
        return sdpath;
    }
    public static String getCacheDir(){
        String dir =  getSdcardDir()+ AppConfig.CAHE_DIR;
        File f = new File(dir);
        if(!f.exists()){
            f.mkdirs();
        }
        return dir;
    }

    public static int getVersion(Context context){
        try {
            PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    public static String getVersionName(Context context){
        try {
            PackageInfo pi=context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }
    }
    public static void hidKeyboard(Context context,View view){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

   public static void setImageFitBitmap(ImageView iv, Bitmap bmp){
       iv.setImageBitmap(bmp);
       ViewGroup.LayoutParams params = (ViewGroup.LayoutParams)iv.getLayoutParams();
       params.height = (int)((float)bmp.getHeight()*((float)iv.getWidth()/(float)bmp.getWidth()));
       iv.setLayoutParams(params);
   }

    /**
     * 读取图片旋转角度
     * @param path
     * @return
     */
   public static int readPictureDegree(String path){
       int degree = 0;

       try {
           ExifInterface exifInterface = new ExifInterface(path);
           int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
           switch(orientation){
               case ExifInterface.ORIENTATION_ROTATE_90:
                   degree = 90;
                   break;
               case ExifInterface.ORIENTATION_ROTATE_180:
                   degree = 180;
                   break;
               case ExifInterface.ORIENTATION_ROTATE_270:
                   degree = 270;
                   break;
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       return degree;
   }

    /**
     * 旋转图片
     * @param angle
     * @param bitmap
     * @return
     */
    public static Bitmap rotaingBitmap(int angle, Bitmap bitmap){
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * scrollview与listview之后使用解决冲突,android:fillViewport="true"可使listview全屏
     * setListViewHeightBasedOnChildren
     * (这里描述这个方法适用条件 – 可选)
     * @param lv
     *void
     * @exception
     * @since  1.0.0
     */
    public static  void setListViewHeightBasedOnChildren(ListView lv) {

		ListAdapter listAdapter = lv.getAdapter();
        if (listAdapter == null) {

            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, lv);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = totalHeight + (lv.getDividerHeight() * (listAdapter.getCount() - 1));
        lv.setLayoutParams(params);
    }

    /**
     * 用于显示图片文本的方法
     *void
     * @exception
     * @since  1.0.0
     */
    public static int getResourceID(String name){
		Field field;
		try {
			field = R.drawable.class.getField(name);
			return Integer.parseInt(field.get(null).toString());
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return 0;

	}
    public static CharSequence getCharSequenceText(String text) {
    	CharSequence charSequence= Html.fromHtml(text, new ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                Drawable drawable = AppContext.getInstance().getResources().getDrawable(getResourceID(source));
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight() / 2);
                return drawable;
            }
        }, null);
    	 return charSequence;
     }

    /** 检查是否有网络 */
	public static boolean isNetworkAvailable(Context context) {
		NetworkInfo info = getNetworkInfo(context);
		if (info != null) {
			return info.isAvailable();
		}
		return false;
	}

	private static NetworkInfo getNetworkInfo(Context context) {

		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo();
	}


	/** 检查SD卡是否存在 */
	public static boolean checkSdCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED))
			return true;
		else
			return false;
	}
	

	
    public static String md5(String plain){
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plain.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;

    }


    /**
     * 驗證手機號碼
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles){
        boolean flag = false;
        try{
            Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }


    public static String getVersionName()
    {
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            // 获取packagemanager的实例
            PackageManager packageManager =AppContext.getInstance().getPackageManager();
            packInfo = packageManager.getPackageInfo(AppContext.getInstance().getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }

    /**
     * 设置背景
     * @param view
     * @param imagebakground
     */
    public static void  setBackground(Context context,ImageView view,int imagebakground){
            view.setImageResource(imagebakground);
    }


    // 获得可用的内存
    public static long getmem_UNUSED(Context mContext) {
        long MEM_UNUSED;
        ActivityManager am = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);

        MEM_UNUSED = mi.availMem / 1024;
        return MEM_UNUSED;
    }

    // 获得总内存
    public static long getmem_TOLAL() {
        long mTotal;
        // /proc/meminfo读出的内核信息进行解释
        String path = "/proc/meminfo";
        String content = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path), 8);
            String line;
            if ((line = br.readLine()) != null) {
                content = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // beginIndex
        int begin = content.indexOf(':');
        // endIndex
        int end = content.indexOf('k');
        // 截取字符串信息

        content = content.substring(begin + 1, end).trim();
        mTotal = Integer.parseInt(content);
        return mTotal;
    }

    /**
     * 判断activity是否在运行
     * @param mContext
     * @param activityClassName
     * @return
     */
    public static boolean isActivityRunning(Context mContext,String activityClassName){
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = activityManager.getRunningTasks(1);
        if(info != null && info.size() > 0){
            ComponentName component = info.get(0).topActivity;
            if(activityClassName.equals(component.getClassName())){
                return true;
            }
        }
        return false;
    }
}
