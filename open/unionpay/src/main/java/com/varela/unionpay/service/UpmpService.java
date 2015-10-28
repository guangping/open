package com.varela.unionpay.service;


import com.varela.unionpay.conf.UpmpConfig;
import com.varela.unionpay.util.UpmpCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 类名：接口处理核心类 功能：组转报文请求，发送报文，解析应答报文 版本：1.0 日期：2012-10-11 作者：中国银联UPMP团队 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 */
public class UpmpService {

    private static final Logger logger = LoggerFactory
            .getLogger(UpmpService.class);

    /**
     * 交易接口处理
     *
     * @param req  请求要素
     * @param resp 应答要素
     * @return 是否成功
     */
    public static boolean trade(Map<String, String> req,
                                Map<String, String> resp) {
        String nvp = buildReq(req);

	/*	String respString = post(UpmpConfig.TRADE_URL, nvp);
        return verifyResponse(respString, resp);*/
        return false;
    }

    /**
     * 交易查询处理
     *
     * @param req  请求要素
     * @param resp 应答要素
     * @return 是否成功
     */
    public static boolean query(Map<String, String> req,
                                Map<String, String> resp) {
        String nvp = buildReq(req);

        //调用post
		/*String respString = post(UpmpConfig.QUERY_URL, nvp);
		return verifyResponse(respString, resp);*/
        return false;
    }

    /**
     * 拼接保留域
     *
     * @param req 请求要素
     * @return 保留域
     */
    public static String buildReserved(Map<String, String> req) {
        StringBuilder merReserved = new StringBuilder();
        merReserved.append("{");
        merReserved.append(UpmpCore.createLinkString(req, false, true));
        merReserved.append("}");
        return merReserved.toString();
    }

    /**
     * 拼接请求字符串
     *
     * @param req 请求要素
     * @return 请求字符串
     */
    public static String buildReq(Map<String, String> req) {
        // 除去数组中的空值和签名参数
        Map<String, String> filteredReq = UpmpCore.paraFilter(req);
        // 生成签名结果
        String signature = UpmpCore.buildSignature(filteredReq);

        // 签名结果与签名方式加入请求提交参数组中
        filteredReq.put(UpmpConfig.SIGNATURE, signature);
        filteredReq.put(UpmpConfig.SIGN_METHOD, UpmpConfig.SIGN_TYPE);

        return UpmpCore.createLinkString(filteredReq, false, true);
    }

    /**
     * 异步通知消息验证
     *
     * @param para 异步通知消息
     * @return 验证结果
     */
    public static boolean verifySignature(Map<String, String> para) {
        String respSignature = para.get(UpmpConfig.SIGNATURE);
        // 除去数组中的空值和签名参数
        Map<String, String> filteredReq = UpmpCore.paraFilter(para);
        String signature = UpmpCore.buildSignature(filteredReq);
        if (null != respSignature && respSignature.equals(signature)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 应答解析
     *
     * @param respString 应答报文
     * @param resp       应答要素
     * @return 应答是否成功
     */
    private static boolean verifyResponse(String respString,
                                          Map<String, String> resp) {
        if (respString != null && !"".equals(respString)) {
            // 请求要素
            Map<String, String> para;
            try {
                para = UpmpCore.parseQString(respString);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return false;
            }
            boolean signIsValid = verifySignature(para);

            resp.putAll(para);

            if (signIsValid) {
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

	/*private static HttpConnectionManagerParams loadHttpConfFromFile() {
		String file = "httputil";
		encoding = ConstantUtil.getPropertiesStr(file, "http.content.encoding");

		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setConnectionTimeout(ConstantUtil.getPropertiesInt(file,
				"http.connection.timeout"));
		params.setSoTimeout(ConstantUtil.getPropertiesInt(file,
				"http.so.timeout"));
		params.setStaleCheckingEnabled(ConstantUtil.getPropertiesBoolean(file,
				"http.stale.check.enabled"));
		params.setTcpNoDelay(ConstantUtil.getPropertiesBoolean(file,
				"http.tcp.no.delay"));
		params.setDefaultMaxConnectionsPerHost(ConstantUtil.getPropertiesInt(
				file, "http.default.max.connections.per.host"));
		params.setMaxTotalConnections(ConstantUtil.getPropertiesInt(file,
				"http.max.total.connections"));
		params.setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(0, false));
		return params;
	}

	public static String post(String url, String encoding, String content) {
		try {
			byte[] resp = post(url, content.getBytes(encoding));
			if (null == resp)
				return null;
			return new String(resp, encoding);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String post(String url, String content) {
		return post(url, encoding, content);
	}

	public static byte[] post(String url, byte[] content) {
		try {
			byte[] ret = post(url, new ByteArrayRequestEntity(content));
			return ret;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static byte[] post(String url, RequestEntity requestEntity)
			throws Exception {

		PostMethod method = new PostMethod(url);
		method.addRequestHeader("Connection", "Keep-Alive");
		method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(0, false));
		method.setRequestEntity(requestEntity);
		method.addRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");

		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				logger.info("httpCode=" + statusCode);
				return method.getResponseBody();
			}
			return method.getResponseBody();

		} catch (SocketTimeoutException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		} finally {
			method.releaseConnection();
		}
	}

	public static String encoding;

	private static final HttpConnectionManager connectionManager;

	private static final HttpClient client;

	static {

		HttpConnectionManagerParams params = loadHttpConfFromFile();

		connectionManager = new MultiThreadedHttpConnectionManager();

		connectionManager.setParams(params);

		client = new HttpClient(connectionManager);
	}*/
}
