package com.binge.hvscrollviewdemo.Bean;

/**
 * Created by xzz64 on 2015/12/4.
 */
public class Info {
    private String name;
    private String value;

    public Info(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
