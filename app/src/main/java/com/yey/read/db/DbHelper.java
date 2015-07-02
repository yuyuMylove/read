package com.yey.read.db;

import android.content.Context;
import android.database.Cursor;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;
import com.lidroid.xutils.exception.DbException;
import com.yey.read.common.AccountInfo;
import com.yey.read.common.AppContext;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunnie on 15/6/2.
 */
public class DbHelper {
    private static DbUtils dbutils = null;
    public static  String DBNAME = "reader_";
    public static final int DBVERSION = 1  ;

    public DbHelper(Context context) {
        AccountInfo info = AppContext.getInstance().getAccountInfo();
        try {
            if(info.getUid()!=0){
                if(dbutils == null){
                    DBNAME = DBNAME + info.getUid();
                    dbutils = DbUtils.create(AppContext.getInstance(), DBNAME, DBVERSION, new DbUpgradeListener() {

                        @Override
                        public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                            if(newVersion>oldVersion){
                                //更新数据库

                            }

                        }
                    });
                    initSql();
                }
            }else{

            }


        } catch (Exception e) {

        }
    }

    public  synchronized static DbUtils getDB(Context context){
        AccountInfo info = AppContext.getInstance().getAccountInfo();
        try {
            if(info.getUid()!=0){
                if(dbutils == null){
                    DBNAME = DBNAME + info.getUid();
                    dbutils = DbUtils.create(AppContext.getInstance(), DBNAME, DBVERSION, new DbUpgradeListener() {

                        @Override
                        public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                            if(newVersion>oldVersion){
                                //更新数据库

                            }

                        }
                    });
                    initSql();
                }
            }else{

            }


        } catch (Exception e) {

        }
        if(dbutils==null){
            dbutils = DbUtils.create(context);
        }
        return dbutils;
    }

    public static void updateDBUtils(AccountInfo info){
        DBNAME = "kindergaten_" + info.getUid();
        dbutils = DbUtils.create(AppContext.getInstance(), DBNAME, DBVERSION, new DbUpgradeListener() {

            @Override
            public void onUpgrade(DbUtils db, int oldVersion, int newVersion) {
                if(newVersion>oldVersion){
                    //更新数据库

                }

            }
        });
    }

    public static void initSql() {
//        try {
//
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
    }

    public static <T> List<T> QueryTData(String sql, Class databean) {
        List<T> list = new ArrayList<T>();
        try {
            T  a=(T) databean.newInstance();
            Cursor cursor;
            cursor = DbHelper.getDB(AppContext.getInstance()).execQuery(sql);
            cursor.moveToFirst();
            list=(List<T>) getAList(a, cursor);
            cursor.close();
        } catch (InstantiationException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static <T> List<T> getAList(T databean,Cursor cursor){
        List<T> list = new ArrayList<T>();
        for(cursor.moveToFirst(); ! cursor.isAfterLast(); cursor.moveToNext()){
            Class<? extends Object> classType = databean.getClass();
            T data = null;
            try {
                data = (T) databean.getClass().newInstance();
                Field[] fields =classType.getDeclaredFields(); //获取T的所有属性
                databean.getClass().newInstance();
                if(fields !=null){
                    for(Field field: fields){
                        field.setAccessible(true);
                        Object objValue = null;
                        int index= cursor.getColumnIndex(field.getName());
                        if(index>-1){
                            objValue = cursor.getString(index);  //opt方法与get方法一样。不同的是get 如果为null 的时候异常，而 opt 可以返回空值
                            if(objValue!=null){
                                if (field.getType() == String.class)
                                {
                                    field.set(data, objValue);
                                }
                                if (field.getType() == int.class)
                                {
                                    field.set(data,Integer.valueOf(String.valueOf(objValue)));
                                }
                            }
                        }
                    }
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            list.add(data);
        }
        return list;
    }

}

