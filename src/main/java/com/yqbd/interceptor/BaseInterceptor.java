package com.yqbd.interceptor;

import com.yqbd.dto.response.BaseJsonResponse;
import com.yqbd.constants.CommonConstants;
import com.yqbd.dto.Token;
import com.yqbd.service.TokenService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    protected TokenService tokenService;

    protected void makeResponse(Object controller, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        if (controller.getClass().isAnnotationPresent(RestController.class) ||
                controller.getClass().getSuperclass().isAnnotationPresent(RestController.class)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            BaseJsonResponse baseJsonResponse = new BaseJsonResponse();
            baseJsonResponse.setReturnCode("E.1");
            baseJsonResponse.setErrorMessage("没有权限");
            JSONObject jsonObject = JSONObject.fromObject(baseJsonResponse);
            httpServletResponse.setCharacterEncoding("UTF8");
            httpServletResponse.getWriter().println(jsonObject.toString());
        } else {
            httpServletResponse.sendRedirect("/company/login");
        }
    }

    protected String getTokenFromHeaderOrCookie(HttpServletRequest request, String key) {
        String header = getHeader(request, key);
        if (header != null) {
            return header;
        }
        String cookie = getCookie(request, key);
        return cookie;
    }

    protected String getHeader(HttpServletRequest request, String key) {
        return request.getHeader(key);
    }

    protected String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    protected Token getToken(HttpServletRequest request) {
        String tokenString = getTokenFromHeaderOrCookie(request, CommonConstants.TOKEN_KEY);
        if (tokenString == null) {
            return null;
        }
        try {
            return tokenService.parseToken(tokenString);
        } catch (RuntimeException e) {
            return null;
        }
    }
}
