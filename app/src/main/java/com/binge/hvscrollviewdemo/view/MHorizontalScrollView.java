package com.binge.hvscrollviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

import com.binge.hvscrollviewdemo.observer.ScrollChangeObserver;

/**
 * Created by xzz64 on 2015/12/8.
 */
public class MHorizontalScrollView extends HorizontalScrollView {

    /**
     * 必需静态
     */
    private static ScrollChangeObserver observer = new ScrollChangeObserver();

    public MHorizontalScrollView(Context context) {
        super(context);
    }

    public MHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*public MHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }*/

    public void addScrollChangeListener(com.binge.hvscrollviewdemo.listener.OnScrollChangeListener listener){
        observer.addScrollChangeListener(listener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (observer != null) {
            observer.notifyToScroll(l, t, oldl, oldt);
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
