package com.demo.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Activity {

    public String activityName() default "";
    public boolean isActive() default true;

    /**
     * Its internal name for mapping with @Activity
     * value format = ClassName_MethodName
     * example = com.scaffold.accounts.controller.UserHandler_verify
     * @return
     */
    public String handlerMethodName() default "";

}

