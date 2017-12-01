package com.yqbd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by 11022 on 2017/7/21.
 */
@Controller
public class BaseController {
    @Autowired
    protected HttpServletRequest request;

    public static final String ROOT = "image";


    public int getCurrentCompanyId(){
        HttpSession session = request.getSession();
        int companyId = (int)session.getAttribute("companyId");
        return companyId;
    }

    public int getTaskId(){
        HttpSession session = request.getSession();
        int taskId = (int)session.getAttribute("taskId");
        return taskId;
    }
}
