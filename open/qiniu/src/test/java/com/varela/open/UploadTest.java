package com.varela.open;

import com.varela.open.upload.QiniuUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by lance on 2016/4/26.
 */
@ContextConfiguration({
        "classpath:applicationContext-test.xml"
})
public class UploadTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private QiniuUploadService uploadService;

    @Test
    public void upload() {
        String fileName=this.uploadService.getFileName();
        boolean sign=this.uploadService.upload(new File("C:\\Users\\51offer\\Desktop\\Unnamed QQ Screenshot20160426111850.png"),fileName);
        System.out.println(sign+":"+this.uploadService.getFileUrl(fileName));
    }


}
