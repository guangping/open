package com.varela.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by lance on 11/5/2015.
 * aop拦截
 */
@Aspect
@Component
public class LevelAspect {

    private Logger logger = LoggerFactory.getLogger(LevelAspect.class);

   // @Pointcut("target(com.varela.service.api.bus.LevelService)")
    private void pointCut() {
    }

    @Pointcut("execution(* queryById(..))")
    private void queryById() {
    }


   // @Around(" pointCut() && queryById()")
    public Object aroundAdviceAll(ProceedingJoinPoint pjp) throws Throwable {
        Object arg = pjp.getArgs().length >= 1 ? pjp.getArgs()[0] : null;
        logger.info("aop拦截!{}", arg);

        return pjp.proceed();
    }
}
