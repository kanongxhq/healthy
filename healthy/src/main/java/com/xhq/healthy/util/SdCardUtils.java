package com.xhq.healthy.util;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by Administrator on 2015/9/15.
 */
public class SdCardUtils {

    public static boolean hasSdCard(){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            return true;
        }else{
            return false;
        }
    }

    public static String getSdCardPath(){
        if (hasSdCard())
            return Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator;
        return "/sdcard/";
    }

    public static boolean sdCardCanWrite(){
        return Environment.getExternalStorageDirectory().canWrite();
    }

    public static long getSdCardAvailableStore(){
        if (sdCardCanWrite()){
            String path = getSdCardPath();
            if (!TextUtils.isEmpty(path)){
                StatFs sf = new StatFs(path);
                return  sf.getBlockSizeLong()*sf.getAvailableBlocksLong();
            }
        }
        return 0;
    }
}
