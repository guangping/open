package com.varela.open.event;

import com.varela.open.RopContext;

/**
 * <pre>
 *   在Rop框架初始化后产生的事件
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class AfterStartedRopEvent extends RopEvent {

    public AfterStartedRopEvent(Object source, RopContext ropContext) {
        super(source, ropContext);
    }

}

