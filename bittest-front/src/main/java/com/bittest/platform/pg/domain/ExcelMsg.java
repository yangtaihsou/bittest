package com.bittest.platform.pg.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelMsg {

    // 标示excel中当前值所在列
    public int index() default 0;

    // 标示excel中当前值是否必须
    public boolean require() default false;

    // 标示excel中当前列的表头
    public String header() default "";

    // 标示excel中当前列的宽度
    public int width() default 12;

    // 标示excel模板中的默认值
    public String template() default "";

    //是否需要校验
    public boolean validate() default false;
}
