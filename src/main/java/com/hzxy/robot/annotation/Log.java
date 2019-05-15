package com.hzxy.robot.annotation;

import java.lang.annotation.*;

/**
 * 自定义操作日子记录注解
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    String description()  default "";
    String type() default "b";
}
