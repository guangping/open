package com.varela.open.params.base;

import com.varela.open.AbstractRopRequest;
import com.varela.open.RopResponse;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-27 16:59
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseRopRequest<T extends RopResponse> extends AbstractRopRequest {

    /*
  * 调用服务编码
  * **/
    public abstract String getApiMethodName();

    /*
    * 初始化检测
    * **/
    public void check() {

    }
}
