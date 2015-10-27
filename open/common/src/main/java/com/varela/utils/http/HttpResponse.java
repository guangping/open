package com.varela.utils.http;

import java.io.Serializable;

/**
 * Created by lance on 9/17/2015.
 */
public class HttpResponse implements Serializable {

    private static final long serialVersionUID = 6024295049070570089L;

    private boolean success = false;

    private String result;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "success=" + success +
                ", result='" + result + '\'' +
                '}';
    }
}
