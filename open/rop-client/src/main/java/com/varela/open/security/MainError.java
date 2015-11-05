/**
 * 日    期：12-2-11
 */
package com.varela.open.security;

import java.util.List;


public interface MainError {

    String getCode();

    String getMessage();

    String getSolution();

    List<SubError> getSubErrors();

    MainError addSubError(SubError subError);

}

