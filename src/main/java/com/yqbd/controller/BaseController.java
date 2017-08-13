package com.yqbd.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 11022 on 2017/7/21.
 */
public class BaseController {
    @Autowired
    protected HttpServletRequest request;
}
