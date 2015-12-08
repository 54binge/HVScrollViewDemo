package com.binge.hvscrollviewdemo.bean;

import java.util.List;

/**
 * Created by xzz64 on 2015/12/4.
 */
public class CarInfo {
    private String attrName;
    private List<Info> infoList;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }
}
