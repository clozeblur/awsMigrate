package com.clozeblur.re.aws.migrate.annotation;

/**
 * Created by yuanjx
 * on 2017/12/5
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定时任务开关,用于控制任务是否可执行
 * @author guofei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TaskOnOff {

    /**
     * 可运行的IP值 ================>为方便使用,这里只允许单ip
     * 为空表示所有IP都可运行  默认为空
     * @return
     */
    String canRunIp() default "";
}
