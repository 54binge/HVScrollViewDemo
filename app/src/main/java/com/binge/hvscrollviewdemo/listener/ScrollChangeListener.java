package com.binge.hvscrollviewdemo.listener;

import com.binge.hvscrollviewdemo.view.MHorizontalScrollView;

/**
 * Created by xzz64 on 2015/12/8.
 */
public class ScrollChangeListener implements OnScrollChangeListener {

    private MHorizontalScrollView view;

    public ScrollChangeListener(MHorizontalScrollView view) {
        this.view = view;
    }

    @Override
    public void onScrollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        view.smoothScrollTo(scrollX, scrollY);
    }
}
