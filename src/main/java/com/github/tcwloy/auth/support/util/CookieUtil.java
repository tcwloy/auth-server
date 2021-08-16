package com.github.tcwloy.auth.support.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie工具
 */
public class CookieUtil {
    public static final int COOKIE_MAX_AGE = 604800;
    public static final int COOKIE_HALF_HOUR = 7200;
    public static final int COOKIE_MAX_AGE_COWORKING = 2592000;

    private CookieUtil() {
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            Cookie cookie = null;
            int length = cookies.length;

            for (int i = 0; i < length; ++i) {
                Cookie c = cookies[i];
                if (name.equals(c.getName())) {
                    cookie = c;
                    try {
                        cookie.setValue(URLDecoder.decode(cookie.getValue(), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }

            return cookie;
        } else {
            return null;
        }
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        if (cookie != null) {
            try {
                return URLDecoder.decode(cookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException var4) {
                var4.printStackTrace();
            }
        }

        return null;
    }

    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        if (null != name) {
            Cookie cookie = getCookie(request, name);
            if (null != cookie) {
                cookie.setPath("/");
                cookie.setValue("");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    public static void setCookie(HttpServletResponse response, String name, String value, int maxValue) throws UnsupportedEncodingException {
        if (!StringUtils.isEmpty(name)) {
            if (null == value) {
                value = "";
            }
            value = URLEncoder.encode(value, "UTF-8");
            Cookie cookie = new Cookie(name, value);
            cookie.setPath("/");
            if (maxValue != 0) {
                cookie.setMaxAge(maxValue);
            } else {
                cookie.setMaxAge(7200);
            }
            response.addCookie(cookie);
        }
    }

    public static void setCookie(HttpServletResponse response, String name, String value) throws UnsupportedEncodingException {
        setCookie(response, name, value, 7200);
    }

    public static Map<String, Cookie> getCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            int length = cookies.length;

            for (int i = 0; i < length; ++i) {
                Cookie cookie = cookies[i];
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
