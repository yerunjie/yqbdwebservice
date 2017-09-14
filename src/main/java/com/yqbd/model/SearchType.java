package com.yqbd.model;

import java.util.List;

/**
 * Created by 佳乐 on 2017/7/31.
 */
public class SearchType {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    private String name;
    private List<Type> types;
}
