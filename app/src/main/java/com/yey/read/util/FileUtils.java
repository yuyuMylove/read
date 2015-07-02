package com.yey.read.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具包
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class FileUtils {
	/**
	 * 把图片压缩到200K
	 * 
	 * @param oldpath
	 *            压缩前的图片路径
	 * @param newPath
	 *            压缩后的图片路径
	 * @return
	 */
	/**
	 * 把图片压缩到200K
	 * 
	 * @param oldpath
	 *            压缩前的图片路径
	 * @param newPath
	 *            压缩后的图片路径
	 * @return
	 */
	public static File compressFile(String oldpath, String newPath) {
		Bitmap compressBitmap = FileUtils.decodeFile(oldpath);
		Bitmap newBitmap = ratingImage(oldpath, compressBitmap);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		newBitmap.compress(CompressFormat.PNG, 100, os);
		byte[] bytes = os.toByteArray();
		
		File file = null ;
		try {
			file = FileUtils.getFileFromBytes(bytes, newPath);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(newBitmap != null ){
				if(!newBitmap.isRecycled()){
					newBitmap.recycle();
				}
				newBitmap  = null;
			}
			if(compressBitmap != null ){
				if(!compressBitmap.isRecycled()){
					compressBitmap.recycle();
				}
				compressBitmap  = null;
			}
		}
		return file;
	}
	
	private static Bitmap ratingImage(String filePath,Bitmap bitmap){
		int degree = readPictureDegree(filePath);
		return rotaingImageView(degree, bitmap);
	}
	/**
	 *  旋转图片
	 * @param angle
	 * @param bitmap
	 * @return Bitmap
	 */
	public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {
        //旋转图片 动作
		Matrix matrix = new Matrix();;
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return resizedBitmap;
	}
	/**
	 * 读取图片属性：旋转的角度
	 * @param path 图片绝对路径
	 * @return degree旋转的角度
	 */
    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
                ExifInterface exifInterface = new ExifInterface(path);
                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                switch (orientation) {
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
	 * 图片压缩
	 * 
	 * @param fPath
	 * @return
	 */
	public static Bitmap decodeFile(String fPath) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		opts.inDither = false; // Disable Dithering mode
		opts.inPurgeable = true; // Tell to gc that whether it needs free
		opts.inInputShareable = true; // Which kind of reference will be used to
		BitmapFactory.decodeFile(fPath, opts);
		final int REQUIRED_SIZE = 200;
		int scale = 1;
		if (opts.outHeight > REQUIRED_SIZE || opts.outWidth > REQUIRED_SIZE) {
			final int heightRatio = Math.round((float) opts.outHeight
					/ (float) REQUIRED_SIZE);
			final int widthRatio = Math.round((float) opts.outWidth
					/ (float) REQUIRED_SIZE);
			scale = heightRatio < widthRatio ? heightRatio : widthRatio;//
		}
		Log.i("scale", "scal =" + scale);
		opts.inJustDecodeBounds = false;
		opts.inSampleSize = scale;
		Bitmap bm = BitmapFactory.decodeFile(fPath, opts).copy(Config.ARGB_8888, false);
		return bm;
	}
	
	/**
	 * 把字节数组保存为一个文件
	 * 
	 * @param b
	 * @param outputFile
	 * @return
	 */
	public static File getFileFromBytes(byte[] b, String outputFile) {
		File ret = null;
		BufferedOutputStream stream = null;
		try {
			ret = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(ret);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			// log.error("helper:get file from byte process error!");
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// log.error("helper:get file from byte process error!");
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	
	/**
	 * 写文本文件 在Android系统中，文件保存在 /data/data/PACKAGE_NAME/files 目录下
	 * 
	 * @param context
	 * @param
	 */
	public static void write(Context context, String fileName, String content) {
		if (content == null)
			content = "";

		try {
			FileOutputStream fos = context.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			fos.write(content.getBytes());

			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取文本文件
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String read(Context context, String fileName) {
		try {
			FileInputStream in = context.openFileInput(fileName);
			return readInStream(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String readInStream(FileInputStream inStream) {
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[512];
			int length = -1;
			while ((length = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, length);
			}

			outStream.close();
			inStream.close();
			return outStream.toString();
		} catch (IOException e) {
			Log.i("FileTest", e.getMessage());
		}
		return null;
	}

	public static File createFile(String folderPath, String fileName) {
		File destDir = new File(folderPath);
		if (!destDir.exists()) {
			destDir.mkdirs();
		}
		return new File(folderPath, fileName + fileName);
	}

	/**
	 * 向手机写图片
	 * 
	 * @param buffer
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static boolean writeFile(byte[] buffer, String folder,
			String fileName) {
		boolean writeSucc = false;

		boolean sdCardExist = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);

		String folderPath = "";
		if (sdCardExist) {
			folderPath = Environment.getExternalStorageDirectory()
					+ File.separator + folder + File.separator;
		} else {
			writeSucc = false;
		}

		File fileDir = new File(folderPath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		File file = new File(folderPath + fileName);
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write(buffer);
			writeSucc = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return writeSucc;
	}

	/**
	 * 根据文件绝对路径获取文件名
	 *
	 * @param filePath
	 * @return
	 */
//	public static String getFileName(String filePath) {
//		if (StringUtils.isEmpty(filePath))
//			return "";
//		return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
//	}

	/**
	 * 根据文件的绝对路径获取文件名但不包含扩展名
	 *
	 * @param filePath
	 * @return
	 */
//	public static String getFileNameNoFormat(String filePath) {
//		if (StringUtils.isEmpty(filePath)) {
//			return "";
//		}
//		int point = filePath.lastIndexOf('.');
//		return filePath.substring(filePath.lastIndexOf(File.separator) + 1,
//				point);
//	}

	/**
	 * 获取文件扩展名
	 *
	 * @param fileName
	 * @return
	 */
//	public static String getFileFormat(String fileName) {
//		if (StringUtils.isEmpty(fileName))
//			return "";
//
//		int point = fileName.lastIndexOf('.');
//		return fileName.substring(point + 1);
//	}

	/**
	 * 获取文件大小
	 *
	 * @param filePath
	 * @return
	 */
	public static long getFileSize(String filePath) {
		long size = 0;

		File file = new File(filePath);
		if (file != null && file.exists()) {
			size = file.length();
		}
		return size;
	}

	/**
	 * 获取文件大小
	 *
	 * @param size
	 *            字节
	 * @return
	 */
	public static String getFileSize(long size) {
		if (size <= 0)
			return "0";
		java.text.DecimalFormat df = new java.text.DecimalFormat("##.##");
		float temp = (float) size / 1024;
		if (temp >= 1024) {
			return df.format(temp / 1024) + "M";
		} else {
			return df.format(temp) + "K";
		}
	}

	/**
	 * 转换文件大小
	 *
	 * @param fileS
	 * @return B/KB/MB/GB
	 */
	public static String formatFileSize(long fileS) {
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 获取目录文件大小
	 *
	 * @param dir
	 * @return
	 */
	public static long getDirSize(File dir) {
		if (dir == null) {
			return 0;
		}
		if (!dir.isDirectory()) {
			return 0;
		}
		long dirSize = 0;
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				dirSize += file.length();
			} else if (file.isDirectory()) {
				dirSize += file.length();
				dirSize += getDirSize(file); // 递归调用继续统计
			}
		}
		return dirSize;
	}

	/**
	 * 获取目录文件个数
	 *
	 * @param
	 * @return
	 */
	public long getFileList(File dir) {
		long count = 0;
		File[] files = dir.listFiles();
		count = files.length;
		for (File file : files) {
			if (file.isDirectory()) {
				count = count + getFileList(file);// 递归
				count--;
			}
		}
		return count;
	}

	public static byte[] toBytes(InputStream in) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int ch;
		while ((ch = in.read()) != -1) {
			out.write(ch);
		}
		byte buffer[] = out.toByteArray();
		out.close();
		return buffer;
	}

	/**
	 * 检查文件是否存在
	 *
	 * @param name
	 * @return
	 */
	public static boolean checkFileExists(String name) {
		boolean status;
		if (!name.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + name);
			status = newPath.exists();
		} else {
			status = false;
		}
		return status;
	}

	/**
	 * 检查路径是否存在
	 *
	 * @param path
	 * @return
	 */
	public static boolean checkFilePathExists(String path) {
		return new File(path).exists();
	}

	/**
	 * 计算SD卡的剩余空间
	 *
	 * @return 返回-1，说明没有安装sd卡
	 */
	public static long getFreeDiskSpace() {
		String status = Environment.getExternalStorageState();
		long freeSpace = 0;
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			try {
				File path = Environment.getExternalStorageDirectory();
				StatFs stat = new StatFs(path.getPath());
				long blockSize = stat.getBlockSize();
				long availableBlocks = stat.getAvailableBlocks();
				freeSpace = availableBlocks * blockSize / 1024;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return -1;
		}
		return (freeSpace);
	}

	/**
	 * 新建目录
	 *
	 * @param directoryName
	 * @return
	 */
	public static boolean createDirectory(String directoryName) {
		boolean status;
		if (!directoryName.equals("")) {
			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + directoryName);
			status = newPath.mkdir();
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * 检查是否安装SD卡
	 *
	 * @return
	 */
	public static boolean checkSaveLocationExists() {
		String sDCardStatus = Environment.getExternalStorageState();
		boolean status;
		if (sDCardStatus.equals(Environment.MEDIA_MOUNTED)) {
			status = true;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除目录(包括：目录里的所有文件)
	 *    yey/
	 * @param fileName
	 * @return
	 */
	public static boolean deleteDirectory(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

			//File path = Environment.getExternalStorageDirectory();
			File newPath = new File(fileName);
			//checker.checkDelete(newPath.toString());
			if (newPath.isDirectory()) {
				String[] listfile = newPath.list();
				// delete all files within the specified directory and then
				// delete the directory
				try {
					for (int i = 0; i < listfile.length; i++) {
						File deletedFile = new File(newPath.toString() + "/"
								+ listfile[i].toString());
						deletedFile.delete();
					}
					newPath.delete();
					Log.i("DirectoryManager deleteDirectory", fileName);
					status = true;
				} catch (Exception e) {
					e.printStackTrace();
					status = false;
				}

			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除文件
	 *
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String fileName) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!fileName.equals("")) {

			File path = Environment.getExternalStorageDirectory();
			File newPath = new File(path.toString() + fileName);
			checker.checkDelete(newPath.toString());
			if (newPath.isFile()) {
				try {
					Log.i("DirectoryManager deleteFile", fileName);
					newPath.delete();
					status = true;
				} catch (SecurityException se) {
					se.printStackTrace();
					status = false;
				}
			} else
				status = false;
		} else
			status = false;
		return status;
	}


	/**
	 * 删除文件
	 *
	 * @param
	 * @return
	 */
	public static boolean deleteFileByPath(String filepath) {
		boolean status;
		SecurityManager checker = new SecurityManager();

		if (!filepath.equals("")) {
			File newPath = new File(filepath);
			checker.checkDelete(newPath.toString());
			if (newPath.isFile()) {
				try {
					Log.i("DirectoryManager deleteFile", filepath);
					newPath.delete();
					status = true;
				} catch (SecurityException se) {
					se.printStackTrace();
					status = false;
				}
			} else
				status = false;
		} else
			status = false;
		return status;
	}

	/**
	 * 删除空目录
	 *
	 * 返回 0代表成功 ,1 代表没有删除权限, 2代表不是空目录,3 代表未知错误
	 *
	 * @return
	 */
	public static int deleteBlankPath(String path) {
		File f = new File(path);
		if (!f.canWrite()) {
			return 1;
		}
		if (f.list() != null && f.list().length > 0) {
			return 2;
		}
		if (f.delete()) {
			return 0;
		}
		return 3;
	}

	/**
	 *
	 * 删除SD卡的文件
	 * (这里描述这个方法适用条件 – 可选)
	 * @param file
	 *void
	 * @exception
	 * @since  1.0.0
	 */
	public static void deleSDFolder(File file){
		if(file.exists()){
			if(file.listFiles().length>0){
				for(File temp :file.listFiles()){
					if(temp.isDirectory()){
						deleSDFolder(temp);
					}else{
						temp.delete();
					}
				}
			}
			file.delete();
		}
		try{
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 重命名
	 *
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public static boolean reNamePath(String oldName, String newName) {
		File f = new File(oldName);
		return f.renameTo(new File(newName));
	}

	/**
	 * 删除文件
	 *
	 * @param filePath
	 */
	public static boolean deleteFileWithPath(String filePath) {
		SecurityManager checker = new SecurityManager();
		File f = new File(filePath);
		checker.checkDelete(filePath);
		if (f.isFile()) {
			Log.i("DirectoryManager deleteFile", filePath);
			f.delete();
			return true;
		}
		return false;
	}

	/**
	 * 获取SD卡的根目录，末尾带\
	 *
	 * @return
	 */
	public static String getSDRoot() {
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator;
	}

	/**
	 * 列出root目录下所有子目录
	 *
	 * @param
	 * @return 绝对路径
	 */
	public static List<String> listPath(String root) {
		List<String> allDir = new ArrayList<String>();
		SecurityManager checker = new SecurityManager();
		File path = new File(root);
		checker.checkRead(root);
		if (path.isDirectory()) {
			for (File f : path.listFiles()) {
				if (f.isDirectory()) {
					allDir.add(f.getAbsolutePath());
				}
			}
		}
		return allDir;
	}

	public enum PathStatus {
		SUCCESS, EXITS, ERROR
	}

	/**
	 * 创建目录
	 *
	 * @param
	 */
	public static PathStatus createPath(String newPath) {
		File path = new File(newPath);
		if (path.exists()) {
			return PathStatus.EXITS;
		}
		if (path.mkdir()) {
			return PathStatus.SUCCESS;
		} else {
			return PathStatus.ERROR;
		}
	}

	/**
	 * 截取路径名
	 *
	 * @return
	 */
	public static String getPathName(String absolutePath) {
		int start = absolutePath.lastIndexOf(File.separator) + 1;
		int end = absolutePath.length();
		return absolutePath.substring(start, end);
	}


	/**
	 * 删除该目录下的文件
	 *
	 * @param path
	 */
	public static void delFile(String path) {
		if (!TextUtils.isEmpty(path)) {
			File file = new File(path);
			if (file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * 获取本地图片bitmap
	 * @param path
	 * @return
	 */
//	public static Bitmap getLocalBitmap(Context context,String path){
//		SoftReference bitmap =null;
//		try {
//			InputStream fis= new FileInputStream(path);
//			bitmap=new SoftReference(BitmapFactory.decodeStream(fis));
//		} catch (FileNotFoundException e) {
//           android.os.Process.killProcess(android.os.Process.myPid());
//           System.exit(1);
//		}catch (OutOfMemoryError e) {
//            if(bitmap!=null){
//                ((Bitmap)bitmap.get()).recycle();
//                Bitmap cbitmap=null;
//                final DisplayMetrics metrics = AppContext.getInstance().getResources().getDisplayMetrics();
//                int size = (int) (Math.min(metrics.widthPixels, metrics.heightPixels) / 0.75);
//                DecodeUtils utils = new DecodeUtils();
//                cbitmap=utils.decode(AppContext.getInstance(), Uri.parse(path), size, size);
//                utils=null;
//                return cbitmap;
//            }
//
//		}
//        if(bitmap==null){
//            bitmap= new SoftReference(BitmapUtil.getImageByPath(path, false));
//        }
//		return (Bitmap) bitmap.get();
//	}


    public static Bitmap revitionImageSize(String path){
        BufferedInputStream in = null;
        Bitmap bitmap = null;
        try {
            in = new BufferedInputStream(new FileInputStream(new File(path)));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(in, null, options);
        in.close();
        int i = 0;
        while (true) {
            if ((options.outWidth >> i <= 256)
                    && (options.outHeight >> i <= 256)) {
                in = new BufferedInputStream(
                        new FileInputStream(new File(path)));
                options.inSampleSize = (int) Math.pow(2.0D, i);
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
         /**
          * 压缩图片的方法--普通质量压缩
          * @param path
          * @param savePath
          * @return
          */
//	public static String saveAsCommon(String path,String savePath) {
////        if(AppUtils.getmem_UNUSED(AppContext.getInstance())<1024*1024){
////        }
//        ImageLoader.getInstance().clearMemoryCache();
//        System.gc();
//
//        String name = "";
//		File file = new File(savePath);
//		if(!file.exists()){
//			file.mkdirs();// 创建文件夹
//		}
//		 int degree = readPictureDegree(path);
//		 final DisplayMetrics metrics = AppContext.getInstance().getResources().getDisplayMetrics();
//	     int size = (int) (Math.min(metrics.widthPixels, metrics.heightPixels) / 0.75);
//
//        if(path!=null && !path.equals("")){
//            DecodeUtils utils = new DecodeUtils();
//             Bitmap cbitmap = null;
//             try {
////                 cbitmap = ImageLoader.getInstance().loadImageSync("file:///"+path);
//               cbitmap =  utils.decode(AppContext.getInstance(), Uri.parse(path),size,size);
//             }catch (OutOfMemoryError error){
//                 cbitmap =  utils.decode(AppContext.getInstance(), Uri.parse(path),size,size);
//             }
//            if(cbitmap==null){
//                return "";
//            }
//            if(degree>0){
//                cbitmap=BitmapUtil.rotaingImageView(degree, cbitmap);
//            }
//            File f = new File(path);
//            name=f.getName();
//            Boolean contents=false;
//            File root=new File(path);
//            File[] fils=root.listFiles();
//            if(fils != null){
//                for (File af : fils){
//                    if(af.isDirectory()){
//                        af.getName().equals(name);
//                        contents=true;
//                        break;
//                    }
//                }
//            }
//            if(!contents){
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                if(cbitmap==null){
//                    return "";
//                }
//                if(!cbitmap.isRecycled())
//                cbitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                FileOutputStream fos;
//                try {
//                    fos = new FileOutputStream(new File(savePath,
//                            name));
//                    int options = 100;
//
//                    while (baos.toByteArray().length / 1024 > 80 && options != 10) {
//                        baos.reset();
//                        if(cbitmap!=null&&baos!=null){
//                            try {
//                                cbitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
//                            }catch (NullPointerException e){
//                                 return "";
//                            }
//                        }else {
//                            return path;
//                        }
//                         options -= 30;
//                    }
//                    fos.write(baos.toByteArray());
//                    fos.close();
//                    baos.close();
//                    if(cbitmap==null){
//                        return "";
//                    }
//                    if(cbitmap!=null && !cbitmap.isRecycled()){
//                         cbitmap.recycle();
//                         System.gc();
//                         cbitmap = null;
//                    }
//                } catch (FileNotFoundException e) {
//
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//             if(savePath+name==null){
//                 return "";
//             }
//			return savePath+name;
//	    }
	/**
	 * 保存高清图片的算法
	 * @param path
	 * @param savePath
	 * @return
	 */
//	public static String saveAsHd(Context context,String path,String savePath) {
//		String name = "";
//		File file = new File(savePath);
//		if(!file.exists()){
//			file.mkdirs();// 创建文件夹
//		}
//		 int degree = readPictureDegree(path);
//         Bitmap cbitmap = ImageLoader.getInstance().loadImageSync("file:///"+path);
////	     SoftReference cbitmap = new SoftReference(getLocalBitmap(context,path));
//        if(cbitmap==null){
//            return null;
//        }
//	     if(degree>0){
//	    	 cbitmap= BitmapUtil.rotaingImageView(degree,cbitmap);
//	     }
//
//			File f = new File(path);
//			name=f.getName();
//			Boolean contents=false;
//			File root=new File(path);
//			File[] fils=root.listFiles();
//			if(fils != null){
//		            for (File af : fils){
//		                if(af.isDirectory()){
//		                    af.getName().equals(name);
//		                    contents=true;
//		                    break;
//		                }
//		            }
//		     }
//			if(!contents){
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				FileOutputStream fos;
//				try {
//					fos = new FileOutputStream(new File(savePath,
//							name));
//					int options = 90;
//					int length = baos.toByteArray().length / 1024;
//					System.out.println(length);
//					options -= 10;
//                   cbitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
//
//					fos.write(baos.toByteArray());
//					fos.close();
//					baos.close();
//					if(cbitmap!=null && !cbitmap.isRecycled()){
//                        cbitmap.recycle();
//                        System.gc();
//                        cbitmap = null;
//					}
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			return savePath+name;
//	      }
//
//	/**
//	 *
//	 * @param path
//	 * @param savePath
//	 * @return
//	 */
//	public static String saveAsHdScale(String path,String savePath) {
//		String name = "";
//		File file = new File(savePath);
//		if(!file.exists()){
//			file.mkdirs();// 创建文件夹
//		}
//		 int degree = readPictureDegree(path);
//		 final DisplayMetrics metrics = AppContext.getInstance().getResources().getDisplayMetrics();
//			int size = (int) (Math.min(metrics.widthPixels, metrics.heightPixels) / 0.75);
//         DecodeUtils utils = new DecodeUtils();
//         Bitmap cbitmap=utils.decode(AppContext.getInstance(), Uri.parse(path), size, size);
//         if(cbitmap==null){
//            return null;
//         }
//         if(degree>0){
//        	 cbitmap=BitmapUtil.rotaingImageView(degree, cbitmap);
//         }
//
//			File f = new File(path);
//			name=f.getName();
//			Boolean contents=false;
//			File root=new File(path);
//			File[] fils=root.listFiles();
//			if(fils != null){
//		            for (File af : fils){
//		                if(af.isDirectory()){
//		                    af.getName().equals(name);
//		                    contents=true;
//		                    break;
//		                }
//		            }
//		     }
//			if(!contents){
//			    BitmapUtil.savePhotoToSDCard(cbitmap, savePath, name);
//			}
//			return savePath+name;
//	     }
	
}