package com.varela.open.security;


public interface FileUploadController {

    /**
     * 上传文件的类型是否是允许
     *
     * @param fileType
     * @return
     */
    boolean isAllowFileType(String fileType);

    /**
     * 是否超过了上传大小的限制
     *
     * @param fileSize
     * @return
     */
    boolean isExceedMaxSize(int fileSize);
}

