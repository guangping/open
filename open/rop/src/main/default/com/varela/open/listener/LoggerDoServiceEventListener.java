package com.varela.open.listener;

import com.varela.open.RopRequestContext;
import com.varela.open.event.AfterDoServiceEvent;
import com.varela.open.event.RopEventListener;
import com.varela.open.pojo.RopLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 *          <p/>
 *          记录调用日志
 */
public class LoggerDoServiceEventListener implements RopEventListener<AfterDoServiceEvent> {

    private static Logger log = LoggerFactory.getLogger(LoggerDoServiceEventListener.class);

    private Queue<RopLogger> queue = null;
    private Timer timer = null;

    {
        queue = new LinkedBlockingQueue<RopLogger>();
        log.debug("日志队列的大小:{}", queue.size());
        timer = new Timer(true);
        for (int i = 0; i < 10; i++) {
            timer.schedule(new RunnableLogger(), 30000, 1000);
        }
    }


    public void onRopEvent(AfterDoServiceEvent ropEvent) {
        RopRequestContext ropRequestContext = ropEvent.getRopRequestContext();

        if (ropRequestContext != null) {
            RopLogger logger = new RopLogger();
            logger.setAppKey(ropRequestContext.getAppKey());
            logger.setMethod(ropRequestContext.getMethod());
            logger.setIp(ropRequestContext.getIp());
            logger.setServiceBeginTime(new Date(ropRequestContext.getServiceBeginTime()));
            logger.setServiceEndTime(new Date(ropRequestContext.getServiceEndTime()));
            logger.setVersion(ropRequestContext.getVersion());
            logger.setSessionId(ropRequestContext.getSessionId());
            /*
            * 记录调用日志
            * */
            queue.offer(logger);
            /*
            *记录调用次数(总调用次数、某个方法的调用次数)
            * **/


        }
    }


    private class RunnableLogger extends TimerTask {

        private int Max = 1000;

        private RunnableLogger() {
        }


        public void run() {

        }
    }


    public int getOrder() {
        return 0;
    }

}

