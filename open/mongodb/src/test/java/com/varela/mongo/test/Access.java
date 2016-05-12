package com.varela.mongo.test;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lance on 12/9/2015.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Access implements Serializable {


    private long id;


    private String accessId;


    private String secret;


    private Date dateTime;


    private int status;


}
