package com.clozeblur.re.aws.migrate.aop;

import com.clozeblur.re.aws.migrate.annotation.TaskOnOff;
// IPUtil架包引入
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

 /**
 * Created by yuanjx
 * on 2017/12/5 用于控制调度任务
 */
@Aspect
@Component
public class TaskDispatchAspect extends BaseAspect {

    private static Logger logger = LoggerFactory.getLogger(TaskDispatchAspect.class);

    /**
     * 自动拦截以
     */
    @Pointcut("@annotation(com.clozeblur.re.aws.migrate.annotation.TaskOnOff)")
    private void cutTaskOnOff() {}

    /**
     * Controller结尾或者注解都进行拦截
     * @param thisJoinPoint 切点
     * @return 返回结果
     * @throws Throwable
     */
    @Around("cutTaskOnOff()")
    public Object around(ProceedingJoinPoint thisJoinPoint) throws Throwable {

        Signature sig = thisJoinPoint.getSignature();
        Method m = ((MethodSignature)sig).getMethod();

        //检查可运行开关
        if(!checkCanRun(m.getAnnotation(TaskOnOff.class))){
            logger.debug("未达到运行条件["+sig.getDeclaringTypeName()+"]["+sig.getName()+"]");
            return null;
        }
        return getResult(thisJoinPoint);
    }

    /**
     * 检查定时任务是否满足执行要求
     * @param to 注解
     */
    private boolean checkCanRun(TaskOnOff to) {
        if (to == null) return true;
        String ips = to.canRunIp();
        return StringUtils.isEmpty(ips) || IPUtil.getLocalIpSet().contains(ips);
    }
}
