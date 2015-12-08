package com.binge.hvscrollviewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by xzz64 on 2015/12/8.
 */
public class MLinearLayout extends LinearLayout {
    public MLinearLayout(Context context) {
        super(context);
    }

    public MLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
