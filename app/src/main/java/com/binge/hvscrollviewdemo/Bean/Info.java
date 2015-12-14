package com.binge.hvscrollviewdemo.bean;

import java.util.List;

/**
 * Created by xzz64 on 2015/12/4.
 */
public class Info {
    private String carName;
    private String keyName;
    private List<ValueInfo> infoValue;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public List<ValueInfo> getInfoValue() {
        return infoValue;
    }

    public void setInfoValue(List<ValueInfo> infoValue) {
        this.infoValue = infoValue;
    }
}
