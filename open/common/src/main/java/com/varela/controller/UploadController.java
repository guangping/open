package com.varela.controller;

import com.varela.pojo.APIResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by lance on 2016/2/17.
 */
@Controller
@RequestMapping(value = "upload", method = RequestMethod.POST)
public class UploadController extends BaseController {

    @RequestMapping
    @ResponseBody
    public APIResult upload() {
        APIResult apiResult = new APIResult();

        try {
            Map<String, MultipartFile> filemap = null;
            if (this.request instanceof MultipartHttpServletRequest) {
                filemap = ((MultipartHttpServletRequest) request).getFileMap();
            }
            for (Map.Entry<String, MultipartFile> entry : filemap.entrySet()) {
                MultipartFile multipartFile = entry.getValue();
                InputStream inputStream = multipartFile.getInputStream();
                //处理上传逻辑

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiResult;
    }
}
