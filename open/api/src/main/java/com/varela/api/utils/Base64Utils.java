package com.varela.api.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by lance on 12/16/2015.
 */
public class Base64Utils {

    public static String encodeStr(String plainText){
        byte[] b=plainText.getBytes();
        Base64 base64=new Base64();
        b=base64.encode(b);
        String s=new String(b);
        return s;
    }
}
