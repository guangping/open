package com.varela;

import org.testng.annotations.Test;

import java.text.MessageFormat;

/**
 * Created by lance on 12/11/2015.
 */
public class MsgTest {


    @Test
    public void run(){
        String str="ddddd{0}";
        System.out.println(MessageFormat.format(str,"0"));
    }
}
