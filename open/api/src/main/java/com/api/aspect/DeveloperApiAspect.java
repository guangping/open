package com.api.aspect;

import com.api.entity.DeveloperApi;
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
public class DeveloperApiAspect {

    private Logger logger = LoggerFactory.getLogger(DeveloperApiAspect.class);

    @Autowired
    private RedisCache redisCache;

    @Pointcut("target(com.api.service.api.DeveloperApiService)")
    public void pointCut() {
    }

    @Pointcut("execution(* queryObj(long ,long ))")
    private void queryObj() {
    }


    @Around("pointCut() && queryObj()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object params[] = pjp.getArgs();
        Object obj = null;
        if (params.length == 2) {
            String key = APIRedisKey.getDeveloperidAppidKey(String.valueOf(params[0]), String.valueOf(params[1]));
            obj = this.redisCache.getObj(key, DeveloperApi.class);
            if (null != obj) {
                return obj;
            }
            obj = pjp.proceed();
            if (null != obj) {
                this.redisCache.set(key, obj, RedisKey.REDIS_1D_EXPIRING);
            }
        }
        return obj;
    }

}
