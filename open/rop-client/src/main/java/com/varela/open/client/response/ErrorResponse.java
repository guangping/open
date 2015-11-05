package com.varela.open.client.response;

import com.varela.open.Constants;
import com.varela.open.security.MainError;
import com.varela.open.security.SubError;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: guangping
 * Date: 2014-07-01 14:25
 * To change this template use File | Settings | File Templates.
 */
public class ErrorResponse implements Serializable {

    protected String errorToken = Constants.ERROR_TOKEN;

    protected String code;

    protected String message;


    protected String solution;


    protected List<SubError> subErrors;

    public ErrorResponse() {
    }

    public ErrorResponse(MainError mainError) {
        this.code = mainError.getCode();
        this.message = mainError.getMessage();
        this.solution = mainError.getSolution();
        if (mainError.getSubErrors() != null && mainError.getSubErrors().size() > 0) {
            this.subErrors = mainError.getSubErrors();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public List<SubError> getSubErrors() {
        return subErrors;
    }

    public void setSubErrors(List<SubError> subErrors) {
        this.subErrors = subErrors;
    }


    public String getErrorToken() {
        return errorToken;
    }

    /**
     * 对服务名进行标准化处理：如book.upload转换为book-upload，
     *
     * @param method
     * @return
     */
    protected String transform(String method) {
        if (method != null) {
            method = method.replace(".", "-");
            return method;
        } else {
            return "LACK_METHOD";
        }
    }

}
