package com.xhq.healthy.base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2015/9/15.
 */
public class SettingUtility {

    public static final String APP_ROOT_PATH = "app_root_path";
    public static final String APP_DATA_DIR ="app_data_dir";
    public static final String APP_IMAGE_DIR = "app_image_dir";
    public static final String APP_THEME = "app_theme";
    public static final String APP_LANGUAGE = "app_language";
    public static final String APP_LANGUAGE_COUNTRY = "app_language_countyr";
    public final static String FILE = "settings";
    public static int getIntSetting(String key,int defValue){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE, Context.MODE_PRIVATE);
        return sp.getInt(key,defValue);
    }

    public static int getIntSetting(String key){
        return getIntSetting(key, 0);
    }

    public static void putIntSetting(String key,int value){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public static long getLongSetting(String key,long defValue){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE,Context.MODE_PRIVATE);
        return sp.getLong(key,defValue);
    }

    public static long getLongSetting(String key){
        return getLongSetting(key, 0) ;
    }

    public static void putLongSetting(String key,long value){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key,value);
        editor.commit();
    }

    public static float getFloatSetting(String key,float defValue){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE,Context.MODE_PRIVATE);
        return sp.getFloat(key,defValue);
    }

    public static float getFloatSetting(String key){
        return getFloatSetting(key, 0f);
    }

    public  static void  putFloatSetting(String key,float value){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key,value);
        editor.commit();
    }

    public static boolean getBooleanSetting(String key,boolean defValue){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE,Context.MODE_PRIVATE);
        return sp.getBoolean(key,defValue);
    }

    public  static boolean getBooleanSetting(String key){
        return getBooleanSetting(key, false);
    }

    public static void putBooleanSetting(String key,boolean value){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,value);
    }

    public static String getStringSetting(String key,String defValue){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE,Context.MODE_PRIVATE);
        return sp.getString(key,defValue);
    }

    public static String getStringSetting(String key){
        return getStringSetting(key, "");
    }

    public static void putStringSetting(String key,String value){
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(FILE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
    }












}
