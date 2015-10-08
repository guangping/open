/**
 * 日    期：12-2-27
 */
package com.varela.open;

import java.io.OutputStream;

/**
 * <pre>
 *   负责将请求方法返回的响应对应流化为相应格式的内容。
 * </pre>
 *
 * @author 陈雄华
 * @version 1.0
 */
public interface RopMarshaller {
    void marshaller(Object object, OutputStream outputStream);
}

