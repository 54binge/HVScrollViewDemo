package com.binge.hvscrollviewdemo.bean;

import java.util.List;

/**
 * Created by xzz64 on 2015/12/4.
 */
public class Info {
    private String carName;
    private String keyName;
    private List<String> infoValue;

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

    public List<String> getInfoValue() {
        return infoValue;
    }

    public void setInfoValue(List<String> infoValue) {
        this.infoValue = infoValue;
    }
}
