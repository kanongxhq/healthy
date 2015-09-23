package com.xhq.healthy.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;

import com.xhq.healthy.base.SettingUtility;
import com.xhq.healthy.util.Logger;
import com.xhq.healthy.util.ThemeUtils;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BaseActivity extends AppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();
    private int mTheme = 0;//主题
    private Locale mLanguage = Locale.CHINESE;
    private boolean isDestroy = false;
    private Map<String,WeakReference<Fragment>> mFragementRefs;

    private static BaseActivity mRunningActivity;
    public static BaseActivity getRunningActivity(){return mRunningActivity;}
    public static void setRunningActivity(BaseActivity activity){mRunningActivity = activity;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG,"onCreate");
        //setContentView(R.layout.activity_base);
        mFragementRefs = new HashMap<String,WeakReference<Fragment>>();
        mTheme = configTheme();
        setTheme(mTheme);
        mLanguage = new Locale(SettingUtility.getStringSetting(SettingUtility.APP_LANGUAGE,Locale.getDefault().getLanguage()),
                SettingUtility.getStringSetting(SettingUtility.APP_LANGUAGE_COUNTRY,Locale.getDefault().getCountry()));
        setLanguage(mLanguage);

        try{
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField!=null){
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config,false);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public int configTheme(){
        return ThemeUtils.themeArr[SettingUtility.getIntSetting(SettingUtility.APP_THEME,0)][0];
    }

    @Override
    public void onResume(){
        super.onResume();
        Logger.d(TAG, "onResume");
        setRunningActivity(this);
        if(mTheme != configTheme())
            reload();
        String languageStr = SettingUtility.getStringSetting(SettingUtility.APP_LANGUAGE,Locale.getDefault().getLanguage());
        String countryStr =  SettingUtility.getStringSetting(SettingUtility.APP_LANGUAGE_COUNTRY,Locale.getDefault().getCountry());
        if(mLanguage != null && (!languageStr.equals(mLanguage.getLanguage()) || !countryStr.equals(mLanguage.getCountry())))
            reload();
    }

    @Override
    public void finish(){
        super.finish();
        Logger.d(TAG, "finish");
        isDestroy = true;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Logger.d(TAG, "onDestroy");
        isDestroy = true;
    }


    public void setLanguage(Locale local){
        Resources res = getResources();
        Configuration config = res.getConfiguration();
        config.locale = local;
        DisplayMetrics dm = res.getDisplayMetrics();
        res.updateConfiguration(config,dm);
    }

    public void addFragment(String tag,Fragment fragment){
        mFragementRefs.put(tag, new WeakReference<Fragment>(fragment));
    }

    public void removeFragment(String tag){
        mFragementRefs.remove(tag);
    }

    public void reload(){
        Logger.d(TAG,"reload");
        Intent intent = getIntent();
        overridePendingTransition(0,0);
        finish();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
