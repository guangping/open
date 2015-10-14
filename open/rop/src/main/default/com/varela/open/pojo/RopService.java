package com.varela.open.pojo;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-07-02 14:03
 * To change this template use File | Settings | File Templates.
 */
public class RopService implements Serializable {


    private String id;
    /**
     * API的方法
     */
    private String method;

    /**
     * API的方法的标识
     */
    private String methodTitle;

    /**
     * API方法组名的标识
     */
    private String methodGroupTitle;

    /**
     * API方法所属组名
     */
    private String methodGroup = "DEFAULT";

    /**
     * 对应的版本号，如果为null或""表示不区分版本
     */
    private String version = null;

    /**
     * 是否需要进行会话校验
     */
    private String needInSession;

    /**
     * 服务方法是否已经过期
     */
    private String obsoleted = String.valueOf(false);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodTitle() {
        return methodTitle;
    }

    public void setMethodTitle(String methodTitle) {
        this.methodTitle = methodTitle;
    }

    public String getMethodGroupTitle() {
        return methodGroupTitle;
    }

    public void setMethodGroupTitle(String methodGroupTitle) {
        this.methodGroupTitle = methodGroupTitle;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNeedInSession() {
        return needInSession;
    }

    public void setNeedInSession(String needInSession) {
        this.needInSession = needInSession;
    }

    public String getObsoleted() {
        return obsoleted;
    }

    public void setObsoleted(String obsoleted) {
        this.obsoleted = obsoleted;
    }

    public String getMethodGroup() {
        return methodGroup;
    }

    public void setMethodGroup(String methodGroup) {
        this.methodGroup = methodGroup;
    }
}
