package com.varela.open;

import com.varela.open.annotation.Temporary;

/**
 * <pre>
 *   所有请求对象应该通过扩展此抽象类实现
 * </pre>
 */
public abstract class AbstractRopRequest implements RopRequest {

    @Temporary
    private RopRequestContext ropRequestContext;


    public RopRequestContext getRopRequestContext() {
        return ropRequestContext;
    }

    public final void setRopRequestContext(RopRequestContext ropRequestContext) {
        this.ropRequestContext = ropRequestContext;
    }


}

