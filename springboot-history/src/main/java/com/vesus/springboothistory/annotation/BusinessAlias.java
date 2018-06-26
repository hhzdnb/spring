package com.vesus.springboothistory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:实体别名
 * @Author: vesus
 * @CreateDate: 2018/6/25 上午11:44
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BusinessAlias {
    public abstract String alias() ;
}
