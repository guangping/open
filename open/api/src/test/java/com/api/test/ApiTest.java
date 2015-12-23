package com.api.test;

import com.api.utils.APIMD5Utils;
import com.varela.utils.RandomUtil;
import org.apache.commons.codec.binary.Base64;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by lance on 12/10/2015.
 */
public class ApiTest {

    @Test
    public void sign(){
        String randomStr=RandomUtil.getRandomStr();
        System.out.println(APIMD5Utils.sign(randomStr));
    }

    @Test
    public void run()throws UnsupportedEncodingException {
        String randomStr=RandomUtil.getRandomStr();

        byte[] b=randomStr.getBytes();
        Base64 base64=new Base64();
        b=base64.encode(b);
        String s=new String(b);
        System.out.println("s==:"+s);

        b=randomStr.getBytes("UTF-8");
        base64=new Base64();
        b=base64.encode(b);
        s=new String(b);
        System.out.println("s==:"+s);

        System.out.println("密钥串:"+randomStr);
    }
}
