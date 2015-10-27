package com.varela.wechat.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.varela.wechat.pojo.WeChatPay;

/**
 * 
 * @ClassName: XStreamUtil
 * @author ListFeng
 * @date 2015年8月18日 上午10:56:28
 * @Description: xstream 工具
 *
 */
public class XStreamUtil {

	/**
	 * OrderModel转换为xml
	 * 
	 * @param t
	 * @return
	 */
	public static String convertModel2Xml(WeChatPay order) {
		XStream xs = getXstreamInstance();
		xs.alias("xml", WeChatPay.class);
		return xs.toXML(order);
	}

	/**
	 * 转换调用接口的返回数据
	 * 
	 * @param post
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convetXml2Model(String data, Class<T> clazz) {
		XStream xs = getXstreamInstance();
		xs.alias("xml", clazz);
		return (T) xs.fromXML(data);
	}

	/**
	 * 获取XStream对象
	 */
	public static XStream getXstreamInstance() {
		return new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
	}
}
