package cn.sourcespro.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * lr-tree
 *
 * @author zhanghaowei
 * @date 2018/7/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LRTreeAdd {

    String tableName() default "";

}
