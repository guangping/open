package com.varela.open.request;

/**
 * <pre>
 *   将以BASE64位编码字符串转换为字节数组的{@link UploadFile}对象
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public class UploadFileConverter implements RopConverter<String, UploadFile> {


    public UploadFile convert(String source) {
        String fileType = UploadFileUtils.getFileType(source);
        byte[] contentBytes = UploadFileUtils.decode(source);
        return new UploadFile(fileType, contentBytes);
    }


    public String unconvert(UploadFile target) {
        return UploadFileUtils.encode(target);
    }


    public Class<String> getSourceClass() {
        return String.class;
    }


    public Class<UploadFile> getTargetClass() {
        return UploadFile.class;
    }
}

