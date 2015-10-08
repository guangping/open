package com.varela.open;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-06-27 16:47
 * To change this template use File | Settings | File Templates.
 */
public interface RopResponse extends Serializable {

    /*
    * 判断结果
    * */
    public boolean isResult();


    /*
    * 返回编码
    * */
    public String getCode();


    /*
    *描述信息
    * */
    public String getMsg();
}
