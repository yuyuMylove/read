package com.yey.read.util;

/**
 * Created by sunnie on 15/6/1.
 */
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

public class SharedPreferencesHelper {
    private static String PREF_FILE = "Reader_Pref";
    public static String PREF_LOGIN_FILE="reader_login_Pref";
    private static Map<String, SharedPreferencesHelper> instances = new HashMap<String, SharedPreferencesHelper>();

    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    private SharedPreferencesHelper(Context context, String pref) {

        settings = context
                .getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        editor = settings.edit();

    }


    public static SharedPreferencesHelper getInstance(Context context) {
        return getInstance(context, null);
    }
    public static SharedPreferencesHelper getInstance(Context context, String pref){
        if(pref != null) PREF_FILE = pref;
        if (!instances.containsKey(PREF_FILE)){
            instances.put(PREF_FILE, new SharedPreferencesHelper(context.getApplicationContext(), pref));
        }

        return instances.get(PREF_FILE);
    }

    public String getString(String key, String defValue) {
        return settings.getString(key, defValue);
    }

    public SharedPreferencesHelper setString(String key, String value) {
        editor.putString(key, value);
        editor.commit();

        return this;
    }

    public int getInt(String key, int defValue) {
        return settings.getInt(key, defValue);
    }

    public SharedPreferencesHelper setInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();

        return this;
    }

    public long getLong(String key, long defValue) {
        return settings.getLong(key, defValue);
    }

    public SharedPreferencesHelper setLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();

        return this;
    }
    public boolean getBoolean(String key, boolean defValue) {
        return settings.getBoolean(key, defValue);
    }

    public SharedPreferencesHelper setBoolean(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();

        return this;
    }

    public float getFloat(String key, float defValue) {
        return settings.getFloat(key, defValue);
    }

    public SharedPreferencesHelper setFloat(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();

        return this;
    }


    public void removeItem(String key){
        editor.remove(key);
        editor.commit();
    }



    public Map<String, ?> getAlldata(){
        Map<String, ?> map  = settings.getAll();
        return map;
    }

}
