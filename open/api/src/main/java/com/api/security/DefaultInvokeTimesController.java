package com.api.security;

import com.api.service.api.DeveloperApiService;
import com.api.utils.APIRedisKey;
import com.varela.cache.RedisCache;
import com.varela.cache.RedisKey;
import com.varela.utils.StringCommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lance on 12/24/2015.
 */
@Component
public class DefaultInvokeTimesController implements InvokeTimesController {

    @Autowired
    private DeveloperApiService developerApiService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public boolean checkMethod(String appKey, String method) {
        return developerApiService.query(appKey, method);
    }

    @Override
    public void caculateInvokeTimes(String appKey, String method) {
        //1.接入者1分钟调用频率限制
        String limitKey = APIRedisKey.getApiLimit(appKey);
        this.redisCache.incre(limitKey, 1);
        this.redisCache.expire(limitKey, RedisKey.REDIS_1M_EXPIRING);

        String limitMethodKey = APIRedisKey.getApiMethodLimit(appKey, method);

    }

    @Override
    public boolean isAppInvokeLimitExceed(String appKey, String method) {
        return false;
    }

    @Override
    public boolean isAppInvokeFrequencyExceed(String appKey) {
        String limitKey = APIRedisKey.getApiLimit(appKey);
        long count = StringCommonUtils.getSafeLong(this.redisCache.get(limitKey));
        if (count > APIRedisKey.MINUTES_COUNT) {
            return false;
        }
        return true;
    }
}
