package com.gwamcc.aii.util.sql2.anno;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 左连接
 * @author 范大德
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Left{
    //连接表名
    public String table();
    //连接字段
    public String on();
    //查询字段
    public String[]fields();
    //连接顺序
    public int seq() default 1;
}