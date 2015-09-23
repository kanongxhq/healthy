package com.xhq.healthy.base;

import android.app.Application;
import android.os.Handler;

import com.xhq.healthy.util.SdCardUtils;

/**
 * Created by Administrator on 2015/9/15.
 */
public class BaseApplication extends Application {


    private static BaseApplication mInstance = null;
    private Handler mHandler = new Handler();
    public Handler getHandler(){return mHandler;}

    public static BaseApplication getInstance(){return mInstance;}

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }

    public String getAppPath(){
        return SdCardUtils.getSdCardPath()+SettingUtility.getStringSetting(SettingUtility.APP_ROOT_PATH,"xdata/");
    }

    public String getDataPath(){
        return getAppPath()+SettingUtility.getStringSetting(SettingUtility.APP_DATA_DIR,"data/");
    }

    public String getImagePath(){
        return  getAppPath()+SettingUtility.getStringSetting(SettingUtility.APP_IMAGE_DIR,"image/");
    }
}
