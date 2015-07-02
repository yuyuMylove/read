package com.yey.read.util;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.yey.read.R;

public class ImageLoadOptions {



	public static DisplayImageOptions getOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				// // 设置图片在下载期间显示的图片
				// .showImageOnLoading(R.drawable.small_image_holder_listpage)
				// // 设置图片Uri为空或是错误的时候显示的图片
				// .showImageForEmptyUri(R.drawable.small_image_holder_listpage)
				// // 设置图片加载/解码过程中错误时候显示的图片
				 .showImageOnFail(R.drawable.icon_imageview_error)
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.ARGB_4444)// 设置图片的解码类型
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				.considerExifParams(true)
				// 设置图片下载前的延迟
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// 。preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				// .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//				.displayer(new FadeInBitmapDisplayer(100))// 淡入
				.build();
		return options;
	}
	
	//头像
	public static DisplayImageOptions getHeadOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				// // 设置图片在下载期间显示的图片
				 .showImageOnLoading(R.drawable.defaulticon)
				// // 设置图片Uri为空或是错误的时候显示的图片
				 .showImageForEmptyUri(R.drawable.defaulticon)
				// // 设置图片加载/解码过程中错误时候显示的图片
				 .showImageOnFail(R.drawable.defaulticon)	
				.cacheInMemory(true)
				
				// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)
				// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				.considerExifParams(true)
				// 设置图片下载前的延迟
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// 。preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				// .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//				.displayer(new FadeInBitmapDisplayer(100))// 淡入
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();
		return options;
	}
	
	public static DisplayImageOptions getFriendOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				 // 设置图片在下载期间显示的图片
				 .showImageOnLoading(R.drawable.defaulticon)
				 // 设置图片Uri为空或是错误的时候显示的图片
				 .showImageForEmptyUri(R.drawable.defaulticon)
				 // 设置图片加载/解码过程中错误时候显示的图片
				 .showImageOnFail(R.drawable.defaulticon)
				.cacheInMemory(true)
				// 设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)
				// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true)
				.imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				.considerExifParams(true)
				// 设置图片下载前的延迟
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// 。preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(true)
                // 设置图片在下载前是否重置，复位
				// .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
//				.displayer(new FadeInBitmapDisplayer(100))// 淡入
				.build();
		return options;
	}
	
//	public static DisplayImageOptions getFriendDataOptions() {
//		DisplayImageOptions options = new DisplayImageOptions.Builder()
//		 // 设置图片在下载期间显示的图片
//		 .showImageOnLoading(R.drawable.common_defalut_photo_loading)
//		 // 设置图片Uri为空或是错误的时候显示的图片
//		 .showImageForEmptyUri(R.drawable.common_defalut_photo_loading)
//		 // 设置图片加载/解码过程中错误时候显示的图片
//		 .showImageOnFail(R.drawable.common_defalut_photo_loading)
//		.cacheInMemory(true)
//		// 设置下载的图片是否缓存在内存中
//		.cacheOnDisk(true)
//		// 设置下载的图片是否缓存在SD卡中
//		.considerExifParams(true)
//		.imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
//		.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
//		// .decodingOptions(android.graphics.BitmapFactory.Options
//		// decodingOptions)//设置图片的解码配置
//		.considerExifParams(true)
//		// 设置图片下载前的延迟
//		// .delayBeforeLoading(int delayInMillis)//int
//		// delayInMillis为你设置的延迟时间
//		// 设置图片加入缓存前，对bitmap进行设置
//		// 。preProcessor(BitmapProcessor preProcessor)
//		.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
//		// .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
////		.displayer(new FadeInBitmapDisplayer(100))// 淡入
//		.build();
//  return options;
//	}
//
//
//	public static DisplayImageOptions getContactsPuacPicOptions() {
//		DisplayImageOptions options =  new DisplayImageOptions.Builder()
//		.showImageOnLoading(R.drawable.puacdefaulteicon)
//		.showImageForEmptyUri(R.drawable.puacdefaulteicon)
//		.showImageOnFail(R.drawable.puacdefaulteicon)
//		.cacheInMemory(true)
//		.cacheOnDisk(true)
//		.considerExifParams(true)
//		.bitmapConfig(Bitmap.Config.RGB_565)
//		.build();
//         return options;
//	}
	
	public static DisplayImageOptions getAppPicOptions() {
		DisplayImageOptions options =  new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_launcher)
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.ic_launcher)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
         return options;
	}
	
	public static DisplayImageOptions getContactsFriendPicOptions() {
		DisplayImageOptions options =  new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.defaulticon)
		.showImageForEmptyUri(R.drawable.defaulticon)
		.showImageOnFail(R.drawable.defaulticon)
		.cacheInMemory(true)
		.cacheOnDisk(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
         return options;
	}
	
	//头像
		public static DisplayImageOptions getChatOptions() {
			DisplayImageOptions options = new DisplayImageOptions.Builder()
					 .showImageOnLoading(R.drawable.defaulticon)
					 .showImageForEmptyUri(R.drawable.defaulticon)
					 .showImageOnFail(R.drawable.defaulticon)	
					.cacheInMemory(true)
					.cacheOnDisk(true)
					.considerExifParams(true)
					.imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
					.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
					.considerExifParams(true)		
					.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
					 .displayer(new RoundedBitmapDisplayer(100))//是否设置为圆角，弧度为多少
					//.displayer(new FadeInBitmapDisplayer(100))// 淡入
					.build();
			return options;
		}
		
		
		//头像
//				public static DisplayImageOptions getMessagePublicOptions() {
//					DisplayImageOptions options = new DisplayImageOptions.Builder()
//							 .showImageOnLoading(R.drawable.puacdefaulteicon)
//							 .showImageForEmptyUri(R.drawable.puacdefaulteicon)
//							 .showImageOnFail(R.drawable.puacdefaulteicon)
//							.cacheInMemory(true)
//							.cacheOnDisc(true)
//							.considerExifParams(true)
//							.imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
//							.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
//							.considerExifParams(true)
//							.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
//							 .displayer(new RoundedBitmapDisplayer(50))//是否设置为圆角，弧度为多少
//							//.displayer(new FadeInBitmapDisplayer(100))// 淡入
//							.build();
//					return options;
//				}
				
//				public static DisplayImageOptions getMessagePublicOptions_view() {
//					DisplayImageOptions options = new DisplayImageOptions.Builder()
//							 .showImageOnLoading(R.drawable.icon_image_loading_default)
//							 .showImageForEmptyUri(R.drawable.icon_image_loading_default)
//							 .showImageOnFail(R.drawable.icon_image_loading_default)
//							.cacheInMemory(true)
//							.cacheOnDisc(true)
//							.considerExifParams(true)
//							.imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
//							.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型
//							.considerExifParams(true)
//							.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
//							// .displayer(new RoundedBitmapDisplayer(50))//是否设置为圆角，弧度为多少
//							//.displayer(new FadeInBitmapDisplayer(100))// 淡入
//							.build();
//					return options;
//				}
		
				//相册
				public static DisplayImageOptions getCameraOptions() {
					DisplayImageOptions options;
					options = new DisplayImageOptions.Builder()
					// .showImageOnLoading(R.drawable.ico) //设置图片在下载期间显示的图片  
					// .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片  
					.showImageOnFail(R.drawable.icon_imageview_error)  //设置图片加载/解码过程中错误时候显示的图片
					.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
					.cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中  
					.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
					.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
					.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
					//.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
					//设置图片加入缓存前，对bitmap进行设置  
					//.preProcessor(BitmapProcessor preProcessor)  
					.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
//					.displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少  	  
					.build();//构建完成 
					return options;
				}
				
				/**
				 * 班级相册
				 * @return
				 */
//				public static DisplayImageOptions getClassPhotoOptions() {
//					DisplayImageOptions options;
//					options = new DisplayImageOptions.Builder()
//					.showImageOnLoading(R.drawable.icon_image_galley_default) //设置图片在下载期间显示的图片
//					.showImageForEmptyUri(R.drawable.icon_image_galley_default)//设置图片Uri为空或是错误的时候显示的图片
//					.showImageOnFail(R.drawable.icon_image_galley_default)  //设置图片加载/解码过程中错误时候显示的图片
//					.cacheInMemory(true)//设置下载的图片是否缓存在内存中
//					.cacheOnDisk(false)//设置下载的图片是否缓存在SD卡中
//					.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//					.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//                            //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
//					//设置图片加入缓存前，对bitmap进行设置
//					//.preProcessor(BitmapProcessor preProcessor)
//					.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//					.build();//构建完成
//					return options;
//				}
				
				//相片的
//				public static DisplayImageOptions getGalleryOptions() {
//					DisplayImageOptions options;
//					options = new DisplayImageOptions.Builder()
//					 .showImageOnLoading(R.drawable.icon_image_loading_default) //设置图片在下载期间显示的图片
//					 .showImageForEmptyUri(R.drawable.icon_image_loading_default)//设置图片Uri为空或是错误的时候显示的图片
//					.showImageOnFail(R.drawable.icon_image_loading_default)  //设置图片加载/解码过程中错误时候显示的图片
//					.cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
//					.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//					.imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
//					.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//					//.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
//					//设置图片加入缓存前，对bitmap进行设置
//					//.preProcessor(BitmapProcessor preProcessor)
//					.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//					.build();//构建完成
//					return options;
//				}
				
//				public static DisplayImageOptions getLifePhotoOptions() {
//					DisplayImageOptions options;
//					options = new DisplayImageOptions.Builder()
//					 .showImageOnLoading(R.drawable.icon_image_loading_default) //设置图片在下载期间显示的图片
//					 .showImageForEmptyUri(R.drawable.ic_empty)//设置图片Uri为空或是错误的时候显示的图片
//					.showImageOnFail(R.drawable.ic_error)  //设置图片加载/解码过程中错误时候显示的图片
//					.cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
//					.considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//					.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)//设置图片以如何的编码方式显示
//					.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//					//设置图片加入缓存前，对bitmap进行设置
//					//.preProcessor(BitmapProcessor preProcessor)
//					.build();//构建完成
//					return options;
//				}
//
//				public static DisplayImageOptions getPhotoViewOptions() {
//                    DisplayImageOptions options;
//                options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.color.black) //设置图片在下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.ic_empty)//设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.ic_error)  //设置图片加载/解码过程中错误时候显示的图片
//                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
//                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
//                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)//设置图片以如何的编码方式显示
//                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
//                        //设置图片加入缓存前，对bitmap进行设置
//                        //.preProcessor(BitmapProcessor preProcessor)
////					.resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
//                .build();//构建完成
//        return options;
//    }
}
