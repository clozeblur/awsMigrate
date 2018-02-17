package com.clozeblur.re.aws.migrate.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;

/**
 * Created by yuanjx
 * on 2017/12/5
 */
public class BaseAspect implements Ordered {

    protected Logger log = LoggerFactory.getLogger(getClass());

    protected int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * 获取方法执行结果
     * @param jp
     * @return 方法执行结果
     * @throws Throwable
     */
    protected Object getResult(ProceedingJoinPoint jp) throws Throwable{
        Object result;
        Object[] args = jp.getArgs();
        if(args == null || args.length == 0) {
            result = jp.proceed();
        } else {
            result = jp.proceed(args);
        }
        return result;
    }

}
