package com.varela.api.pojo;

import com.varela.utils.date.DatePattern;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lance on 12/11/2015.
 */
public class User implements Serializable {



    private String mobile;


    private String email;

    @DateTimeFormat(pattern = DatePattern.YYYY_MM_DD_HH_MM_SS)
    private Date createTime;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 分情况验证不同的字段
     * groups区分
     * */
    @Pattern(regexp = "^1\\d{10}$", message = "{message.content.pattern}",groups = {Query.class})
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @NotEmpty(message="参数{0}不能为空")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
