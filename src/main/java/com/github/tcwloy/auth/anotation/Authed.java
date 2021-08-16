package com.github.tcwloy.auth.anotation;

import java.lang.annotation.*;

/**
 * 权限码验证注解
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authed {
    /**
     * 权限code
     * @return
     */
    String value() default "";
}
