package com.vesus.springboothistory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:标识属性不记录
 * @Author: vesus
 * @CreateDate: 2018/6/25 上午11:47
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BusinessNotRecord {
}
