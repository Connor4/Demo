package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 声明注解在什么地方用的，作用域
@Retention(RetentionPolicy.SOURCE) // 声明注解的生命周期
public @interface BindView {
    int value();
}
