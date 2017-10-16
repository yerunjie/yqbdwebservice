package com.yqbd.beans;

/**
 * Created by 11022 on 2017/6/29.
 */
public class BaseBean {
    private String singleResult;

    public BaseBean() {
        singleResult = "";
    }

    public BaseBean(Object singleResult) {
        this.singleResult = singleResult.toString();
    }

    public String getSingleResult() {
        return singleResult;
    }

    public void setSingleResult(String singleResult) {
        this.singleResult = singleResult;
    }
}
