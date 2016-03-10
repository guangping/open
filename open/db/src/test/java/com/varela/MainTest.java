package com.varela;

import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;

/**
 * Created by 51offer on 2016/3/7.
 */
public class MainTest {


    public static void main(String[] args) {
        MainTest mainTest=new MainTest();
        mainTest.run();
    }

    public void run() {
        try {
            FileInputStream inputStream = new FileInputStream(new File("F:\\other\\1.log"));
            int read = inputStream.read();
            System.out.println(read + ":" + inputStream);
        } catch (Exception e) {
            //System.out.println(e.getClass().getName());

            StackTraceElement stackTraceElement= e.getStackTrace()[0];
            if(null!=stackTraceElement){
                System.err.println("className:"+stackTraceElement.getClassName());
                System.err.println("Line:"+stackTraceElement.getLineNumber());// 打印出错行号
                System.err.println("Method:"+stackTraceElement.getMethodName());// 打印出错方法
            }


  /*          ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            String exception = baos.toString();*/
            // System.out.println("exception:" + exception);


/*            String regEx = "Caused by:(.*)";
            Pattern pat = Pattern.compile(regEx);
            Matcher mat = pat.matcher(exception);
            boolean rs = mat.find();
            System.out.println("found?" + rs);
            System.out.println(mat.group(1));*/


 /*           StackTraceElement[] st = e.getStackTrace();
            for (StackTraceElement stackTraceElement : st) {
                String exclass = stackTraceElement.getClassName();
                String method = stackTraceElement.getMethodName();
                System.out.println(exclass);
                System.out.println(method);
            }*/
            e.printStackTrace();
        }
    }
}
