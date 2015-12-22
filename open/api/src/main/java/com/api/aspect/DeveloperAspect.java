package com.api.aspect;

import com.api.entity.Developer;
import com.api.utils.APIRedisKey;
import com.varela.cache.RedisCache;
import com.varela.cache.RedisKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lance on 12/22/2015.
 */
@Aspect
@Component
public class DeveloperAspect {

    @Autowired
    private RedisCache redisCache;

    @Pointcut("target(com.api.service.api.DeveloperService)")
    public void pointCut() {
    }

    @Pointcut("execution(* queryObj(..))")
    private void queryObj() {
    }

    @Around("pointCut() && queryObj()")
    public Object aroundAdviceAll(ProceedingJoinPoint pjp) throws Throwable {
        Object arg = pjp.getArgs().length >= 1 ? pjp.getArgs()[0] : null;
        String key = null;
        if (arg instanceof String) {
            key = APIRedisKey.getDeveloperAppKey(String.valueOf(arg));
        }
        if (arg instanceof Long) {
            key = APIRedisKey.getDeveloperKey(String.valueOf(arg));
        }
        Object obj = this.redisCache.getObj(key, Developer.class);
        if (null != obj) {
            return obj;
        }
        obj = pjp.proceed();
        if (null != obj) {
            this.redisCache.set(key, obj, RedisKey.REDIS_1D_EXPIRING);
        }
        return obj;
    }


}
