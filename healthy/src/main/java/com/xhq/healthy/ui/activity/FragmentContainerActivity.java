package com.xhq.healthy.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.xhq.healthy.activity.R;

public class FragmentContainerActivity extends BaseActivity {


    public static void lauch(Activity activity,Class<? extends Fragment> clazz,FragmentArgs args){
        Intent intent = new Intent(activity,FragmentContainerActivity.class);
        intent.putExtra("classname",clazz.getClass());
        if(args != null)
            intent.putExtra("args",args);
        activity.startActivity(intent);
    }

    public static void lauchForResult(Fragment fragment,Class<? extends Fragment> clazz,
                                      FragmentArgs args,int requestCode){
        if(fragment.getActivity() == null)
            return;;
        Intent intent = new Intent(fragment.getActivity(),FragmentContainerActivity.class);
        intent.putExtra("classname",clazz.getClass());
        if(args != null)
            intent.putExtra("args",args);
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void laucnForResult(BaseActivity activity,Class<? extends Fragment> clazz,
                                      FragmentArgs args,int requestCode){
        Intent intent = new Intent(activity,FragmentContainerActivity.class);
        intent.putExtra("classname",clazz.getClass());
        if(args != null)
            intent.putExtra("args",args);
        activity.startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_contain);
    }

}
