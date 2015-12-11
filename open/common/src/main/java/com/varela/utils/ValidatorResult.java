package com.varela.utils;

import com.varela.enumerate.APIMsg;
import com.varela.pojo.APIResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * Created by lance on 12/11/2015.
 */
public class ValidatorResult {

    public static APIResult handle(BindingResult result) {
        APIResult apiResult = new APIResult();

        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            ObjectError oe = list.get(0);
            apiResult.setMessage(oe.getDefaultMessage());
        } else {
            apiResult.setMsg(APIMsg.Success);
        }
        return apiResult;
    }
}
