package com.api.security;

import com.api.service.api.DeveloperApiService;
import com.api.utils.APIRedisKey;
import com.varela.cache.RedisCache;
import com.varela.cache.RedisKey;
import com.varela.utils.StringCommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

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

        //2.具体某个服务的调用限制 1天
        String limitMethodKey = APIRedisKey.getApiMethodLimit(appKey, method);
        this.redisCache.incre(limitMethodKey, 1);


        //3.调用记录入库

        
    }

    @Override
    public boolean isAppInvokeLimitExceed(String appKey, String method) {
        return false;
    }

    @Override
    public boolean isAppInvokeFrequencyExceed(String appKey) {
        String limitKey = APIRedisKey.getApiLimit(appKey);
        long count = StringCommonUtils.getSafeLong(this.redisCache.get(limitKey));
        //获取接入者1分钟内可调用次数
        if (count > APIRedisKey.MINUTES_COUNT) {
            return false;
        }
        return true;
    }

    //获取现在到凌晨的时间差
    private long getTime() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }
}
