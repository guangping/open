package com.api.pojo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by lance on 12/11/2015.
 */
public class User implements Serializable {


    @Pattern(regexp = "^1\\d{10}$", message = "{message.content.mobile}")
    private String mobile;

    private String email;


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
