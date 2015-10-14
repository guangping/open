/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012
 * 日    期：12-8-1
 */
package com.varela.open.impl;

import com.varela.open.request.UploadFile;

import java.io.File;


public class ExtUploadFile extends UploadFile {
    public ExtUploadFile(String fileType, byte[] content) {
        super(fileType, content);
    }

    public ExtUploadFile(File file) {
        super(file);
    }
}

