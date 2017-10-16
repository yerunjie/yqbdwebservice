package com.yqbd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
}
