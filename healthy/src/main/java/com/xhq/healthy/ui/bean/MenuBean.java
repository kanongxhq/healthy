package com.xhq.healthy.ui.bean;

import com.xhq.healthy.activity.R;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/19.
 */
public class MenuBean implements Serializable{

    private int id;
    private int titleId;
    private int iconId;

    public static MenuBean newInstance(int id){
        switch (id){
            case 1000:
                return new MenuBean(1000,0,0);
            case 3:
                return  new MenuBean(3,R.string.mainmenu_image,0);
            case 2:
                return new MenuBean(2, R.string.mainmenu_healthy,0);
            case 1:
                return  new MenuBean(1,R.string.mainmenu_image,0);
            case 0:
            default:
                return new MenuBean(0, R.string.mainmenu_healthy,0);
        }
    }

    public MenuBean(int id, int titleId, int iconId) {
        this.id = id;
        this.titleId = titleId;
        this.iconId = iconId;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
}
