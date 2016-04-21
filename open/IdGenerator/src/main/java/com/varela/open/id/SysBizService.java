/**
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package com.varela.open.id;

/**
 * 获取系统业务编号服务，<br>
 * 为了区别不同的系统业务处理 如果需要严格控制不同系统间不重合，设置此值。 <br>
 * 如果在一个系统中,也可以设置不同的模块等。 <br>
 * 取值会压缩至区间在0-127<br>
 */
public interface SysBizService {

    /**
     * 获取生成唯一编号的业务编号<br>
     * : 一般是获取
     */
    public long getBiz();
}
