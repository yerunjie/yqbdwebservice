package com.yqbd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11022 on 2017/7/21.
 */
@Controller
public class UtilsController extends BaseController{
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam("fileToUpload") MultipartFile file) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (!file.isEmpty()) {
            try {
                //System.out.println(file.getOriginalFilename());
                //相对路径
                String fileRelativePath = "/images/";
                // 文件保存路径
                String filePath = request.getSession().getServletContext().getRealPath(fileRelativePath);
                File f = new File(filePath);
                //若目录不存在，则创建
                if (!f.exists()) {
                    f.mkdirs();
                }
                System.out.println(filePath);
                // 转存文件
                String fileName = new Date().getTime() + file.getOriginalFilename();
                file.transferTo(new File(filePath, fileName));
                fileRelativePath +=  fileName;
                result.put("message", "uploadSuccess");
                result.put("file", fileRelativePath);
                //momentService.postPicture(momentId,fileRelativePath.substring(1));
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.put("message", "uploadError");
        return result;
    }
}
