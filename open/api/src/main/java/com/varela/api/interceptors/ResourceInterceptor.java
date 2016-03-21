package com.varela.api.interceptors;

import com.varela.api.pojo.APIKey;
import com.varela.api.utils.APIRedisKey;
import com.varela.cache.RedisCache;
import com.varela.cache.RedisKey;
import com.varela.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lance on 2016/1/25.
 */
public class ResourceInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ResourceInterceptor.class);

    @Autowired
    private RedisCache redisCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("ctx", request.getContextPath());
        //
        this.execute(request);
        return super.preHandle(request, response, handler);
    }

    /**
     * 1、限制单个ip/api token的访问量，比如15分钟限制访问页面180次，具体标准可参考一些大型网站的公开api，如twitter api，对于抓取用户公开信息的爬虫要格外敏感。
     * <p>
     * 2、蜜罐资源。爬虫解析离不开正则匹配，适当在页面添加一些正常浏览器浏览访问不到的资源，一旦有ip访问，过滤下头部是不是搜素引擎的蜘蛛，不是就可以直接封了。
     * <p>
     * 3、定期分析日志。系统分析的效果肯定要强过过滤单条日志，比如装一个awstat之类的专门分析web服务器日志的应用。
     */
    private void execute(HttpServletRequest request) {
        String ip = WebUtils.getIpAddr(request);
        String ipKey = APIRedisKey.getIpCount(ip);
        long ipCount=this.redisCache.incre(ipKey, 1);
        this.redisCache.expire(ip, RedisKey.REDIS_1M_EXPIRING);
        if(ipCount> APIKey.IP_COUNT){
            logger.info("ip:{},一分钟内访问:{}次",ip,ipCount);
        }
    }

}
