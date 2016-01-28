package com.varela.open.batch.policy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

/**
 * Created by lance on 2016/1/28.
 *
 * 自定义skip策略
 */
public class MyPolicy implements SkipPolicy {
    private Logger logger= LoggerFactory.getLogger(MyPolicy.class);
    @Override
    public boolean shouldSkip(Throwable throwable, int i) throws SkipLimitExceededException {
        logger.error("错误次数:{},信息:{}",i,throwable.getMessage());
        return true;
    }
}
