package com.xhq.healthy.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/9/21.
 */
public class FragmentArgs implements Serializable{

    private Map<String,Serializable> values = new HashMap<String,Serializable>();

    public FragmentArgs add(String key,Serializable value){
        if(!TextUtils.isEmpty(key) && value != null)
            values.put(key,value);
        return this;
    }

    public Serializable get(String key){return values.get(key);}

    public static void setToBundle(Bundle bundle ,FragmentArgs args){
        Set<String> keys = args.values.keySet();
        for(String key : keys){
            Object o = args.get(key);
            if(o == null)
                continue;
            bundle.putSerializable(key,(Serializable)o);
        }
    }

    public static FragmentArgs transToArgs(Bundle bundle){
        FragmentArgs args = new FragmentArgs();
        for(String key : bundle.keySet()){
            Object o = bundle.get(key);
            if(o == null)
                continue;
            args.add(key, (Serializable) o);
        }
        return  args;
    }

    public static Bundle transToBundle(FragmentArgs args){
        Bundle bundle = new Bundle();
        setToBundle(bundle,args);
        return bundle;
    }
}
