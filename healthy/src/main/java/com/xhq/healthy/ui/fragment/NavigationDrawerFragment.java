package com.xhq.healthy.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.xhq.healthy.activity.R;
import com.xhq.healthy.ui.bean.MenuBean;
import com.xhq.healthy.util.Logger;
import com.xhq.healthy.util.ThemeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment {
    public static final String TAG = NavigationDrawerFragment.class.getSimpleName();
    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ListView mDrawerListView;
    private BaseAdapter mAdapter;
    private List<MenuBean> mMenuBeans;
    private MenuBean mLastSelectedMenuBean ;


    public static NavigationDrawerFragment newInstance(String type){
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        if(!TextUtils.isEmpty(type)){
            Bundle bundle = new Bundle();
            bundle.putString("type",type);
            fragment.setArguments(bundle);
        }
        return fragment;
    }
    public NavigationDrawerFragment() {
        Logger.d(TAG, "NavigationDrawerFragment");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(TAG, "onCreate");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Logger.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        if(getActivity() instanceof NavigationDrawerCallbacks )
            mCallbacks = (NavigationDrawerCallbacks)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View rootView = inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        mDrawerListView = (ListView)rootView.findViewById(R.id.navigation_listview);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });

        if(mMenuBeans == null) {
            mMenuBeans = new ArrayList<MenuBean>();
            mMenuBeans.add(MenuBean.newInstance(0));
            mMenuBeans.add(MenuBean.newInstance(1));
            mMenuBeans.add(MenuBean.newInstance(1000));
            mMenuBeans.add(MenuBean.newInstance(2));
            mMenuBeans.add(MenuBean.newInstance(3));
        }
        mAdapter = new MyAdapter(getActivity().getApplicationContext(), mMenuBeans);
        mDrawerListView.setAdapter(mAdapter);
        return rootView;
    }

    public void setSelectedMenu(MenuBean menu){
        Logger.d(TAG, "setSelectedPosition");
        mLastSelectedMenuBean = menu;
        if(mAdapter != null)
            mAdapter.notifyDataSetChanged();

    }

    private void selectItem(int position) {
        mLastSelectedMenuBean = mMenuBeans.get(position);
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(mLastSelectedMenuBean);
        }
        if(mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(MenuBean menu);
    }

    public class MyAdapter extends BaseAdapter {

        private List<MenuBean> mMenuBeans;
        private Context mContext;

        public MyAdapter(Context context,List<MenuBean> menus){
            mContext = context;
            if(menus == null)
                mMenuBeans = new ArrayList<MenuBean>();
            else
                mMenuBeans = menus;
        }
        @Override
        public int getCount() {
            return mMenuBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return mMenuBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null)
                convertView = View.inflate(mContext,R.layout.item_mainmenu,null);
            MenuBean menu = mMenuBeans.get(position);
            if(menu.getId() == 1000){
                convertView.findViewById(R.id.itemDivider).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.itemDivider).setBackgroundColor(ThemeUtils.getThemeColor(mContext));
                convertView.findViewById(R.id.itemContent).setVisibility(View.GONE);
            }else{
                convertView.findViewById(R.id.itemDivider).setVisibility(View.GONE);
                convertView.findViewById(R.id.itemContent).setVisibility(View.VISIBLE);

                ImageView icon = (ImageView) convertView.findViewById(R.id.menu_icon);
                if(menu.getIconId()>0) {
                    icon.setImageResource(menu.getIconId());
                    icon.setVisibility(View.VISIBLE);
                }else{
                    icon.setVisibility(View.GONE);
                }
                TextView title = (TextView)convertView.findViewById(R.id.menu_title);
                title.setText(menu.getTitleId());
                View item = convertView.findViewById(R.id.itemContent);
                if(mLastSelectedMenuBean != null && mLastSelectedMenuBean.getId() == menu.getId()){
                    title.setTextColor(ThemeUtils.getThemeColor(mContext));
                    item.setBackgroundResource(R.drawable.abc_list_pressed_holo_light);
                }else {
                    title.setTextColor(Color.parseColor("#000000"));
                    item.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            return convertView;
        }
    }
}
