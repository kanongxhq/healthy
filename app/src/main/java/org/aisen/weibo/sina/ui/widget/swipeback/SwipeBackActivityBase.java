package org.aisen.weibo.sina.ui.widget.swipeback;

/**
 * @author Yrom
 */
public interface SwipeBackActivityBase {
    /**
     * @return the SwipeBackLayout associated with this com.xhq.ui.activity.
     */
    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the com.xhq.ui.activity
     */
    public abstract void scrollToFinishActivity();

}
