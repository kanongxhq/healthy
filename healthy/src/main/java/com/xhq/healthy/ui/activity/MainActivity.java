package com.xhq.healthy.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.xhq.healthy.activity.R;
import com.xhq.healthy.base.BaseApplication;
import com.xhq.healthy.base.SettingUtility;
import com.xhq.healthy.ui.bean.MenuBean;
import com.xhq.healthy.ui.fragment.NavigationDrawerFragment;
import com.xhq.healthy.ui.fragment.NavigationDrawerFragment.NavigationDrawerCallbacks;
import com.xhq.healthy.util.Logger;

public class MainActivity extends BaseActivity implements NavigationDrawerCallbacks {
    public static final String TAG = MainActivity.class.getSimpleName();
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    private int mBackKeyRepeatCount = 0;
    private MenuBean mLastSelectedMenuBean = null;
    private NavigationDrawerFragment mDrawerFragment = null;

    public static void lauch(){
        Intent intent = new Intent(BaseApplication.getInstance(),MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.getInstance().startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawlayout);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //决定左上角的图标是否可以点击。没有向左的小图标。 true 图标可以点击  false 不可以点击。
        getSupportActionBar().setHomeButtonEnabled(true);
        // 给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 使左上角图标是否显示
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,mToolbar,
                R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View view){
                super.onDrawerOpened(view);
            }

            @Override
            public void onDrawerClosed(View view){
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };
        mActionBarDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);

        int menuId = SettingUtility.getIntSetting("lastmenu",0);
        mLastSelectedMenuBean = MenuBean.newInstance(menuId);
        getSupportActionBar().setTitle(mLastSelectedMenuBean.getTitleId());

        if(getSupportFragmentManager().findFragmentByTag("DrawerFragment") == null){
            mDrawerFragment = new NavigationDrawerFragment();
            mDrawerFragment.setSelectedMenu(mLastSelectedMenuBean);
            getSupportFragmentManager().beginTransaction().add(R.id.nativation_container,mDrawerFragment,"DrawerFragment").commit();
        }else{
            mDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentByTag("DrawerFragment");
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Logger.d(TAG,"onCreateOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        Logger.d(TAG,"onPrepareOptionsMenu");
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isDrawerOpened() {
        return mDrawerLayout.isDrawerOpen(Gravity.LEFT) || mDrawerLayout.isDrawerOpen(Gravity.RIGHT);
    }

    @Override
    public void onNavigationDrawerItemSelected(MenuBean menu) {
        Logger.d(TAG, "onNavigationDrawerItemSelected: " + menu.getId());
        int id = menu.getId();
        SettingUtility.putIntSetting("lastmenu",id);
        mToolbar.setTitle(menu.getTitleId());
        switch (id)  {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        mDrawerLayout.closeDrawers();
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        Logger.d(TAG,"onKeyDown keyCode: "+keyCode);
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            if(isDrawerOpened())
                mDrawerLayout.closeDrawers();
            else {
                mBackKeyRepeatCount++;
                if(mBackKeyRepeatCount >=2)
                    finish();
                else
                    Toast.makeText(this,"再按一次退出",Toast.LENGTH_SHORT).show();
                BaseApplication.getInstance().getHandler().postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        mBackKeyRepeatCount =0;
                    }
                },1500);
            }

            return true;
        }else if(keyCode == KeyEvent.KEYCODE_MENU){
            if(isDrawerOpened())
                mDrawerLayout.closeDrawers();
            else
                mDrawerLayout.openDrawer(Gravity.LEFT);
            return  true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
