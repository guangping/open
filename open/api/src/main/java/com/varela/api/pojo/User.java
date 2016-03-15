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


    @Pattern(regexp = "^1\\d{10}$", message = "{message.content.pattern}")
    private String mobile;

    @NotEmpty(message="参数{0}不能为空")
    private String email;

    @DateTimeFormat(pattern = DatePattern.YYYY_MM_DD_HH_MM_SS)
    private Date createTime;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
