package com.varela.open.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;


public class SimpleRopEventMulticaster extends AbstractRopEventMulticaster {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Executor executor;


    public void multicastEvent(final RopEvent event) {
        try {
            for (final RopEventListener listener : getRopEventListeners(event)) {
                Executor executor = getExecutor();
                if (executor != null) {
                    executor.execute(new Runnable() {

                        public void run() {
                            listener.onRopEvent(event);
                        }
                    });
                } else {
                    listener.onRopEvent(event);
                }
            }
        } catch (Exception e) {
            logger.error("处理" + event.getClass().getName() + "事件发生异常", e);
        }
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}

