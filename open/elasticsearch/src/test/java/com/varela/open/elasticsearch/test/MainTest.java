package com.varela.open.elasticsearch.test;

import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.testng.annotations.Test;

import java.net.InetAddress;

/**
 * Created by lance on 2016/2/16.
 */
public class MainTest {

    @Test
    public void run(){
        InetAddress inetAddress=InetAddress.getLoopbackAddress();
        System.out.println(inetAddress);

       try {
           inetAddress=InetAddress.getLocalHost();
           System.out.println(inetAddress);


       }catch (Exception e){}

    }
}
