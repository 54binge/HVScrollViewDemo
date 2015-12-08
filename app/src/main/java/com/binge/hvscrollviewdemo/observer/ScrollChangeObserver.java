package com.binge.hvscrollviewdemo.observer;

import com.binge.hvscrollviewdemo.listener.OnScrollChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzz64 on 2015/12/8.
 */
public class ScrollChangeObserver {
    private List<OnScrollChangeListener> list;

    public ScrollChangeObserver() {
        list = new ArrayList<>();
    }

    public void addScrollChangeListener(OnScrollChangeListener listener) {
        list.add(listener);
    }

    public void removeScrollChangeListener(OnScrollChangeListener listener) {
        list.remove(listener);
    }

    public void notifyToScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (list != null && !list.isEmpty()) {
            for (OnScrollChangeListener listener : list) {
                listener.onScrollChange(scrollX, scrollY, oldScrollX, oldScrollY);
            }
        }
    }
}
