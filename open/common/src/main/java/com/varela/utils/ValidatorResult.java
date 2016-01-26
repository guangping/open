package com.varela.utils;

import com.varela.enumerate.Msg;
import com.varela.pojo.APIResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.text.MessageFormat;
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
            String fieldName = null;
            if (oe instanceof FieldError) {
                FieldError fieldError = (FieldError) oe;
                fieldName = fieldError.getField();
            }
            apiResult.setMessage(MessageFormat.format(oe.getDefaultMessage(), fieldName));
        } else {
            apiResult.setMsg(Msg.SUCCESS);
        }
        return apiResult;
    }
}
