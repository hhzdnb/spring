package com.vesus.springboothistory.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

/**
 * @Description:
 * @Author: vesus
 * @CreateDate: 2018/6/26 下午2:05
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(TYPE)
public @interface TableName {
    public abstract String name();
}
