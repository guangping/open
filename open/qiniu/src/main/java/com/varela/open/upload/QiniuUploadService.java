package com.varela.open.upload;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.varela.utils.RandomUtil;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Created by lance on 2016/1/27.
 * <p/>
 * 七牛文件上传
 */
@Component
public class QiniuUploadService {

    private Logger logger = LoggerFactory.getLogger(QiniuUploadService.class);


    private static Auth auth = null;
    private UploadManager uploadManager = null;

    @Value("${qn.domain}")
    private String domain;

    @Value("${qn.namespace}")
    private String namespace;

    @Value("${qn.access.key}")
    private String accessKey;

    @Value("${qn.secret.key}")
    private String secretKey;


    @PostConstruct
    public void init() {
        auth = Auth.create(accessKey, secretKey);
        uploadManager = new UploadManager();
    }

    // 简单上传，使用默认策略
    public String getUpToken() {
        return auth.uploadToken(namespace).trim();
    }

    /**
     * 获取文件名
     */
    public String getFileName() {
        FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy/MM/dd");
        FastDateFormat format = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");
        StringBuffer buffer = new StringBuffer(200);
        buffer.append(dateFormat.format(System.currentTimeMillis()));
        buffer.append("/");
        buffer.append(format.format(System.currentTimeMillis()));
        return buffer.toString();
    }

    /**
     * 获取文件地址
     */
    public String getFileUrl(String fileName) {
        StringBuilder builder = new StringBuilder(300);
        builder.append(domain);
        builder.append(fileName);
        return builder.toString();
    }


    /**
     * 删除指定文件
     *
     * @param fileName 要删除的文件名
     */
    public void deleteFile(String fileName) throws QiniuException {
        BucketManager buketManager = new BucketManager(auth);
        buketManager.delete(namespace, fileName);
    }

    /***
     * 直接上传缓存数据
     *
     * @param data 文件
     * @return filename
     * @throws Exception
     */
    public boolean upload(byte[] data, String upToken, String fileName) throws Exception {
        boolean result = false;
        try {
            Response res = uploadManager.put(data, fileName, upToken);
            logger.info(res + "\r\n" + res.bodyString());
            if (res.isOK()) {
                result = true;
            }
        } catch (QiniuException e) {
            Response r = e.response;
            logger.error(r.toString());
            e.printStackTrace();
        }
        return result;
    }


    public boolean upload(byte[] data, String fileName) throws Exception {
        String token = this.getUpToken();
        return this.upload(data, token, fileName);
    }

    public boolean upload(File file, String fileName) {
        boolean result = false;
        try {
            Response res = uploadManager.put(file, fileName, getUpToken());
            logger.info(res + "\r\n" + res.bodyString());
            if (res.isOK()) {
                result = true;
            }
        } catch (QiniuException e) {
            Response r = e.response;
            logger.error(r.toString());
            e.printStackTrace();
        }
        return result;
    }

    public boolean upload(String filePath, String fileName) {
        boolean result = false;
        try {
            Response res = uploadManager.put(filePath, fileName, getUpToken());
            logger.info(res + "\r\n" + res.bodyString());
            if (res.isOK()) {
                result = true;
            }
        } catch (QiniuException e) {
            Response r = e.response;
            logger.error(r.toString());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 输入流转换为byte数组
     */
    public byte[] getBytesByInputStream(InputStream in) {
        byte[] intobyte = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int i = 0;
            while ((i = in.read(buff, 0, 100)) > 0) {
                out.write(buff, 0, i);
            }
            intobyte = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intobyte;
    }


}
