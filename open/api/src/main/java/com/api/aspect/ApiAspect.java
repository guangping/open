package com.api.aspect;

import com.api.entity.API;
import com.api.utils.APIRedisKey;
import com.varela.cache.RedisCache;
import com.varela.cache.RedisKey;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lance on 12/22/2015.
 */
@Aspect
@Component
public class ApiAspect {

    private Logger logger = LoggerFactory.getLogger(ApiAspect.class);

    @Autowired
    private RedisCache redisCache;

    @Pointcut("target(com.api.service.api.APIService)")
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
            key = APIRedisKey.getApiMethodKey(String.valueOf(arg));
        }
        if (arg instanceof Long) {
            key = APIRedisKey.getApiKey(String.valueOf(arg));
        }
        Object obj = this.redisCache.getObj(key, API.class);
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
