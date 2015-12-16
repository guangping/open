package com.varela.aspect;

import com.varela.log.annotation.MethodLog;
import com.varela.log.pojo.MethodLogExplain;
import com.varela.log.pojo.RecordLog;
import com.varela.utils.date.DatePattern;
import org.apache.commons.lang3.time.FastDateFormat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by lance on 11/9/2015.
 * 日志处理
 */
@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.varela.log.annotation.MethodLog)")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String datetime = FastDateFormat.getInstance(DatePattern.YYYYMMDDHHMMSSSSS).format(System.currentTimeMillis());
        String methodName = point.getSignature().getName();
        String packages = point.getThis().getClass().getName();
        MethodLogExplain explain = getMethodLog(point);
        Object args[] = point.getArgs();

        RecordLog recordLog = new RecordLog();
        recordLog.setCreateTime(new Date());
        recordLog.setId("");
        recordLog.setUserName("");
        recordLog.setEvent(explain.getEvent());

        logger.info("方法:{},包:{},参数:{},描述信息:{}", methodName, packages, args, explain);

        return point.proceed();
    }

    /**
     * 获取方法的中文备注____用于记录用户的操作日志描述
     */
    public static MethodLogExplain getMethodLog(ProceedingJoinPoint joinPoint)
            throws Exception {
        MethodLogExplain operation = new MethodLogExplain();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();

        Class targetClass = Class.forName(targetName);
        Method[] method = targetClass.getMethods();

        for (Method m : method) {
            if (m.getName().equals(methodName)) {
                Class[] tmpCs = m.getParameterTypes();
                if (tmpCs.length == arguments.length) {
                    MethodLog methodCache = m.getAnnotation(MethodLog.class);
                    if (null != methodCache) {
                        operation.setEvent(methodCache.event());
                        operation.setParamClazz(methodCache.paramClazz());
                    }
                    break;
                }
            }
        }
        return operation;
    }

}
