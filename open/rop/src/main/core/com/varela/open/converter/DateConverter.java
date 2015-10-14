package com.varela.open.converter;

import com.varela.open.request.RopConverter;

import java.util.Date;

/**
 * @author : chenxh(quickselect@163.com)
 * @date: 14-3-18
 */
public class DateConverter implements RopConverter<String, Date> {


    public Date convert(String s) {
        return DateUtils.parseDate(s);
    }


    public String unconvert(Date date) {
        return DateUtils.format(date, DateUtils.DATETIME_FORMAT);
    }


    public Class<String> getSourceClass() {
        return String.class;
    }


    public Class<Date> getTargetClass() {
        return Date.class;
    }
}
