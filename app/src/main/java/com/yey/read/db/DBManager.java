package com.yey.read.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import com.yey.read.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBManager {
    private final int BUFFER_SIZE = 1024;
    public static final String DB_NAME = "city_cn.s3db";
    public static final String PACKAGE_NAME = "com.yey.kindergaten";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"+ PACKAGE_NAME;
    private SQLiteDatabase sqlite;
    private Context context;
    private File file=null;
    
    public DBManager(Context context) {
        this.context = context;
    }
 
    public void openDatabase() {
        this.sqlite = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }
    public SQLiteDatabase getDatabase(){
    	return this.sqlite;
    }
 
    private SQLiteDatabase openDatabase(String dbfile) {
        try {
        	file = new File(dbfile);
            if (!file.exists()) {
            	InputStream is = context.getResources().openRawResource(R.raw.city_cn);
            	FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count =is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                	fos.flush();
                }
                fos.close();
                is.close();
            }
            sqlite = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
            return sqlite;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
        }
        return null;
    }
     
    public void closeDatabase() {
    	if(this.sqlite!=null)
    		this.sqlite.close();
    }
}
