package com.github.tcwloy.auth.support.interceptor;


import com.github.tcwloy.auth.anotation.Authed;
import com.github.tcwloy.auth.model.AuthConstant;
import com.github.tcwloy.auth.model.pojo.UserAuthorityInfo;
import com.github.tcwloy.auth.service.IUserAuthCache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限验证及用户信息填充
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserAuthCache userAuthCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前方法是否启用认证
        Authed annotation = getAuthedAnnotation(handler);
        //获取token
        String token = getToken(request);
        if (null != annotation) {
            //无token表示未登陆
            if (StringUtils.isEmpty(token)) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return false;
            } else {
                UserAuthorityInfo userAuthorityInfo = userAuthCache.getUser(token);
                if (null == userAuthorityInfo) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return false;
                } else {
                    log.info("authority code is {}, user is {}", annotation.value(), userAuthorityInfo);
                    boolean forbidden = !StringUtils.isEmpty(annotation.value()) && !userAuthorityInfo.getAuthorities().contains(annotation.value());
                    if (forbidden) {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        return false;
                    } else {
                        request.setAttribute(AuthConstant.ATTRIBUTE_USER, userAuthorityInfo);
                        return true;
                    }
                }
            }
        } else {
            if (StringUtils.isNotBlank(token)) {
                UserAuthorityInfo user = userAuthCache.getUser(token);
                if (null != user) {
                    request.setAttribute(AuthConstant.ATTRIBUTE_USER, user);
                }
            }
            return true;
        }
    }

    private Authed getAuthedAnnotation(Object handler) {
        Authed annotation = null;
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            annotation = handlerMethod.getMethodAnnotation(Authed.class);
            if (annotation == null) {
                annotation = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Authed.class);
            }
        }
        return annotation;
    }

    private String getToken(HttpServletRequest request) {
        //todo 解析token
        return null;
    }

}
