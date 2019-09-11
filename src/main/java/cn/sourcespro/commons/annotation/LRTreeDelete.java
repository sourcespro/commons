package cn.sourcespro.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 左右值树删除注解
 *
 * @author zhanghaowei
 * @since 2018/7/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LRTreeDelete {

    String tableName() default "";

}
