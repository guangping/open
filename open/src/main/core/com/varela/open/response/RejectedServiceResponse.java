package com.varela.open.response;

import com.varela.open.security.MainError;
import com.varela.open.security.MainErrorType;
import com.varela.open.security.MainErrors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Locale;

/**
 * <pre>
 *   当服务平台资源耗尽（超过最大线程数且列队排满后）
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class RejectedServiceResponse extends ErrorResponse {

    public RejectedServiceResponse() {
    }

    public RejectedServiceResponse(Locale locale) {
        MainError mainError = MainErrors.getError(MainErrorType.FORBIDDEN_REQUEST, locale);
        setMainError(mainError);
    }
}

