package com.kaloudasdev.cooldown.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cooldown {
    long duration();
    TimeUnit unit() default TimeUnit.SECONDS;
    String message() default "&cPlease wait %time% seconds before using this command again.";
    String bypassPermission() default "";
}
