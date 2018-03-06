package cn.cust.netty.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangbing on 2018/3/6.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface Action {
    String value() default "";
}
